package com.status.aka.statusfacebook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.status.aka.adapter.FontRecyclerview;
import com.status.aka.model.Font;

import java.util.ArrayList;
import java.util.Arrays;

public class FontActivity extends AppCompatActivity{

    private TextView mTitleText;
//    private ListView mListViewFont;
//    private ArrayList<String> mListFont;
//    private FontAdapter mFontAdapter;

    private ArrayList<Font> mListFont;
    private RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    FontRecyclerview mFontRecyclerview;
    private String mStatusFont = "Status Font";
    public static int mPosition ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font);
        addControls();
        addEvents();
    }

    private void addEvents() {



//        mListViewFont.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent();
//                intent.putExtra("position", i);
//                setResult(RESULT_OK, intent);
//                finish();
//            }
//        });

        mTitleText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTitleText.setBackgroundColor(Color.RED);
                mTitleText.setTextColor(Color.WHITE);
                Intent intent = new Intent();
                int i = -1;
                intent.putExtra("position", i);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void xuLyDoiFont(int position) {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/" + mListFont.get(position));
        mTitleText.setTypeface(typeface);

        //Save status font, just this app được xài
        SharedPreferences preferences = getSharedPreferences(mStatusFont, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("FONT_TEXT", "font/" + mListFont.get(position));
        editor.commit();
    }

    private void addControls() {
        mListFont     = new ArrayList<>();
        mTitleText = (TextView) findViewById(R.id.txtTitle);
      //  mListViewFont = (ListView) findViewById(R.id.listFonts);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mListFont     = new ArrayList<>();

        GridLayoutManager layoutManager = new GridLayoutManager(FontActivity.this, 1);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);

        mFontRecyclerview = new FontRecyclerview(FontActivity.this, mListFont);
        recyclerView.setAdapter(mFontRecyclerview);

//        mFontAdapter = new FontAdapter(
//                FontActivity.this,
//                R.layout.item_font,
//                mListFont);
//        mListViewFont.setAdapter(mFontAdapter);



        Bundle bundle = getIntent().getExtras();
        mPosition = bundle.getInt("POSITION_TITLE");

        try {
            //Get all data in assets folder
            AssetManager assetManager = getAssets();

            //get list font in font file
            String[] arrayFont = assetManager.list("font");

            ArrayList<String> listFont = new ArrayList<>();
            listFont.addAll(Arrays.asList(arrayFont));
            for (int i = 0 ; i< listFont.size(); i++){
                String name = listFont.get(i);
                boolean check = false;
                Font font = new Font(name, check);
                mListFont.add(font);
                mFontRecyclerview.notifyItemInserted(i);
            }

            mListFont.get(mPosition).setCheck(true);
            mFontRecyclerview.notifyItemChanged(mPosition);


            if ( mPosition != 0){
                recyclerView.scrollToPosition(mPosition);
            }

//            SharedPreferences preferences = getSharedPreferences(mStatusFont, MODE_PRIVATE);
//            String font = preferences.getString("FONT_TEXT", "");
//            if(!font.isEmpty()){
//                Typeface typeface = Typeface.createFromAsset(getAssets(), font);
//                mTitleText.setTypeface(typeface);
//            }
        }catch(Exception ex){
            Log.e("Error_Font", ex.toString());
        }
    }


}
