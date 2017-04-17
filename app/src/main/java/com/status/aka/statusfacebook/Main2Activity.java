package com.status.aka.statusfacebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.status.aka.adapter.ViewPagerAdapter;
import com.status.aka.fragment.GeneralStatus;
import com.status.aka.fragment.HeartStatus;
import com.status.aka.fragment.MyStatus;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    public static TabLayout tabLayout;
    ViewPager viewPager;
    ArrayList<Integer> selectedImageResources ;
    ArrayList<Integer> unSelectedImageResources ;
    private Toolbar toolbar;
    FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundResource(R.color.colorPrimary);

        tabLayout = (TabLayout) findViewById(R.id.myTablayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getFragmentManager());
        selectedImageResources = new ArrayList<>();
        unSelectedImageResources = new ArrayList<>();

        selectedImageResources.add(R.drawable.home1_red);
        selectedImageResources.add(R.drawable.heart1_red);
        selectedImageResources.add(R.drawable.user1_red);


        unSelectedImageResources.add(R.drawable.home_normal);
        unSelectedImageResources.add(R.drawable.heart_normal);
        unSelectedImageResources.add(R.drawable.user_normal);

        pagerAdapter.addFragment_Title(new GeneralStatus(), "");
        pagerAdapter.addFragment_Title(new HeartStatus(), "");
        pagerAdapter.addFragment_Title(new MyStatus(), "");

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.home_normal).select();
        tabLayout.getTabAt(1).setIcon(R.drawable.heart_normal);
        tabLayout.getTabAt(2).setIcon(R.drawable.user_normal);

        tabLayout.getTabAt(0).setIcon(R.drawable.home1_red);
        toolbar.setTitle(R.string.home);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.setIcon(selectedImageResources.get(tab.getPosition()));
                switch (tab.getPosition()){
                    case 0:
                        toolbar.setTitle(R.string.home);
                        break;
                    case 1:
                        toolbar.setTitle(R.string.my_heart);
                        break;
                    case 2:
                        toolbar.setTitle(R.string.account);
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setIcon(unSelectedImageResources.get(tab.getPosition()));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this, EditStatus.class);
                startActivity(intent);
            }
        });

    }
}
