package com.eestecapp.event;

import com.eestecapp.model.SongEntity;

/**
 * Created by Spiros I. Oikonomakis on 11/12/15.
 */
public class SongsListEvent {

  private SongEntity[] result;

  public SongsListEvent(SongEntity[] result) {
    this.result = result;
  }

  public SongEntity[] getResult() {
    return result;
  }
}
