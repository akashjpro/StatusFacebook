package com.status.aka.statusfacebook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class EditStatus extends AppCompatActivity {

    private EditText edtStatusText;
    private ImageView imgProfile, imgDelete;
    private TextView txtDesign, txtName, txtLine, txtShow_footer;
    private Button  btnReview;
    private String mStatusFont = "Status Font";
    private String textStatus = "saveText";
    

    private LinearLayout layoutFooter;
    private RelativeLayout layoutEdit;

    private LinearLayout layout, layoutChung, layoutEditText;

    private ArrayList<String> mListFont;
    private FirebaseAuth mAuth;
    int REQUEST_CODE_FONT = 123, REQUEST_CODE_COLOR = 111;
    private final int CODE_TITLE = 0, CODE_DESIGN = 1, CODE_NAME = 2;
    private int swich_format = 0;
    private int POSITION_TITLE = 0;

    private boolean isShowFooter = true, orientationVertical = true;
    public int indexTextColor = 4 , indexBackground = 0 , indexStartColor = 1,
            indexCenterColor = 2, indexEndColor = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_status);
        addControls();
        addEvents();

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-8707500979920735/2957491208");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

    }

    private void addEvents() {

        //Hide footer
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutFooter.setVisibility(View.GONE);
                txtLine.setVisibility(View.GONE);
                imgDelete.setVisibility(View.GONE);
                txtShow_footer.setVisibility(View.VISIBLE);
                isShowFooter = false;
            }
        });

        // Show footer
        txtShow_footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutFooter.setVisibility(View.VISIBLE);
                txtLine.setVisibility(View.VISIBLE);
                txtShow_footer.setVisibility(View.GONE);
                imgDelete.setVisibility(View.VISIBLE);
                isShowFooter = true;
            }
        });

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


        String s =  "Xuân khứ bách hoa lạc,\n" +
                    "Xuân đáo bách hoa khai.\n" +
                    "Sự trục nhãn tiền quá,\n" +
                    "Lão tùng đầu thượng lai.\n" +
                    "Mạc vị xuân tàn hoa lạc tận,\n" +
                    "Đình tiền tạc dạ nhất chi mai.";

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

        // Initialize a new GradientDrawable
        GradientDrawable  gd = new GradientDrawable();

        //Specify the shape of drawable
        gd.setShape(GradientDrawable.RECTANGLE);

        // Set fill colors of drawable
        gd.setColors(new int[] {
                Color.GREEN,
                Color.YELLOW,
                Color.CYAN
        });

        gd.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);

        // Create a 2 pixels width red colored border for drawable
        //gd.setStroke(2, Color.RED);
        //gd.setStroke(4, Color.RED);

        //Made the boder rounded
        //gd.setCornerRadius(15); // border corner radius

        // finally, apply the GradientDrawable as background
        layout.setBackground(gd);
        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(isShowFooter){
//                    imgDelete.setVisibility(View.GONE);
//                }else {
//                    txtShow_footer.setVisibility(View.GONE);
//                }

                layoutEdit.setVisibility(View.INVISIBLE);

                edtStatusText.setFocusable(false);
                layoutChung.setDrawingCacheEnabled(false);
                layoutChung.setDrawingCacheEnabled(true);
//                Bitmap bitmap = Bitmap.createBitmap(layout.getDrawingCache());

//                layout.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.EXACTLY),
//                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.EXACTLY));
//                layout.measure(View.MeasureSpec.makeMeasureSpec(
//                        layout.getLayoutParams().width, View.MeasureSpec.EXACTLY),
//                        View.MeasureSpec.makeMeasureSpec(
//                                layout.getLayoutParams().height,
//                                View.MeasureSpec.EXACTLY));
//                layout.layout(0, 0, 480, 854);

                layoutChung.buildDrawingCache(true);
                Bitmap bitmap = Bitmap.createBitmap(layoutChung.getWidth(), layoutChung.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

                Canvas canvas = new Canvas(bitmap);
                layoutChung.draw(canvas);
                //chuyen bimap thanh byte
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.WEBP, 100, stream);
                byte[] byteArray = stream.toByteArray();


                layoutEdit.setVisibility(View.VISIBLE);

                Intent intent = new Intent(EditStatus.this, ReviewStatusActivity.class);
                intent.putExtra("byteArray", byteArray);
                startActivity(intent);
            }
        });

        findViewById(R.id.action_font).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditStatus.this, FontActivity.class);
                intent.putExtra("POSITION_TITLE", POSITION_TITLE);
                startActivityForResult(intent, REQUEST_CODE_FONT);
            }
        });

        findViewById(R.id.action_color).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditStatus.this, EditColor.class);
                Bundle bundle = new Bundle();
                bundle.putInt("indexTextColor", indexTextColor);
                bundle.putInt("indexBackground", indexBackground);
                bundle.putInt("indexStartColor", indexStartColor);
                bundle.putInt("indexCenterColor", indexCenterColor);
                bundle.putInt("indexEndColor", indexEndColor);
                bundle.putBoolean("orientationVertical", orientationVertical);
                intent.putExtra("data", bundle);
                startActivityForResult(intent, REQUEST_CODE_COLOR);
            }
        });

        findViewById(R.id.action_bold).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               edtStatusText.setTypeface(edtStatusText.getTypeface(), Typeface.BOLD);
            }
        });

        findViewById(R.id.action_italic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtStatusText.setTypeface(edtStatusText.getTypeface(), Typeface.ITALIC);
            }
        });

        findViewById(R.id.action_underline).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtStatusText.setPaintFlags(edtStatusText.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
            }
        });

        findViewById(R.id.action_normal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 edtStatusText.setTypeface(null, Typeface.NORMAL);
                 edtStatusText.setPaintFlags(edtStatusText.getPaintFlags() & Paint.STRIKE_THRU_TEXT_FLAG);
            }
        });

        findViewById(R.id.action_align_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //edtStatusText.setGravity(Gravity.LEFT);
                //layoutFooter.setGravity(Gravity.LEFT);
//                edtStatusText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                edtStatusText.setGravity(Gravity.LEFT);
            }
        });

        findViewById(R.id.action_align_center).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtStatusText.setGravity(Gravity.CENTER);
               // layoutFooter.setGravity(Gravity.CENTER);

            }
        });

        findViewById(R.id.action_align_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtStatusText.setGravity(Gravity.RIGHT);
                //layoutFooter.setGravity(Gravity.RIGHT);
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
//                                swich_format = CODE_DESIGN;
//                                Toast.makeText(EditStatus.this, "Đã chọn", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(EditStatus.this, FontAndColor.class);
                                startActivity(intent);
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
                                Intent intent = new Intent(EditStatus.this, FontAndColor.class);
                                startActivity(intent);
                                break;
                            case R.id.menuDelete:
                                break;
                        }
                        return false;
                    }
                });

                popupMenu.show(                                                                                        );
            }
        });

        edtStatusText.addTextChangedListener(textWatcher);



    }


    private void addControls() {
        mListFont      = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        edtStatusText  = (EditText) findViewById(R.id.statusText);
        txtDesign      = (TextView) findViewById(R.id.txtDesign);
        txtName        = (TextView) findViewById(R.id.txtName);
        txtLine        = (TextView) findViewById(R.id.line);
        txtShow_footer = (TextView) findViewById(R.id.show_footer);
        imgProfile     = (ImageView) findViewById(R.id.imgProfile);
        imgDelete      = (ImageView) findViewById(R.id.hide_footer);
        btnReview      = (Button) findViewById(R.id.btnReview);
        layout         = (LinearLayout) findViewById(R.id.layout);
        layoutChung    = (LinearLayout) findViewById(R.id.layoutChung);
        layoutFooter   = (LinearLayout) findViewById(R.id.layoutFooter);
        layoutEdit     = (RelativeLayout) findViewById(R.id.layoutEdit);
        layoutEditText = (LinearLayout) findViewById(R.id.layoutEditText);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        edtStatusText.setFocusable(true);
        edtStatusText.requestFocus();
        edtStatusText.setFocusableInTouchMode(true);
        edtStatusText.setSelection(edtStatusText.getText().length());
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_FONT && resultCode == RESULT_OK && data != null){

            int position = data.getIntExtra("position", -1);
            if(position != -1) {
                Typeface typeface = Typeface.createFromAsset(getAssets(), "font/" + mListFont.get(position));
                // Save status font, just this app được xài
                SharedPreferences preferences = getSharedPreferences(mStatusFont, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
//                switch (swich_format){
//                    case CODE_TITLE:
//                        edtStatusText.setTypeface(typeface);
//                        editor.putString("FONT_TEXT", "font/" + mListFont.get(position));
//                        break;
//                    case CODE_DESIGN:
//                        txtDesign.setTypeface(typeface);
//                        editor.putString("FONT_DESIGN", "font/" + mListFont.get(position));
//                        break;
//                    case CODE_NAME:
//                        txtName.setTypeface(typeface);
//                        editor.putString("FONT_NAME", "font/" + mListFont.get(position));
//                        break;
//
//                }
                edtStatusText.setTypeface(typeface);
                editor.putString("FONT_TEXT", "font/" + mListFont.get(position));
                editor.putInt("POSITION_TITLE", position);

                editor.commit();
            }

            if(position == -1) {
                SharedPreferences preferences = getSharedPreferences(mStatusFont, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("POSITION_TITLE", -1);
                editor.commit();
            }


        }

        if (requestCode == REQUEST_CODE_COLOR && resultCode == RESULT_OK && data != null){
            Bundle bundle = data.getBundleExtra("data");
            if(bundle != null){
                indexTextColor = bundle.getInt("indexTextColor", 0);
                indexBackground = bundle.getInt("indexBackground", 0);
                indexStartColor = bundle.getInt("indexStartColor", 0);
                indexCenterColor = bundle.getInt("indexCenterColor", 0);
                indexEndColor = bundle.getInt("indexEndColor", 0);
                orientationVertical = bundle.getBoolean("orientationVertical", true);

                String[] arrayColor = getResources().getStringArray(R.array.aray_color);

                edtStatusText.setTextColor(Color.parseColor(arrayColor[indexTextColor]));
                layoutEditText.setBackgroundColor(Color.parseColor(arrayColor[indexBackground]));
                edtStatusText.setBackgroundColor(Color.parseColor(arrayColor[indexBackground]));

                // Initialize a new GradientDrawable
                GradientDrawable  gd = new GradientDrawable();

                //Specify the shape of drawable
                gd.setShape(GradientDrawable.RECTANGLE);

                // Set fill colors of drawable
                gd.setColors(new int[] {
                        Color.parseColor(arrayColor[indexStartColor]),
                        Color.parseColor(arrayColor[indexCenterColor]),
                        Color.parseColor(arrayColor[indexEndColor])
                });

                if(orientationVertical)
                    gd.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
                else
                    gd.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);

                // Create a 2 pixels width red colored border for drawable
                //gd.setStroke(2, Color.RED);
             //   gd.setStroke(4, Color.RED);

                //Made the boder rounded
                //gd.setCornerRadius(15); // border corner radius

                // finally, apply the GradientDrawable as background
                layout.setBackground(gd);

            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
        SharedPreferences preferences = getSharedPreferences(mStatusFont, MODE_PRIVATE);
        String font = preferences.getString("FONT_TEXT", "");

        int positon = preferences.getInt("POSITION_TITLE", -1);
        if(positon != -1) {
            POSITION_TITLE = positon;

            String fontDesign = preferences.getString("FONT_DESIGN", "");
            String fontName = preferences.getString("FONT_NAME", "");
            if (!font.isEmpty()) {
                Typeface typeface = Typeface.createFromAsset(getAssets(), font);
                edtStatusText.setTypeface(typeface);
            }

            if (!fontDesign.isEmpty()) {
                Typeface typeface = Typeface.createFromAsset(getAssets(), fontDesign);
                txtDesign.setTypeface(typeface);
            }

            if (!fontName.isEmpty()) {
                Typeface typeface = Typeface.createFromAsset(getAssets(), fontName);
                txtName.setTypeface(typeface);
            }
        }else {
            edtStatusText.setTypeface(null, Typeface.NORMAL);
        }

        SharedPreferences preferencesText = getSharedPreferences(textStatus, MODE_PRIVATE);
        String text = preferencesText.getString("textST", "");
        if (text.isEmpty()){
            edtStatusText.setText("");
            edtStatusText.setHint("Nhập Status");
        }else {
            edtStatusText.setText(text);
        }

    }


    public static Bitmap loadBitmapFromView(View v) {
        Bitmap b = Bitmap.createBitmap( v.getLayoutParams().width, v.getLayoutParams().height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(100, 100, v.getLayoutParams().width, v.getLayoutParams().height);
        v.draw(c);
        return b;
    }


    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String s = edtStatusText.getText().toString().trim();
            if(s.isEmpty()){
                edtStatusText.setHint("Nhập status...");
                edtStatusText.setHintTextColor(Color.rgb(207, 207, 207));
                //edtStatusText.setTy(null, Typeface.NORMAL);
            }
        }
    };

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences preferences = getSharedPreferences(textStatus, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("textST", edtStatusText.getText().toString());
        editor.commit();
    }
}
