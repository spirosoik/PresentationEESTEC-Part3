package com.eestecapp.service;

import com.eestecapp.service.response.SongsResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface ItunesService {
  /**
   * Returns track list
   */
  @GET("/search?media=music&entity=musicTrack")
  void getSongsByTerm(
      @Query(value = "term", encodeValue = true) String keyword,
      Callback<SongsResponse> songsResponseCallback
  );
}