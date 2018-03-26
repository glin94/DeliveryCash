package com.courier.selamapp.deliverytestapp.Database;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.courier.selamapp.deliverytestapp.module.Smena;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by narik on 20.03.2018.
 */

public interface ISmenaDataSource {
  Flowable<Smena> getSmenabyId(int smenaId);

  Flowable<List<Smena>> getAllSmena();

  void insertSmena (Smena... smenas);

  void updateSmena (Smena... smenas);

  void  deleteSmena(Smena smena);

  void deleteAllSmenas();

}
