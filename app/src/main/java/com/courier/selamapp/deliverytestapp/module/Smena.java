package com.courier.selamapp.deliverytestapp.module;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import io.reactivex.annotations.NonNull;
import java.sql.Date;

/**
 * Created by narik on 20.03.2018.
 */
@Entity (tableName = "smena")

public class Smena {
  @NonNull
  @PrimaryKey (autoGenerate = true)
  @ColumnInfo (name = "id")
  private int id;

  @ColumnInfo (name = "date")
  private String date;

  @ColumnInfo (name = "start")
  private String start;

  @ColumnInfo (name = "finish")
  private String finish;

  @ColumnInfo (name = "place")
  private String place;

  @ColumnInfo (name="salary")
  private  int salary_day;

  public Smena() {
  }

  @Ignore
  public Smena(String date, String start, String finish, String place, int salary_day) {

    this.date = date;
    this.start = start;
    this.finish = finish;
    this.place = place;
    this.salary_day = salary_day;
  }


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getStart() {
    return start;
  }

  public void setStart(String start) {
    this.start = start;
  }

  public String getFinish() {
    return finish;
  }

  public void setFinish(String finish) {
    this.finish = finish;
  }

  public String getPlace() {
    return place;
  }

  public void setPlace(String place) {
    this.place = place;
  }



  public int getSalary_day() {
    return salary_day;
  }

  public void setSalary_day(int salary_day) {
    this.salary_day = salary_day;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
