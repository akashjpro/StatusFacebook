package com.status.aka.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.status.aka.statusfacebook.R;

/**
 * Created by Aka on 4/5/2017.
 */

public class ColorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mContext;
    String[] arrayColor;

    public class ViewHolderColor extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolderColor(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }

    public ColorAdapter(Context mContext,String[] arrayColor) {
        this.mContext = mContext;
        this.arrayColor = arrayColor;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_color, parent, false);
        return new ViewHolderColor(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ViewHolderColor){
            ViewHolderColor holderColor = (ViewHolderColor) holder;
            holderColor.textView.setBackgroundColor(Color.parseColor(arrayColor[position].toString()));
            holderColor.textView.setText(arrayColor[position]);
        }
    }

    @Override
    public int getItemCount() {
        return arrayColor.length;
    }
}
