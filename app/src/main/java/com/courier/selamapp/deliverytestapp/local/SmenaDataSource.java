package com.courier.selamapp.deliverytestapp.local;

import com.courier.selamapp.deliverytestapp.Database.ISmenaDataSource;
import com.courier.selamapp.deliverytestapp.module.Smena;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by narik on 20.03.2018.
 */

public class SmenaDataSource implements ISmenaDataSource {

  private SmenaDAO smenaDAO;
  private  static SmenaDataSource mInstance;
  public SmenaDataSource(SmenaDAO smenaDAO) {
    this.smenaDAO = smenaDAO;
  }

  public static SmenaDataSource getmInstance(SmenaDAO smenaDAO)
  {
    if (mInstance==null)
    {

      mInstance=new SmenaDataSource(smenaDAO);
    }
    return mInstance;
  }

  @Override
  public Flowable<Smena> getSmenabyId(int smenaId) {
    return smenaDAO.getSmenabyId(smenaId);
  }

  @Override
  public Flowable<List<Smena>> getAllSmena() {
    return smenaDAO.getAllSmena();
  }

  @Override
  public void insertSmena(Smena... smenas) {
        smenaDAO.insertSmena(smenas);
  }

  @Override
  public void updateSmena(Smena... smenas) {
      smenaDAO.updateSmena(smenas);
  }

  @Override
  public void deleteSmena(Smena smena) {
      smenaDAO.deleteSmena(smena);
  }

  @Override
  public void deleteAllSmenas() {
      smenaDAO.deleteAllSmenas();
  }
}
