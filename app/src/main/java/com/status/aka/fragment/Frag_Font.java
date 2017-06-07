package com.status.aka.fragment;

import android.app.Fragment;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.status.aka.adapter.FontRecyclerview;
import com.status.aka.model.Font;
import com.status.aka.statusfacebook.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Aka on 5/17/2017.
 */

public class Frag_Font extends Fragment {

    private TextView mTitleText;
    private ArrayList<Font> mListFont;
    private RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    FontRecyclerview mFontRecyclerview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view    = inflater.inflate(R.layout.activity_font, container, false);
        mTitleText   = (TextView) view.findViewById(R.id.txtTitle);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mListFont    = new ArrayList<>();

        gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(gridLayoutManager);

        try {
            //Get all data in assets folder
            AssetManager assetManager = getActivity().getAssets();

            //get list font in font file
            String[] arrayFont = assetManager.list("font");

            ArrayList<String> listFont = new ArrayList<>();
            listFont.addAll(Arrays.asList(arrayFont));
            for (int i = 0 ; i< listFont.size(); i++){
                String name = listFont.get(i);
                boolean check = false;
                Font font = new Font(name, check);
                mListFont.add(font);
            }

        }catch(Exception ex){
            Log.e("Error_Font", ex.toString());
        }
        mFontRecyclerview = new FontRecyclerview(getActivity(), mListFont);
        recyclerView.setAdapter(mFontRecyclerview);



        return view;
    }
}
