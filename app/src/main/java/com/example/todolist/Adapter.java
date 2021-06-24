package com.example.todolist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.data.myDataBase;
import com.example.todolist.data.myList;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.holderCheck> {

    Context context;
    ArrayList<myList> lists=new ArrayList<>();
    myDataBase dataBase;
    boolean check=false;

    public Adapter(Context context) {
        this.context = context;
        dataBase=new myDataBase(context);
    }

    public Context getContext() {
        return context;
    }
    public void Deletecard(int p)
    {
        dataBase.delete(lists.get(p).getId());
        lists.remove(p);

    }

    public void setLists(ArrayList<myList> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public holderCheck onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.checkbox,parent,false);
        return new holderCheck(view);
    }


    @Override
    public void onBindViewHolder(@NonNull holderCheck holder, int position) {

        myDataBase dataBase=new myDataBase(context);

           holder.tasktname.setText(lists.get(position).getTaskname());

            holder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int x=lists.get(position).getId();
                    if(x==0)
                    {
                        Toast.makeText(context, "Some Wrong happen", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent intent=new Intent(context,taskDetails.class);
                        Bundle bundle=new Bundle();
                        bundle.putInt("Header",position);
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                    }
                }
            });

            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(check)
                    {
                        holder.image.setImageResource(R.drawable.ic_baseline_check_24);
                        dataBase.udateTableState(1,lists.get(position).getId());
                        MediaPlayer x=MediaPlayer.create(context,R.raw.preview);
                        x.start();
                        check=false;
                    }
                    else {
                        holder.image.setImageResource(R.drawable.box_);
                        dataBase.udateTableState(0,lists.get(position).getId());
                        check=true;
                    }

                }
            });

            if(lists.get(position).getState()==1) {
                holder.image.setImageResource(R.drawable.ic_baseline_check_24);
            }
            else {
                dataBase.udateTableState(0,lists.get(position).getId());
            }


    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class holderCheck extends RecyclerView.ViewHolder{
        TextView tasktname;
        ImageView image;
        CardView card;
        public holderCheck(@NonNull View itemView) {
            super(itemView);

          tasktname=itemView.findViewById(R.id.taskname);
          image=itemView.findViewById(R.id.checkitme);
          card=itemView.findViewById(R.id.cardparent);

        }
    }

}
