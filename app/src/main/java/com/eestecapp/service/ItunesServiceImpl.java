package com.eestecapp.service;

import com.eestecapp.Constants;
import com.eestecapp.event.SongsListEvent;
import com.eestecapp.model.SongEntity;
import com.eestecapp.service.response.SongsResponse;
import com.squareup.otto.Bus;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Spiros I. Oikonomakis on 11/12/15.
 */
public class ItunesServiceImpl {

  private final Bus bus;
  private final ItunesService itunesService;

  public ItunesServiceImpl(Bus bus) {
    RestAdapter restApi = new RestAdapter.Builder().setEndpoint(Constants.API_URL)
        .setLogLevel(RestAdapter.LogLevel.FULL)
        .build();

    this.itunesService = restApi.create(ItunesService.class);
    this.bus = bus;
  }

  public void getSongsByTerm(String term) {
    this.itunesService.getSongsByTerm(term, new Callback<SongsResponse>() {
      @Override
      public void success(SongsResponse songsResponse, Response response) {
        if (songsResponse.getResultCount() > 0) {
          bus.post(new SongsListEvent(songsResponse.getResults()));
        } else {
          bus.post(new SongsListEvent(new SongEntity[]{}));
        }
      }

      @Override
      public void failure(RetrofitError error) {
        bus.post(error.getMessage());
      }
    });
  }
}
