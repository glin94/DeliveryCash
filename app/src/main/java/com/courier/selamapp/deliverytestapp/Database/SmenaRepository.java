package com.courier.selamapp.deliverytestapp.Database;

import com.courier.selamapp.deliverytestapp.local.SmenaDataSource;
import com.courier.selamapp.deliverytestapp.module.Smena;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by narik on 20.03.2018.
 */

public class SmenaRepository implements ISmenaDataSource {

  private ISmenaDataSource mLocalDataSource;
  private static SmenaRepository mInstance;

  public SmenaRepository(ISmenaDataSource mLocalDataSource) {
    this.mLocalDataSource = mLocalDataSource;
  }

  public static SmenaRepository getmInstance(ISmenaDataSource mLocalDataSource)
  {
    if(mInstance==null)
    {
      mInstance=new SmenaRepository(mLocalDataSource);
    }
    return mInstance;
  }

  @Override
  public Flowable<Smena> getSmenabyId(int smenaId) {
    return mLocalDataSource.getSmenabyId(smenaId);
  }

  @Override
  public Flowable<List<Smena>> getAllSmena() {
    return mLocalDataSource.getAllSmena();
  }

  @Override
  public void insertSmena(Smena... smenas) {
        mLocalDataSource.insertSmena(smenas);
  }

  @Override
  public void updateSmena(Smena... smenas) {
        mLocalDataSource.updateSmena(smenas);
  }

  @Override
  public void deleteSmena(Smena smena) {
        mLocalDataSource.deleteSmena(smena);
  }

  @Override
  public void deleteAllSmenas() {
        mLocalDataSource.deleteAllSmenas();
  }
}
