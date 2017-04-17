package com.status.aka.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.status.aka.statusfacebook.R;

/**
 * Created by Aka on 3/29/2017.
 */

public class HeartStatus extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.heart_status, container, false);
        return view;
    }
}
