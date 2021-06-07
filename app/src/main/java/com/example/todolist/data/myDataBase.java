package com.example.todolist.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class myDataBase  extends SQLiteOpenHelper {

    Context context;

    public myDataBase (Context context)
    {
        super(context,"myDataBase",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE task(id INTEGER PRIMARY KEY AUTOINCREMENT not null,taskname VARChAR(200),details VARCHAR(500),state int)");
    }


    public boolean updataTableDetails(String details,int id) {
        try {
            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues data=new ContentValues();
            data.put("details",details);
            db.update("task",data,"id=?",new String[]{String.valueOf(id)});
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }

    }

    public boolean delete(int id) {
        try{
            SQLiteDatabase db=this.getWritableDatabase();
            db.execSQL("DELETE From task where id ='"+id+"'");
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean udateTableState(int state,int id) {
        try {
            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues data=new ContentValues();
            data.put("state",state);
            db.update("task",data,"id=?",new String[]{String.valueOf(id)});
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }

    }

    public myList getById(int id) {

        ArrayList<myList>lists=getAll();
        return lists.get(id);
    }
    public myList getPostionArray(int postions)
    {

        return (myList) getAll().get(postions);
    }

    public boolean add(String task,String details,int s) {
        try {
            ContentValues data=new ContentValues();
            data.put("taskname",task);
            data.put("details",details);
            data.put("state",s);
            SQLiteDatabase db=this.getWritableDatabase();
            db.insert("task",null,data);
            return true;
        }
        catch (Exception e){
            return false;
        }

    }

    public int getNumberRow() {
        int counter=0;
        String sql="SELECT COUNT(*) FROM task";
        Cursor y=getReadableDatabase().rawQuery(sql,null);

        if(y.getCount()>0)
        {
            y.moveToFirst();
            counter=y.getInt(0);
        }

        return counter;
    }

    public ArrayList getAll() {
          ArrayList<myList>lists=new ArrayList<>();

            SQLiteDatabase db=this.getReadableDatabase();
            Cursor y=db.rawQuery("SELECT * FROM task ",null);

            y.moveToFirst();

            do
            {
                myList list=new myList(y.getInt(0),y.getString(1),y.getString(2),y.getInt(3));
                lists.add(list);
            }while(y.moveToNext());


        return lists;

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
