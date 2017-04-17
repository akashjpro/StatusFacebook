package com.status.aka.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.status.aka.statusfacebook.R;

import java.util.List;

/**
 * Created by Aka on 4/16/2017.
 */

public class FontAdapter extends ArrayAdapter<String> {

    Context mContext;
    int mLayout;
    List<String> mListFont;

    public FontAdapter(Context mContext, int mLayout, List<String> mListFont) {
        super(mContext, mLayout, mListFont);
        this.mContext = mContext;
        this.mLayout = mLayout;
        this.mListFont = mListFont;
    }


    private class ViewHolder{
        TextView txtfont;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = convertView;
        LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder = new ViewHolder();

        if(rowView == null){
            rowView = inflater.inflate(this.mLayout, null);
            holder.txtfont = (TextView) rowView.findViewById(R.id.textViewFont);
            rowView.setTag(holder);

        }else {
            holder = (ViewHolder) rowView.getTag();
        }

        Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), "font/" + mListFont.get(position));
        holder.txtfont.setTypeface(typeface);


        return rowView;
    }


}
