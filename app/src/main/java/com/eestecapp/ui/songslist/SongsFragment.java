package com.eestecapp.ui.songslist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.eestecapp.R;
import com.eestecapp.event.SongsListEvent;
import com.eestecapp.model.SongEntity;
import com.eestecapp.service.ItunesServiceImpl;
import com.eestecapp.ui.songslist.adapter.SongsAdapter;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.squareup.otto.ThreadEnforcer;

public class SongsFragment extends Fragment implements AdapterView.OnItemClickListener {

  //UI
  @Bind(R.id.lvSongs) GridView lvSongs;

  //Config
  private SongsAdapter songsAdapter;
  private SongsListener songsListener;
  private Bus bus = new Bus(ThreadEnforcer.MAIN);

  interface SongsListener {
    void onSongSelected(SongEntity songEntity);
  }

  public static SongsFragment newInstance() {
    return new SongsFragment();
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof SongsActivity) {
      this.songsListener = (SongsListener) context;
    }
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View fragmentView = inflater.inflate(R.layout.fragment_songs, container, false);

    ButterKnife.bind(this, fragmentView);

    lvSongs.setOnItemClickListener(this);
    songsAdapter = new SongsAdapter(getActivity(), new SongEntity[]{});
    this.lvSongs.setAdapter(songsAdapter);
    return fragmentView;
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    songsAdapter = new SongsAdapter(getActivity(), new SongEntity[]{});
    this.lvSongs.setAdapter(songsAdapter);

    ItunesServiceImpl itunesService = new ItunesServiceImpl(bus);
    itunesService.getSongsByTerm("dream theater");
  }

  @Override
  public void onStart() {
    super.onStart();
    bus.register(this);
  }

  @Override
  public void onStop() {
    super.onStop();
    bus.unregister(this);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();

    ButterKnife.unbind(this);
  }

  // Inside Fragment or Activity or wherever you need that
  @Subscribe
  public void onSongsListEvent(SongsListEvent event) {
    if (event.getResult() != null) {
      songsAdapter.setSongsList(event.getResult());
    }
  }

  @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    SongEntity songEntity = (SongEntity) songsAdapter.getItem(position);
    if (songEntity != null) {
      this.songsListener.onSongSelected(songEntity);
    }
  }
}
