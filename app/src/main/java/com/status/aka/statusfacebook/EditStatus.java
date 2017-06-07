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
    private String textStatus = "";
    

    private LinearLayout layoutFooter;
    private RelativeLayout layoutEdit;

    private LinearLayout layout, layoutChung, layoutEditText;

    private ArrayList<String> mListFont;
    private FirebaseAuth mAuth;
    int REQUEST_CODE_FONT = 123, REQUEST_CODE_COLOR = 111;
    int REQUEST_CODE_FONT_DESIGN = 124, REQUEST_CODE_COLOR_DESIGN = 125;
    int REQUEST_CODE_FONT_NAME = 126, REQUEST_CODE_COLOR_NAME = 127;
    private int POSITION_TITLE = 0;
    private int POSITION_DESIGN = 0;
    private int POSITION_NAME = 0;

    private int POSITION_DESIGN_COLOR = 0;
    private int POSITION_NAME_COLOR = 5;

    public static int BODER_TYPE_1 = 0;
    public static int BODER_TYPE_2 = 1;
    public static int BODER_TYPE_3 = 2;
    //set color & shap for boder layout
    public static int borderType = BODER_TYPE_1;
    public static String shapeLayout = "RECTANGLE";

    //set color & shap for text
    public static String shapeText   = "RECTANGLE";
    public static int textColor = 4;

    public static final String TRANSPARENT_COLOR = "#00000000";

    //define orientation color layout from left to right : 0
    //define orientation color layout from top to bottom : 1
    //set defaut orientation color layout from left to right : 0
    public static int orientationColorLayout = 0;

    String[] arrayColor;


    private boolean isShowFooter = true;
    public static boolean orientationVertical = true;
    public static int indexTextColor = 4 , indexBackground = 0 , indexStartColor = 1,
            indexCenterColor = 2, indexEndColor = 3;

    private boolean isStartOne = false;


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
                intent.putExtra("POSITION_DESIGN", -1);
                intent.putExtra("POSITION_NAME", -1);
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
                            case R.id.menuFont:
//                                swich_format = CODE_DESIGN;
//                                Toast.makeText(EditStatus.this, "Đã chọn", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(EditStatus.this, FontActivity.class);
                                intent.putExtra("POSITION_TITLE", -1);
                                intent.putExtra("POSITION_DESIGN", POSITION_DESIGN);
                                intent.putExtra("POSITION_NAME", -1);
                                startActivityForResult(intent, REQUEST_CODE_FONT_DESIGN);
                                break;
                            case R.id.menuColor:
                                Intent intentColor = new Intent(EditStatus.this, ListColor.class);
                                intentColor.putExtra("index", POSITION_DESIGN_COLOR);
                                startActivityForResult(intentColor, REQUEST_CODE_COLOR_DESIGN);
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
                            case R.id.menuFont:
                                Intent intent = new Intent(EditStatus.this, FontActivity.class);
                                intent.putExtra("POSITION_TITLE", -1);
                                intent.putExtra("POSITION_DESIGN", -1);
                                intent.putExtra("POSITION_NAME", POSITION_NAME);
                                startActivityForResult(intent, REQUEST_CODE_FONT_NAME);
                                break;
                            case R.id.menuColor:
                                Intent intentColor = new Intent(EditStatus.this, ListColor.class);
                                intentColor.putExtra("index", POSITION_NAME_COLOR);
                                startActivityForResult(intentColor, REQUEST_CODE_COLOR_NAME);
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

        arrayColor = getResources().getStringArray(R.array.aray_color);

        txtDesign.setTextColor(Color.parseColor(arrayColor[POSITION_DESIGN_COLOR]));
        txtName.setTextColor(Color.parseColor(arrayColor[POSITION_NAME_COLOR]));

        GradientDrawable gd1 = new GradientDrawable();
        gd1.setShape(GradientDrawable.RECTANGLE);

        if(!shapeText.equals("RECTANGLE")) {
                gd1.setCornerRadius(15);

        }
        gd1.setColor(Color.parseColor(arrayColor[5]));
        edtStatusText.setBackgroundColor(Color.parseColor(arrayColor[5]));
        layoutEditText.setBackground(gd1);
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

            int position = data.getIntExtra("position", -1);
            if(position != -1) {
                Typeface typeface = Typeface.createFromAsset(getAssets(), "font/" + mListFont.get(position));
                // Save status font, just this app được xài
                SharedPreferences preferences = getSharedPreferences(mStatusFont, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
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

        if(requestCode == REQUEST_CODE_FONT_DESIGN && resultCode == RESULT_OK && data != null){
            int position = data.getIntExtra("position", -1);
            if(position != -1) {
                POSITION_DESIGN = position;
                Typeface typeface = Typeface.createFromAsset(getAssets(), "font/" + mListFont.get(position));
                // Save status font, just this app được xài
                SharedPreferences preferences = getSharedPreferences(mStatusFont, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                txtDesign.setTypeface(typeface);
                editor.putString("FONT_TEXT_DESIGN", "font/" + mListFont.get(position));
                editor.putInt("POSITION_DESIGN", position);

                editor.commit();
            }
            if(position == -1) {
                SharedPreferences preferences = getSharedPreferences(mStatusFont, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("POSITION_DESIGN", -1);
                editor.commit();
            }
        }

        if(requestCode == REQUEST_CODE_COLOR_DESIGN && resultCode == RESULT_OK && data != null){
            int position = data.getIntExtra("index", -1);
            if(position != -1) {
                POSITION_DESIGN_COLOR = position;
                SharedPreferences preferences = getSharedPreferences(mStatusFont, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                txtDesign.setTextColor(Color.parseColor(arrayColor[position]));
                editor.putInt("POSITION_DESIGN_COLOR", position);

                editor.commit();
            }
        }


        if(requestCode == REQUEST_CODE_COLOR_NAME && resultCode == RESULT_OK && data != null){
            int position = data.getIntExtra("index", -1);
            if(position != -1) {
                POSITION_NAME_COLOR = position;
                SharedPreferences preferences = getSharedPreferences(mStatusFont, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                txtName.setTextColor(Color.parseColor(arrayColor[position]));
                editor.putInt("POSITION_NAME_COLOR", position);

                editor.commit();
            }
        }


        if(requestCode == REQUEST_CODE_FONT_NAME && resultCode == RESULT_OK && data != null){
            int position = data.getIntExtra("position", -1);
            if(position != -1) {
                POSITION_NAME = position;
                Typeface typeface = Typeface.createFromAsset(getAssets(), "font/" + mListFont.get(position));
                // Save status font, just this app được xài
                SharedPreferences preferences = getSharedPreferences(mStatusFont, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                txtName.setTypeface(typeface);
                editor.putString("FONT_TEXT_NAME", "font/" + mListFont.get(position));
                editor.putInt("POSITION_NAME", position);

                editor.commit();
            }else {
                SharedPreferences preferences = getSharedPreferences(mStatusFont, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("POSITION_NAME", -1);
                editor.commit();
            }
        }

        if (requestCode == REQUEST_CODE_COLOR && resultCode == RESULT_OK && data != null){
//            Bundle bundle = data.getBundleExtra("data");
//            if(bundle != null){
//                indexTextColor = bundle.getInt("indexTextColor", 0);
//                indexBackground = bundle.getInt("indexBackground", 0);
//                indexStartColor = bundle.getInt("indexStartColor", 0);
//                indexCenterColor = bundle.getInt("indexCenterColor", 0);
//                indexEndColor = bundle.getInt("indexEndColor", 0);
//                orientationVertical = bundle.getBoolean("orientationVertical", true);


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

                layout.setBackground(gd);



                GradientDrawable gd1 = new GradientDrawable();
                gd1.setShape(GradientDrawable.RECTANGLE);

                if (!shapeText.equals("RECTANGLE")) {
                    gd1.setCornerRadius(15);
                }

                if(borderType != BODER_TYPE_1) {
                    gd1.setColor(Color.parseColor(TRANSPARENT_COLOR));
                    edtStatusText.setBackgroundColor(Color.parseColor(TRANSPARENT_COLOR));

                }
                else {
                    gd1.setColor(Color.parseColor(arrayColor[indexBackground]));
                    edtStatusText.setBackgroundColor(Color.parseColor(arrayColor[indexBackground]));
                }
                layoutEditText.setBackground(gd1);


            //}
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(!isStartOne) {
            isStartOne = true;
            SharedPreferences preferences = getSharedPreferences(mStatusFont, MODE_PRIVATE);

            String font = preferences.getString("FONT_TEXT", "");
            int positon = preferences.getInt("POSITION_TITLE", -1);

            String font_design = preferences.getString("FONT_TEXT_DESIGN", "");
            int positon_design = preferences.getInt("POSITION_DESIGN", -1);

            int position_design_color = preferences.getInt("POSITION_DESIGN_COLOR", 4);
            txtDesign.setTextColor(Color.parseColor(arrayColor[position_design_color]));

            int position_name_color = preferences.getInt("POSITION_NAME_COLOR", 5);
            txtName.setTextColor(Color.parseColor(arrayColor[position_name_color]));

            String font_name = preferences.getString("FONT_TEXT_NAME", "");
            int positon_name = preferences.getInt("POSITION_NAME", -1);
            if (positon != -1) {
                POSITION_TITLE = positon;
                if (!font.isEmpty()) {
                    Typeface typeface = Typeface.createFromAsset(getAssets(), font);
                    edtStatusText.setTypeface(typeface);
                }
            } else {
                edtStatusText.setTypeface(null, Typeface.NORMAL);
            }

            if (positon_design != -1) {
                POSITION_DESIGN = positon_design;
                if (!font_design.isEmpty()) {
                    Typeface typeface = Typeface.createFromAsset(getAssets(), font_design);
                    txtDesign.setTypeface(typeface);
                }
            } else {
                txtDesign.setTypeface(null, Typeface.NORMAL);
            }

            if (positon_name != -1) {
                POSITION_NAME = positon_name;
                if (!font_name.isEmpty()) {
                    Typeface typeface = Typeface.createFromAsset(getAssets(), font_name);
                    txtName.setTypeface(typeface);
                }
            } else {
                txtName.setTypeface(null, Typeface.NORMAL);
            }

            SharedPreferences preferences1 = getSharedPreferences("colorST", MODE_PRIVATE);
            String text = preferences1.getString("textST", "");
            if (text.isEmpty()) {
                edtStatusText.setText("");
                edtStatusText.setHint("Nhập Status");
            } else {
                edtStatusText.setText(text);
            }


            indexTextColor = preferences1.getInt("indexTextColor", 4);
            indexBackground = preferences1.getInt("indexBackground", 0);
            indexStartColor = preferences1.getInt("indexStartColor", 1);
            indexCenterColor = preferences1.getInt("indexCenterColor", 2);
            indexEndColor = preferences1.getInt("indexEndColor", 3);
            orientationVertical = preferences1.getBoolean("orientationVertical", true);
            shapeText = preferences1.getString("shapeText", "RECTANGLE");
            borderType = preferences1.getInt("borderType", BODER_TYPE_1);

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

            layout.setBackground(gd);



            GradientDrawable gd1 = new GradientDrawable();
            gd1.setShape(GradientDrawable.RECTANGLE);

            if (!shapeText.equals("RECTANGLE")) {
                gd1.setCornerRadius(15);
            }

            if(borderType != BODER_TYPE_1) {
                gd1.setColor(Color.parseColor(TRANSPARENT_COLOR));
                edtStatusText.setBackgroundColor(Color.parseColor(TRANSPARENT_COLOR));

            }
            else {
                gd1.setColor(Color.parseColor(arrayColor[indexBackground]));
                edtStatusText.setBackgroundColor(Color.parseColor(arrayColor[indexBackground]));
            }
            layoutEditText.setBackground(gd1);

            edtStatusText.setTextColor(Color.parseColor(arrayColor[indexTextColor]));

        }
    }


//    public static Bitmap loadBitmapFromView(View v) {
//        Bitmap b = Bitmap.createBitmap( v.getLayoutParams().width, v.getLayoutParams().height, Bitmap.Config.ARGB_8888);
//        Canvas c = new Canvas(b);
//        v.layout(100, 100, v.getLayoutParams().width, v.getLayoutParams().height);
//        v.draw(c);
//        return b;
//    }


    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            textStatus = edtStatusText.getText().toString().trim();
            if(textStatus.isEmpty()){
                edtStatusText.setHint("Nhập status...");
                edtStatusText.setHintTextColor(Color.rgb(207, 207, 207));
                //edtStatusText.setTy(null, Typeface.NORMAL);
            }
        }
    };

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences preferences = getSharedPreferences("colorST", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        textStatus = edtStatusText.getText().toString();
        editor.putString("textST", textStatus);
        editor.putInt("indexTextColor", indexTextColor);
        editor.putInt("indexBackground", indexBackground);
        editor.putInt("indexStartColor", indexStartColor);
        editor.putInt("indexCenterColor", indexCenterColor);
        editor.putInt("indexEndColor", indexEndColor);
        editor.putBoolean("orientationVertical", orientationVertical);
        editor.putString("shapeText", shapeText);
        editor.putInt("borderType", borderType);
        editor.commit();
    }
}
