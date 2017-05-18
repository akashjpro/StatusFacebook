package com.status.aka.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.status.aka.Listener.ItemClickListener;
import com.status.aka.model.Font;
import com.status.aka.statusfacebook.R;

import java.util.List;

/**
 * Created by Aka on 4/21/2017.
 */

public class FontRecyclerview extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity context;
    private List<Font> mListFont;

    public class FontViewHoler extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textView;
        private ItemClickListener itemClickListener;
        public FontViewHoler(View itemView) {
            super(itemView);
            textView  = (TextView) itemView.findViewById(R.id.textViewFont);
            itemView.setOnClickListener(this);
        }

        public void setOnclickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), false);
        }
    }

    public FontRecyclerview(Activity context, List<Font> mListFont) {
        this.context = context;
        this.mListFont = mListFont;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_font, parent, false);
        return new FontViewHoler(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof  FontViewHoler){
            final FontViewHoler fontViewHoler = (FontViewHoler) holder;
            Font font = mListFont.get(position);
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), "font/" + mListFont.get(position).getName());
            fontViewHoler.textView.setTypeface(typeface);

            if (font.isCheck()){
                //fontViewHoler.textView.setBackgroundColor(Color.BLUE);
                fontViewHoler.textView.setTextColor(Color.WHITE);

                GradientDrawable gd = new GradientDrawable();

                //Specify the shape of drawable
                gd.setShape(GradientDrawable.RECTANGLE);
                gd.setColor(Color.BLUE);
               // gd.setCornerRadius(45.f);
                gd.setStroke(1, Color.BLACK);
                fontViewHoler.textView.setBackground(gd);
            }else {
                //fontViewHoler.textView.setBackgroundColor(Color.WHITE);
                fontViewHoler.textView.setTextColor(Color.BLACK);
                GradientDrawable gd = new GradientDrawable();

                //Specify the shape of drawable
                gd.setShape(GradientDrawable.RECTANGLE);
                gd.setColor(Color.WHITE);
               // gd.setCornerRadius(45.f);
                gd.setStroke(1, Color.BLACK);
                fontViewHoler.textView.setBackground(gd);
            }


           fontViewHoler.setOnclickListener(new ItemClickListener() {
               @Override
               public void onClick(View view, int position, boolean isLongClick) {
                   fontViewHoler.textView.setTextColor(Color.WHITE);
                   //fontViewHoler.textView.setBackgroundColor(Color.RED);

                   GradientDrawable gd = new GradientDrawable();

                   //Specify the shape of drawable
                   gd.setShape(GradientDrawable.RECTANGLE);
                   gd.setColor(Color.RED);
                   //gd.setCornerRadius(45.f);
                   gd.setStroke(1, Color.BLACK);
                   fontViewHoler.textView.setBackground(gd);
                   Intent intent = new Intent();
                   intent.putExtra("position", position);
                   context.setResult(Activity.RESULT_OK, intent);
                   context.finish();
               }
           });

        }
    }

    @Override
    public int getItemCount() {
        return mListFont.size();
    }
}
