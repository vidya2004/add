package com.example.performit.Utils;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.performit.Model.TodoModel;

import java.util.ArrayList;
import java.util.List;
public abstract class DataBaseHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;

    private static final String DATABASE_NAME="TODO_DATABASE";
    private static final String TABLE_NAME="TODO_TABLE";
    private static final String COL_2="TASK";
    private static final String COL_3="STATUS";
    private static final String COL_4 = "PRIORITY";
    private static final String COL_5 = "DESCRIPTION";
    private static final String COL_6 = "DUE_DATE";

    private static final int DATABASE_VERSION = 7;



    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_NAME + "(  TASK TEXT , STATUS INTEGER , PRIORITY TEXT DEFAULT 'LOW', DESCRIPTION TEXT DEFAULT '', DUE_DATE TEXT DEFAULT '' , CATEGORY TEXT DEFAULT '')");
    }
    public void insertTask(@NonNull TodoModel model){
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COL_2,model.getTask());
        values.put(COL_3,model.gettastat());
        values.put(COL_4, model.gettasprior());
        values.put(COL_5, model.getTask());
        values.put(COL_6, model.getTasdate());
        //insert method
        db.insert(TABLE_NAME,null,values);
    }
    public void updateDueDate(int taskId, String newDueDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_6, newDueDate);
        db.update(TABLE_NAME, values, "ID=?", new String[]{String.valueOf(taskId)});
    }

    public void updateTask(int id,String task,String priority,String description,String category){
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COL_2,task);
        values.put(COL_4, priority);
        values.put(COL_5, description);


        //update method
        db.update(TABLE_NAME,values,"ID=?",new String[]{String.valueOf(id)});


    }
    public void updateStatus(int id,int status){
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COL_3,status);
        //update method
        db.update(TABLE_NAME,values,"ID=?",new String[]{String.valueOf(id)});


    }

    public void deleteTask(int id){
        db=this.getWritableDatabase();
        //delete
        db.delete(TABLE_NAME,"ID=?",new String[]{String.valueOf(id)});
    }


    @SuppressLint("Range")
    public List<TodoModel> getAllTasks(){
        db=this.getWritableDatabase();
        Cursor cursor=null;
        List<TodoModel> modelList=new ArrayList<>();
        db.beginTransaction();
        try{
            cursor=db.query(TABLE_NAME,null,null,null,null,null,null);
            if(cursor!=null){
                if(cursor.moveToFirst()){
                    do{
                        TodoModel task=new TodoModel();
                        task.setTask(cursor.getString(cursor.getColumnIndex(COL_2)));
                        task.setTastat(cursor.getString(cursor.getColumnIndex(COL_3)));
                        task.settasprior(cursor.getString(cursor.getColumnIndex(COL_4)));
                        task.setTask(cursor.getString(cursor.getColumnIndex(COL_5)));
                        task.setTasdate(cursor.getString(cursor.getColumnIndex(COL_6)));
                        modelList.add(task);
                    }while(cursor.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            cursor.close();
        }
        return modelList;
}


}

