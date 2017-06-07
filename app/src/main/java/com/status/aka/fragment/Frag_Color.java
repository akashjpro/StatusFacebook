package com.status.aka.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.status.aka.adapter.ColorAdapter;
import com.status.aka.statusfacebook.R;

/**
 * Created by Aka on 5/17/2017.
 */

public class Frag_Color extends Fragment {
    RecyclerView mRecyclerView;
    String[] mArrayListColor;
    ColorAdapter mColorAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_list_color, container, false);
        mRecyclerView   = (RecyclerView) view.findViewById(R.id.recyclerviewColor);

        mArrayListColor = getResources().getStringArray(R.array.aray_color);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 4);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(layoutManager);
        mColorAdapter = new ColorAdapter(getActivity(), mArrayListColor);
        mRecyclerView.setAdapter(mColorAdapter);

        return view;
    }
}
