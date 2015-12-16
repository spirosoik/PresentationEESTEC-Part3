package com.eestecapp.ui.songslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.eestecapp.R;
import com.eestecapp.model.SongEntity;
import com.eestecapp.ui.songshow.SongShowActivity;
import com.eestecapp.ui.songshow.SongShowFragment;

public class SongsActivity extends AppCompatActivity implements SongsFragment.SongsListener {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

  }

  @Override public void onSongSelected(SongEntity songEntity) {

    SongShowFragment songShowFragment =
        (SongShowFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentSongView);
    if (songShowFragment != null) {
      if (songShowFragment.isAdded()) {
        songShowFragment.songShow(songEntity);
      }
    } else {
      Intent intent = new Intent(SongsActivity.this, SongShowActivity.class);
      intent.putExtra(SongShowActivity.EXTRA_PARAM_SONG, songEntity);
      startActivity(intent);
    }
  }
}
