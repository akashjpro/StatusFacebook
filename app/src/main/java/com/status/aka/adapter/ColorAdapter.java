package com.status.aka.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.status.aka.Listener.ItemClickListener;
import com.status.aka.statusfacebook.ListColor;
import com.status.aka.statusfacebook.R;

/**
 * Created by Aka on 4/5/2017.
 */

public class ColorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Activity mContext;
    String[] arrayColor;


    public class ViewHolderColor extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemClickListener listener;
        TextView textView;
        public ViewHolderColor(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition(), false);
        }

        public void setClickListener(ItemClickListener listener){
            this.listener = listener;
        }
    }

    public ColorAdapter(Activity mContext,String[] arrayColor) {
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
            final ViewHolderColor holderColor = (ViewHolderColor) holder;
//            holderColor.textView.setBackgroundColor(Color.parseColor(arrayColor[position].toString()));
//            holderColor.textView.setText("");

            GradientDrawable gd = new GradientDrawable();

            //Specify the shape of drawable
            gd.setShape(GradientDrawable.OVAL);
            gd.setColor(Color.parseColor(arrayColor[position].toString()));

            // Create a 2 pixels width red colored border for drawable
            //gd.setStroke(2, Color.RED);

            if (ListColor.position == position)
            {
                gd.setStroke(4, Color.RED);
                if(position == 0) {
                    holderColor.textView.setText("✓");
                    holderColor.textView.setTextColor(Color.RED);
                }
                else {
                    holderColor.textView.setText("✓");
                    holderColor.textView.setTextColor(Color.WHITE);
                }

            }
            else {
                gd.setStroke(4, Color.BLACK);
                holderColor.textView.setText("");
            }

            holderColor.textView.setBackground(gd);

            holderColor.setClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position, boolean isLongClick) {

                    GradientDrawable gd = new GradientDrawable();

                    //Specify the shape of drawable
                    gd.setShape(GradientDrawable.OVAL);
                    gd.setColor(Color.parseColor(arrayColor[position].toString()));

                    // Create a 2 pixels width red colored border for drawable
                    //gd.setStroke(2, Color.RED);


                    gd.setStroke(4, Color.RED);
                    holderColor.textView.setText("✓");
                    holderColor.textView.setBackground(gd);
                    if(position == 0) {
                        holderColor.textView.setText("✓");
                        holderColor.textView.setTextColor(Color.RED);
                    }
                    else {
                        holderColor.textView.setText("✓");
                        holderColor.textView.setTextColor(Color.WHITE);
                    }

                    Intent intent = new Intent();
                    intent.putExtra("index", position);
                    mContext.setResult(Activity.RESULT_OK, intent);
                    mContext.finish();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return arrayColor.length;
    }
}
