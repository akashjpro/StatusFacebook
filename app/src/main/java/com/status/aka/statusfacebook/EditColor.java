package com.status.aka.statusfacebook;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class EditColor extends AppCompatActivity {

    EditText edtStatus;
    LinearLayout frameLinearStatus;
    Button buttonColorOritation, buttonOK;
    private  String[] arrayColor;
    String stareColor = "#00FF00", centerColor = "#FFFF00", endColor = "#00faf2";
    final int REQUEST_CODE_TEXT = 111, REQUEST_CODE_BACKGROUND = 112, REQUEST_CODE_START = 113,
              REQUEST_CODE_CENTER = 114,  REQUEST_CODE_END = 115;

    public static int indexTextColor, indexBackground, indexStartColor,
                      indexCenterColor, indexEndColor;

    private boolean orientationVertical;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_color);

        addControls();
        addEvents();
    }

    private void addEvents() {
        //Event change text Color
        findViewById(R.id.buttonTextColor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditColor.this, ListColor.class);
                intent.putExtra("index", indexTextColor);
                startActivityForResult(intent, REQUEST_CODE_TEXT);
            }
        });

        //Event change background color
        findViewById(R.id.buttonBackgroundColor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditColor.this, ListColor.class);
                intent.putExtra("index", indexBackground);
                startActivityForResult(intent, REQUEST_CODE_BACKGROUND);
            }
        });

        //Event change color of frame start
        findViewById(R.id.buttonColorStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditColor.this, ListColor.class);
                intent.putExtra("index", indexStartColor);
                startActivityForResult(intent, REQUEST_CODE_START);
            }
        });

        //Event change color of frame center
        findViewById(R.id.buttonColorCenter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditColor.this, ListColor.class);
                intent.putExtra("index", indexCenterColor);
                startActivityForResult(intent, REQUEST_CODE_CENTER);
            }
        });

        //Event change color of frame end
        findViewById(R.id.buttonColorEnd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditColor.this, ListColor.class);
                intent.putExtra("index", indexEndColor);
                startActivityForResult(intent, REQUEST_CODE_END);
            }
        });

        //Event change orientation color
        buttonColorOritation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Initialize a new GradientDrawable
                GradientDrawable gd = new GradientDrawable();

                //Specify the shape of drawable
                gd.setShape(GradientDrawable.RECTANGLE);


                // Create a 2 pixels width red colored border for drawable
                //gd.setStroke(2, Color.RED);
                gd.setStroke(4, Color.RED);
                gd.setColors(new int[]{
                        Color.parseColor(arrayColor[indexStartColor]),
                        Color.parseColor(arrayColor[indexCenterColor]),
                        Color.parseColor(arrayColor[indexEndColor])
                });

                if (orientationVertical){
                    orientationVertical = false;
                    buttonColorOritation.setText("Màu khung từ trái qua phải");
                    gd.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);

                }else {
                    orientationVertical = true;
                    buttonColorOritation.setText("Màu khung từ trên xuống dưới");
                    gd.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
                }

                frameLinearStatus.setBackground(gd);
            }



        });

       buttonOK.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("indexTextColor", indexTextColor);
                bundle.putInt("indexBackground", indexBackground);
                bundle.putInt("indexStartColor", indexStartColor);
                bundle.putInt("indexCenterColor", indexCenterColor);
                bundle.putInt("indexEndColor", indexEndColor);
                bundle.putBoolean("orientationVertical", orientationVertical);
                intent.putExtra("data", bundle);
                setResult(RESULT_OK, intent);
               finish();
           }
       });

        Bundle bundle = getIntent().getBundleExtra("data");
        if(bundle != null){
            indexTextColor      = bundle.getInt("indexTextColor", 0);
            indexBackground     = bundle.getInt("indexBackground", 0);
            indexStartColor     = bundle.getInt("indexStartColor", 0);
            indexCenterColor    = bundle.getInt("indexCenterColor", 0);
            indexEndColor       = bundle.getInt("indexEndColor", 0);
            orientationVertical = bundle.getBoolean("orientationVertical", true);

            // Initialize a new GradientDrawable
            GradientDrawable gd = new GradientDrawable();

            //Specify the shape of drawable
            gd.setShape(GradientDrawable.RECTANGLE);


            if(orientationVertical)
                 gd.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
            else
                gd.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);

            // Create a 2 pixels width red colored border for drawable
            //gd.setStroke(2, Color.RED);
            gd.setStroke(4, Color.RED);
            edtStatus.setTextColor(Color.parseColor(arrayColor[indexTextColor]));
            edtStatus.setBackgroundColor(Color.parseColor(arrayColor[indexBackground]));
            gd.setColors(new int[]{
                    Color.parseColor(arrayColor[indexStartColor]),
                    Color.parseColor(arrayColor[indexCenterColor]),
                    Color.parseColor(arrayColor[indexEndColor])
            });

            frameLinearStatus.setBackground(gd);

        }

    }

    private void addControls() {
        edtStatus            = (EditText) findViewById(R.id.editText);
        frameLinearStatus    = (LinearLayout) findViewById(R.id.layoutFrame);
        arrayColor           = getResources().getStringArray(R.array.aray_color);
        buttonColorOritation = (Button) findViewById(R.id.buttonColorOritation);
        buttonOK             = (Button) findViewById(R.id.buttonOK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(data != null) {

            int position = data.getIntExtra("index", 0);
            // Initialize a new GradientDrawable
            GradientDrawable gd = new GradientDrawable();

            //Specify the shape of drawable
            gd.setShape(GradientDrawable.RECTANGLE);


            gd.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);

            // Create a 2 pixels width red colored border for drawable
            //gd.setStroke(2, Color.RED);
            gd.setStroke(4, Color.RED);


            if (requestCode == REQUEST_CODE_TEXT && resultCode == RESULT_OK && data != null) {
                edtStatus.setTextColor(Color.parseColor(arrayColor[position]));
                indexTextColor = position;
            }

            if (requestCode == REQUEST_CODE_BACKGROUND && resultCode == RESULT_OK && data != null) {
                edtStatus.setBackgroundColor(Color.parseColor(arrayColor[position]));
                indexBackground = position;
            }

            if (requestCode == REQUEST_CODE_START && resultCode == RESULT_OK && data != null) {
                // Set fill colors of drawable
                indexStartColor = position;
            }

            if (requestCode == REQUEST_CODE_CENTER && resultCode == RESULT_OK && data != null) {

                // Set fill colors of drawable
                indexCenterColor = position;

            }

            if (requestCode == REQUEST_CODE_END && resultCode == RESULT_OK && data != null) {

                // Set fill colors of drawable
                indexEndColor = position;
            }

            gd.setColors(new int[]{
                    Color.parseColor(arrayColor[indexStartColor]),
                    Color.parseColor(arrayColor[indexCenterColor]),
                    Color.parseColor(arrayColor[indexEndColor])
            });
            frameLinearStatus.setBackground(gd);

        }

    }
}
