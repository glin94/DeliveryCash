package com.courier.selamapp.deliverytestapp.local;

import android.arch.persistence.room.Dao;
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
@Dao
public interface SmenaDAO {

  @Query("SELECT * FROM smena WHERE id=:smenaId")
  Flowable<Smena> getSmenabyId(int smenaId);

  @Query("SELECT * FROM smena")
  Flowable<List<Smena>> getAllSmena();

  @Insert
  void insertSmena (Smena... smenas);

  @Update
  void updateSmena (Smena... smenas);

  @Delete
  void  deleteSmena(Smena smena);

  @Query("DELETE FROM smena")
    void deleteAllSmenas();
}
