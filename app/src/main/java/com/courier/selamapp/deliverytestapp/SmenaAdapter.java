package com.courier.selamapp.deliverytestapp;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.courier.selamapp.deliverytestapp.module.Smena;
import java.util.List;

/**
 * Created by narik on 21.03.2018.
 */

public class SmenaAdapter extends RecyclerView.Adapter<SmenaAdapter.SmenaViewHolder> {
  public static class SmenaViewHolder extends  RecyclerView.ViewHolder
  {
    CardView cv;
    TextView date;
    TextView startTime;
    TextView finishTime;
    TextView day_salary;
    TextView place;
    public SmenaViewHolder(View itemView) {
      super(itemView);
      date=itemView.findViewById(R.id.date);
      startTime=itemView.findViewById(R.id.start);
      finishTime=itemView.findViewById(R.id.finish);
      day_salary=itemView.findViewById(R.id.salary_day);
      place=itemView.findViewById(R.id.place);


     // day_salary.setTypeface(new MainActivity().face);
    }

  }
  List<Smena> smenas;

  public SmenaAdapter(List<Smena> smenas) {
    this.smenas=smenas;
  }

  @Override
  public int getItemCount() {
    return smenas.size();
  }

  @NonNull
  @Override
  public SmenaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
      SmenaViewHolder smenaViewHolder=new SmenaViewHolder(v);

    return smenaViewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull SmenaViewHolder holder, int position) {
    holder.date.setText(smenas.get(position).getDate());
    holder.startTime.setText(smenas.get(position).getStart());
    holder.finishTime.setText(smenas.get(position).getFinish());
    holder.day_salary.setText(String.valueOf(smenas.get(position).getSalary_day()));
    holder.place.setText(smenas.get(position).getPlace());
  }

  @Override
  public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
    super.onAttachedToRecyclerView(recyclerView);
  }
}
