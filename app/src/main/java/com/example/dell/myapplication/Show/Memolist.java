package com.example.dell.myapplication.Show;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.myapplication.Activitybo;
import com.example.dell.myapplication.R;

import java.util.List;

/**
 * Created by DELL on 5/21/2016.
 */
public class Memolist extends RecyclerView.Adapter<Memolist.MyViewHolder>  {

    private List<Activitybo> orderBOList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView desc, loc, md,mt;

        public MyViewHolder(View view) {
            super(view);

            desc = (TextView) view.findViewById(R.id.Desc);
            loc = (TextView) view.findViewById(R.id.Location);
            md = (TextView) view.findViewById(R.id.mdate);
            mt = (TextView) view.findViewById(R.id.mtime);
        }
    }


    public Memolist(List<Activitybo> moviesList) {
        this.orderBOList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.demandslist, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Activitybo movie = orderBOList.get(position);
        holder.desc.setText(movie.getDescription());
        holder.loc.setText(movie.getLocation());
        holder.md.setText(movie.getDate());
        holder.mt.setText(movie.getTime());

    }

    @Override
    public int getItemCount() {
        return orderBOList.size();
    }
}