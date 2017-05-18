package com.status.aka.statusfacebook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.status.aka.adapter.ColorAdapter;

public class ListColor extends AppCompatActivity {

    RecyclerView mRecyclerView;
    String[] mArrayListColor;
    ColorAdapter mColorAdapter;
    public static int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_color);

        mRecyclerView   = (RecyclerView) findViewById(R.id.recyclerviewColor);

        mArrayListColor = getResources().getStringArray(R.array.aray_color);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(layoutManager);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            position = bundle.getInt("index", 0);
        }


        mColorAdapter = new ColorAdapter(this, mArrayListColor);
        mRecyclerView.setAdapter(mColorAdapter);
        mRecyclerView.scrollToPosition(position);

    }

}
