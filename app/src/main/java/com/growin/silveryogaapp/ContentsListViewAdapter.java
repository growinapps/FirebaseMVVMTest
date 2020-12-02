package com.growin.silveryogaapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.growin.silveryogaapp.Model.Contents;

import java.util.ArrayList;

public class ContentsListViewAdapter extends RecyclerView.Adapter<ContentsListViewAdapter.ViewHolder>{

    private ArrayList<Contents> lstViewItem;

    public ContentsListViewAdapter(ArrayList<Contents> lstViewItem) {
        this.lstViewItem = lstViewItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contents_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Contents item = lstViewItem.get(position);

        holder.itemView.setTag(item);
        Glide.with(holder.itemView.getContext()).load(item.getUriImg()).into(holder.imageView);
        holder.titleTextView.setText(item.getStrName());
    }

    @Override
    public int getItemCount() {
        return lstViewItem.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView titleTextView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.item_text) ;
            imageView = itemView.findViewById(R.id.item_image);

        }
    }
/*
    private ArrayList<Contents> lstViewItem = new ArrayList<Contents>();

    public ContentsListViewAdapter(ArrayList<Contents> data) {
        this.lstViewItem = data;
    }

    @Override
    public int getCount() {
        return lstViewItem.size();
    }

    @Override
    public Object getItem(int position) {
        return lstViewItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.Contents_item, parent, false);

        ImageView iconImageView = convertView.findViewById(R.id.item_image) ;
        TextView titleTextView = convertView.findViewById(R.id.item_text) ;

        Contents vItem = lstViewItem.get(position);
        Glide.with(convertView).load(vItem.getUriImg()).into(iconImageView);
        titleTextView.setText(vItem.getStrName());
        return convertView;

    }

*/

}
