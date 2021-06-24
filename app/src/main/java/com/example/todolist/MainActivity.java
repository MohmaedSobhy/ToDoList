package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.todolist.data.myDataBase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity  {


    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton floatingActionButton=findViewById(R.id.floatingbuttone);
        RecyclerView recyclerView=findViewById(R.id.recyclerview);
        RelativeLayout relativeLayout=findViewById(R.id.relative);
        ImageView button=findViewById(R.id.imageview);
        EditText editText=findViewById(R.id.editText);

        myDataBase dataBase=new myDataBase(this);


        adapter=new Adapter(this);

        if(dataBase.getNumberRow()!=0)
          adapter.setLists(dataBase.getAll());

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                floatingActionButton.setVisibility(View.GONE);
                relativeLayout.setVisibility(View.VISIBLE);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(editText.getText().equals("")))
                {
                  if(dataBase.add(editText.getText().toString(),"",0))
                  {
                       floatingActionButton.setVisibility(View.VISIBLE);
                       relativeLayout.setVisibility(View.GONE);
                       editText.setText("");
                       adapter.setLists(dataBase.getAll());
                       adapter.setLists(dataBase.getAll());
                  }
                  else {
                      Toast.makeText(MainActivity.this, "not saved", Toast.LENGTH_SHORT).show();
                  }
                }
            }
        });

         recyclerView.setAdapter(adapter);
         recyclerView.setLayoutManager(new LinearLayoutManager(this));
         ItemTouchHelper itemTouchHelper=new ItemTouchHelper(new RecyclerViewTouchHelper(adapter));
         itemTouchHelper.attachToRecyclerView(recyclerView);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
        System.exit(0);
    }
}