package com.eestecapp.ui.songshow;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.eestecapp.R;
import com.eestecapp.model.SongEntity;
import com.squareup.picasso.Picasso;

/**
 * Created by Spiros I. Oikonomakis on 08/12/15.
 */
public class SongShowFragment extends Fragment implements View.OnClickListener {

  // CONFIG
  private final static String ARG_SONG = "com.eestecapp.ui.songshow.fragment.SONG";
  private SongEntity songEntity;

  // UI
  @Bind(R.id.avatar) ImageView ivAvatar;
  @Bind(R.id.trackName) TextView txtTrackName;
  @Bind(R.id.trackArtistName)  TextView txtTrackArtistName;
  @Bind(R.id.tvGenreName) TextView tvGenreName;
  @Bind(R.id.tvListenLink) TextView tvListenLink;
  @Bind(R.id.tvLinkArtist) TextView tvLinkArtist;
  @Bind(R.id.tvTrackPrice) TextView tvTrackPrice;
  @Bind(R.id.scrollSongShow) ScrollView scrollSongShow;

  public static SongShowFragment newInstance() {
    return new SongShowFragment();
  }

  public SongShowFragment() {
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View fragmentView = inflater.inflate(R.layout.fragment_song_show, container, false);

    ButterKnife.bind(this, fragmentView);

    tvListenLink.setOnClickListener(this);
    tvLinkArtist.setOnClickListener(this);

    return fragmentView;
  }

  @Override public void onResume() {
    super.onResume();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();

    ButterKnife.unbind(this);
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    songShow(this.songEntity);
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.tvListenLink:
        showLinkInWeb(this.songEntity.getRadioStationUrl());
        break;
      case R.id.tvLinkArtist:
        showLinkInWeb(this.songEntity.getArtistViewUrl());
        break;
    }
  }

  private void showLinkInWeb(String  url) {
    new CustomTabsIntent.Builder().setShowTitle(true)
        .enableUrlBarHiding()
        .setToolbarColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary))
        .build()
        .launchUrl(getActivity(), Uri.parse(url));
  }

  public void songShow(SongEntity songEntity) {
    if (songEntity != null) {
      this.songEntity = songEntity;
      scrollSongShow.setVisibility(View.VISIBLE);
      Picasso.with(getContext()).load(this.songEntity.getImageBig().replace("100x100", "500x500")).into(ivAvatar);
      txtTrackName.setText(this.songEntity.getTrackName());
      txtTrackArtistName.setText(this.songEntity.getArtistName());
      tvGenreName.setText(this.songEntity.getGenre());
      tvTrackPrice.setText(String.valueOf(this.songEntity.getTrackPrice()));
    }
  }


}
