package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todolist.data.myDataBase;

public class taskDetails extends AppCompatActivity {
     int k;
    EditText editText;
    myDataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        Intent intent=getIntent();
        TextView textHeader=findViewById(R.id.textView2);
        editText=findViewById(R.id.taskDetails);
        dataBase=new myDataBase(this);

        Bundle bundle=intent.getExtras();
        if(bundle!=null) {
            k = bundle.getInt("Header", -1);
            if (k != -1) {
                textHeader.setText(dataBase.getPostionArray(k).getTaskname());
                editText.setText(dataBase.getById(k).getDetails());
            } else {
                Toast.makeText(getApplicationContext(), "" + k + " here", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        dataBase.updataTableDetails(editText.getText().toString(),dataBase.getById(k).getId());
    }
}
