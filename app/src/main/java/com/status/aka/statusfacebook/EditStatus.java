package com.status.aka.statusfacebook;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class EditStatus extends AppCompatActivity {

    private EditText edtStatusText;
    private ImageView imgProfile;
    private TextView txtDesign, txtName;
    private Button  btnReview;
    private String mStatusFont = "Status Font";

    private CardView statusCardView;

    private ArrayList<String> mListFont;
    private FirebaseAuth mAuth;
    int REQUEST_CODE_FONT = 123, REQUEST_CODE_COLOR = 111;
    private final int CODE_TITLE = 0, CODE_DESIGN = 1, CODE_NAME = 2;
    private int swich_format = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_status);
        addControls();
        addEvents();

    }

    private void addEvents() {

        try {
            //Get all data in assets folder
            AssetManager assetManager = getAssets();

            //get list font in font file
            String[] arrayFont = new String[0];
            arrayFont = assetManager.list("font");
            mListFont.addAll(Arrays.asList(arrayFont));
            Typeface typeface = Typeface.createFromAsset(getAssets(), "font/" + mListFont.get(25));
            edtStatusText.setTypeface(typeface);

            Typeface typeface1 = Typeface.createFromAsset(getAssets(), "font/" + mListFont.get(26));
            txtDesign.setTypeface(typeface);
        } catch (IOException e) {
            e.printStackTrace();
        }


        String s = "Bồ đề vốn không cây,\n" +
                   "Gương sáng cũng chẳng đài,\n" +
                   "Xưa nay không một vật,\n" +
                   "Nơi nào dính bụi trần.";

        edtStatusText.setText(s);
        edtStatusText.setSelection(edtStatusText.getText().length());
        //get User
        FirebaseUser user = mAuth.getCurrentUser();
        //get name
        String name = user.getDisplayName();
        txtName.setText(name);
        //get url profile
        String url = user.getPhotoUrl().toString();
        Picasso.with(EditStatus.this).load(url).placeholder(R.drawable.status_fb).into(imgProfile);

        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                edtStatusText.clearFocus();
                edtStatusText.setFocusable(false);
                statusCardView.setDrawingCacheEnabled(false);
                statusCardView.setDrawingCacheEnabled(true);
                Bitmap bitmap = Bitmap.createBitmap(statusCardView.getDrawingCache());

                //chuyen bimap thanh byte
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                Intent intent = new Intent(EditStatus.this, ReviewStatusActivity.class);
                intent.putExtra("byteArray", byteArray);
                startActivity(intent);
            }
        });

        findViewById(R.id.action_font).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditStatus.this, FontActivity.class);
                startActivityForResult(intent, REQUEST_CODE_FONT);
            }
        });

        findViewById(R.id.action_color).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        findViewById(R.id.action_bold).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        findViewById(R.id.action_italic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        findViewById(R.id.action_underline).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        findViewById(R.id.action_align_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        findViewById(R.id.action_align_center).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        findViewById(R.id.action_align_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        txtDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupMenu popupMenu = new PopupMenu(EditStatus.this, txtDesign);
                popupMenu.getMenuInflater().inflate(R.menu.menu_design, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menuFormat:
                                swich_format = CODE_DESIGN;
                                Toast.makeText(EditStatus.this, "Đã chọn", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.menuDelete:
                                break;
                        }
                        return false;
                    }
                });

                popupMenu.show();
            }
        });

        txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupMenu popupMenu = new PopupMenu(EditStatus.this, txtDesign);
                popupMenu.getMenuInflater().inflate(R.menu.menu_design, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menuFormat:
                                swich_format = CODE_NAME;
                                Toast.makeText(EditStatus.this, "Đã chọn", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.menuDelete:
                                break;
                        }
                        return false;
                    }
                });

                popupMenu.show();
            }
        });


    }


    private void addControls() {
        mListFont     = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        edtStatusText = (EditText) findViewById(R.id.statusText);
        txtDesign     = (TextView) findViewById(R.id.txtDesign);
        txtName       = (TextView) findViewById(R.id.txtName);
        imgProfile    = (ImageView) findViewById(R.id.imgProfile);
        btnReview     = (Button) findViewById(R.id.btnReview);
        statusCardView = (CardView) findViewById(R.id.statusCardView);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        edtStatusText.setFocusable(true);
        edtStatusText.requestFocus();
        edtStatusText.setFocusableInTouchMode(true);
        edtStatusText.setSelection(edtStatusText.getText().length());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_FONT && resultCode == RESULT_OK && data != null){

            int position = data.getIntExtra("position", 0);
            if(position != -1) {
                Typeface typeface = Typeface.createFromAsset(getAssets(), "font/" + mListFont.get(position));
                switch (swich_format){
                    case CODE_TITLE:
                        edtStatusText.setTypeface(typeface);
                        break;
                    case CODE_DESIGN:
                        txtDesign.setTypeface(typeface);
                        break;
                    case CODE_NAME:
                        txtName.setTypeface(typeface);
                        break;
                }
            }else {
                edtStatusText.setTypeface(null, Typeface.NORMAL);
            }

            //Save status font, just this app được xài
//            SharedPreferences preferences = getSharedPreferences(mStatusFont, MODE_PRIVATE);
//            SharedPreferences.Editor editor = preferences.edit();
//            editor.putString("FONT_TEXT", "font/" + mListFont.get(position));
//            editor.commit();
        }

        if (requestCode == REQUEST_CODE_COLOR && resultCode == RESULT_OK && data != null){

        }
    }
}
