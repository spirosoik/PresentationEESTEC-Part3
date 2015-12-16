package com.eestecapp.ui.songshow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.eestecapp.R;
import com.eestecapp.model.SongEntity;

public class SongShowActivity extends AppCompatActivity {

  public static final String EXTRA_PARAM_SONG = "com.eestecapp.ui.songshow.SONG";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_song_show);

    Bundle bundle = getIntent().getExtras();
    if (bundle.containsKey(EXTRA_PARAM_SONG)) {

      SongShowFragment songShowFragment =
          (SongShowFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentSongShow);
      songShowFragment.songShow((SongEntity) bundle.get(EXTRA_PARAM_SONG));
    }
  }
}
