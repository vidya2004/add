package com.example.performit.adapter;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.performit.MainActivity;
import com.example.performit.Model.TodoModel;
import com.example.performit.R;
import com.example.performit.Utils.DataBaseHelper;

import java.util.Calendar;
import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder> {

    private static List<TodoModel> mList;
    public static MainActivity activity;
    private DataBaseHelper myDB;
    private int status;

    public ToDoAdapter(DataBaseHelper myDB , MainActivity activity){
        this.activity = activity;
        this.myDB = myDB;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final TodoModel item = mList.get(position);


        holder.textView5.setText(item.getTaskTile());
        holder.textView.setText(item.gettastat());
        holder.textView2.setText("Priority"+":"+item.gettasprior());
        holder.textView3.setText(item.getTask());
        holder.textView4.setText("Due Date"+":"+item.getTasdate());

    }

    public boolean toBoolean(int num){
        return num!=0;
    }


    public Context getContext(){
        return activity;
    }
    public void setTasks(List<TodoModel> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }


    public void editItem(int position){
        TodoModel item = mList.get(position);

        Bundle bundle = new Bundle();
        bundle.putString("task" , item.getTask());
        bundle.putString("priority", item.gettasprior());
        bundle.putString("des", item.getTask());


    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
       TextView textView1;
        TextView textView;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        TextView editText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.editTextText1);
            textView2=itemView.findViewById(R.id.editTextDate3);
            textView4=itemView.findViewById(R.id.Autocompletetext);

            editText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openDatePicker(getAdapterPosition());
                }
            });

        }
        private  void openDatePicker(final int position){
            TodoModel item = mList.get(position);
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog=new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    String selectedDate = i + "-" + (i1 + 1) + "-" + i2;
                    mList.get(position).setTasdate(selectedDate);
                    notifyItemChanged(position);
                }
            },year,month,day);
            datePickerDialog.show();
}
}
}