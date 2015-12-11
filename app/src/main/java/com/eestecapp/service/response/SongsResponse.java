package com.eestecapp.service.response;

import com.eestecapp.model.SongEntity;

/**
 * Created by Spiros I. Oikonomakis on 11/12/15.
 */
public class SongsResponse {

  private int resultCount;

  private SongEntity[] results;

  public int getResultCount() {
    return resultCount;
  }

  public void setResultCount(int resultCount) {
    this.resultCount = resultCount;
  }

  public SongEntity[] getResults() {
    return results;
  }

  public void setResults(SongEntity[] results) {
    this.results = results;
  }
}
