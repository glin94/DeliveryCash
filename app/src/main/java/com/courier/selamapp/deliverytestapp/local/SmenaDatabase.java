package com.courier.selamapp.deliverytestapp.local;

import static com.courier.selamapp.deliverytestapp.local.SmenaDatabase.DATABASE_VERSION;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.courier.selamapp.deliverytestapp.module.Smena;

/**
 * Created by narik on 20.03.2018.
 */
@Database(entities = Smena.class,version = DATABASE_VERSION)

public abstract class SmenaDatabase extends RoomDatabase {

  public static final int DATABASE_VERSION = 1;
  public static final String DATABASE_NAME = "DeliveryClub";

  public abstract SmenaDAO smenaDAO();

  private static SmenaDatabase mInstance;

  public static SmenaDatabase getmInstance(Context context)
  {
    if (mInstance==null) {
      mInstance = Room.databaseBuilder(context, SmenaDatabase.class, DATABASE_NAME)
          .fallbackToDestructiveMigration()
          .build();
    }
    return mInstance;
  }

}


