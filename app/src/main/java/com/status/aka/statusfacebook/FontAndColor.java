package com.status.aka.statusfacebook;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.status.aka.adapter.ViewPagerAdapter;
import com.status.aka.fragment.Frag_Color;
import com.status.aka.fragment.Frag_Font;

public class FontAndColor extends AppCompatActivity {
    ViewPager viewPager;
    public static TabLayout tabLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font_and_color);
        tabLayout = (TabLayout) findViewById(R.id.myTablayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getFragmentManager());

        pagerAdapter.addFragment_Title(new Frag_Font(), "Phông chữ");
        pagerAdapter.addFragment_Title(new Frag_Color(), "Màu chữ");

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).select();

//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                tab.setIcon(selectedImageResources.get(tab.getPosition()));
//                switch (tab.getPosition()){
//                    case 0:
//                        toolbar.setTitle(R.string.home);
//                        break;
//                    case 1:
//                        toolbar.setTitle(R.string.my_heart);
//                        break;
//                    case 2:
//                        toolbar.setTitle(R.string.account);
//                        break;
//                }
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                tab.setIcon(unSelectedImageResources.get(tab.getPosition()));
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

    }
}
