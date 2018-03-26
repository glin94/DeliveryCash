  package com.courier.selamapp.deliverytestapp;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.hardware.input.InputManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.courier.selamapp.deliverytestapp.Database.SmenaRepository;
import com.courier.selamapp.deliverytestapp.local.SmenaDataSource;
import com.courier.selamapp.deliverytestapp.local.SmenaDatabase;
import com.courier.selamapp.deliverytestapp.module.Smena;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

  public class MainActivity extends AppCompatActivity  {

    // private GoogleApiClient mGoogleApiClient;
    ;
    private FloatingActionButton fab;
    //Adapter
    SmenaAdapter adapter;
    Smena smena;
    List<Smena> smenaList = new ArrayList<>();
    // ArrayAdapter adapter;
    RecyclerView recyclerView;
    //DataBase

    //Views
    EditText editTextPlace;
    Calendar calendar;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    private Button buttonDate;
    private Button buttonStart;
    private Button buttonFinish;



    //SmenaValues
    String date;
    String place;
    String start;
    String finish;

    private CompositeDisposable compositeDisposable;
    private SmenaRepository smenaRepository;
    private ImageView save;
    private SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      calendar = Calendar.getInstance();
      simpleDateFormat=new SimpleDateFormat("HH:mm");
      //Init
      compositeDisposable = new CompositeDisposable();

      //InitView
      recyclerView = (RecyclerView) findViewById(R.id.recyleView);
      recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
      fab = (FloatingActionButton) findViewById(R.id.fab);

      adapter = new SmenaAdapter(smenaList);
      registerForContextMenu(recyclerView);
      recyclerView.setAdapter(adapter);

      //DataBase
      SmenaDatabase smenaDatabase = SmenaDatabase.getmInstance(this);
      smenaRepository = SmenaRepository
          .getmInstance(SmenaDataSource.getmInstance(smenaDatabase.smenaDAO()));

      //loadAllData from  DataBase
      loadData();

      fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          showDialog();

        }
      });

    }





    private void loadData() {
      //UseRxJava
      Disposable disposable = smenaRepository.getAllSmena()
          .observeOn(AndroidSchedulers.mainThread())
          .subscribeOn(Schedulers.io())
          .subscribe(new Consumer<List<Smena>>() {
            @Override
            public void accept(List<Smena> smenas) throws Exception {
              onGetAllSmenasSuccces(smenas);
            }
          }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
              Toast.makeText(MainActivity.this, "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
          });
      compositeDisposable.add(disposable);

    }


    void showDialog() {
      final Dialog fullscreenDialog = new Dialog(MainActivity.this, R.style.DialogFullscreen);
      fullscreenDialog.setContentView(R.layout.added_dialog);
      final EditText editTextPlace = fullscreenDialog.findViewById(R.id.editTextPlace);
      buttonDate = fullscreenDialog.findViewById(R.id.buttonDate);
      buttonStart = fullscreenDialog.findViewById(R.id.starTime);
      buttonFinish = fullscreenDialog.findViewById(R.id.finishTime);
      save=(ImageView)fullscreenDialog.findViewById(R.id.img_dialog_fullscreen_accept);
      place=editTextPlace.getText().toString();
      ImageView img_dialog_fullscreen_close = fullscreenDialog
          .findViewById(R.id.img_dialog_fullscreen_close);

      img_dialog_fullscreen_close.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          fullscreenDialog.dismiss();
        }
      });
      editTextPlace.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            place=s.toString();

        }
      });
      buttonDate.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          datePickerDialog = new DatePickerDialog(MainActivity.this, new OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
              calendar.set(Calendar.YEAR, year);
              calendar.set(Calendar.MONTH, month);
              calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
               date = DateFormat.getDateInstance(DateFormat.MEDIUM)
                  .format(calendar.getTime());
              buttonDate.setText(date);
            }
          }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
              calendar.get(Calendar.DAY_OF_MONTH));

          datePickerDialog.show();
        }
      });

      buttonStart.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {

          timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {

              calendar.set(Calendar.HOUR_OF_DAY, i);
              calendar.set(Calendar.MINUTE, i1);
              start = simpleDateFormat.format(calendar.getTime());
              buttonStart.setText(start);
            }
          }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
          timePickerDialog.show();
        }
      });
      buttonFinish.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
              calendar.set(Calendar.HOUR_OF_DAY, i);
              calendar.set(Calendar.MINUTE, i1);

              finish = simpleDateFormat.format(calendar.getTime());
              buttonFinish.setText(finish);
            }
          }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
          timePickerDialog.show();
        }
      });
      save.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          AddSmena();
          fullscreenDialog.dismiss();
        }
      });
      fullscreenDialog.show();


    }

    private void onGetAllSmenasSuccces(List<Smena> smenas) {
      smenaList.clear();
      smenaList.addAll(smenas);
      adapter.notifyDataSetChanged();
    }

    public void AddSmena() {
      Disposable disposable = Observable.create(new ObservableOnSubscribe<Object>() {
        @Override
        public void subscribe(ObservableEmitter<Object> e) throws Exception {
          smena = new Smena(date, start, finish,place, day_Salary(100));
          smenaList.add(smena);

          smenaRepository.insertSmena(smena);
          e.onComplete();
        }
      })
          .observeOn(AndroidSchedulers.mainThread())
          .subscribeOn(Schedulers.io())
          .subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
              Toast.makeText(MainActivity.this, "Смена добавлена", Toast.LENGTH_SHORT).show();
            }
          }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
              Toast.makeText(MainActivity.this, "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
          }, new Action() {
            @Override
            public void run() throws Exception {
              loadData();
            }
          });

    }
    public int day_Salary(int i)
    { finish=String.valueOf(finish.toCharArray(),0,2);
      start=String.valueOf(start.toCharArray(),0,2);

      int salary =(Integer.parseInt(finish)-Integer.parseInt(start))*i;
        return salary;
    }
  }


