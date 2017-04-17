package com.status.aka.statusfacebook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.status.aka.adapter.FontAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class FontActivity extends AppCompatActivity {

    private TextView mTitleText;
    private ListView mListViewFont;
    private ArrayList<String> mListFont;
    private FontAdapter mFontAdapter;
    private String mStatusFont = "Status Font";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font);
        addControls();
        addEvents();
    }

    private void addEvents() {
        mListViewFont.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.putExtra("position", i);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        mTitleText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        mListViewFont = (ListView) findViewById(R.id.listFonts);
        mListFont     = new ArrayList<>();

        mFontAdapter = new FontAdapter(
                FontActivity.this,
                R.layout.item_font,
                mListFont);
        mListViewFont.setAdapter(mFontAdapter);



        try {
            //Get all data in assets folder
            AssetManager assetManager = getAssets();

            //get list font in font file
            String[] arrayFont = assetManager.list("font");

            mListFont.addAll(Arrays.asList(arrayFont));
            mFontAdapter.notifyDataSetChanged();

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
