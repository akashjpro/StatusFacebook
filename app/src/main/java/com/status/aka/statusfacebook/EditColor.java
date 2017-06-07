package com.status.aka.statusfacebook;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.status.aka.statusfacebook.EditStatus.BODER_TYPE_1;
import static com.status.aka.statusfacebook.EditStatus.BODER_TYPE_2;
import static com.status.aka.statusfacebook.EditStatus.BODER_TYPE_3;
import static com.status.aka.statusfacebook.EditStatus.TRANSPARENT_COLOR;
import static com.status.aka.statusfacebook.EditStatus.borderType;
import static com.status.aka.statusfacebook.EditStatus.indexBackground;
import static com.status.aka.statusfacebook.EditStatus.indexCenterColor;
import static com.status.aka.statusfacebook.EditStatus.indexEndColor;
import static com.status.aka.statusfacebook.EditStatus.indexStartColor;
import static com.status.aka.statusfacebook.EditStatus.indexTextColor;
import static com.status.aka.statusfacebook.EditStatus.orientationVertical;
import static com.status.aka.statusfacebook.EditStatus.shapeText;

public class EditColor extends AppCompatActivity {

    TextView txtStatus;
    LinearLayout frameLinearStatus;
    Button buttonColorOritation, buttonOK;
    Button btnTextColor, btnBackgroundColor, btnStartColor, btnCenterColor, btnEndColor;
    private  String[] arrayColor;
    final int REQUEST_CODE_TEXT = 111, REQUEST_CODE_BACKGROUND = 112, REQUEST_CODE_START = 113,
              REQUEST_CODE_CENTER = 114,  REQUEST_CODE_END = 115;

//    public static int indexTextColor, indexBackground, indexStartColor,
//                      indexCenterColor, indexEndColor;

    private boolean isOneBackgroundColor = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_color);

        addControls();
        addEvents();
    }

    private void addEvents() {
        //Event change text Color
        btnTextColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditColor.this, ListColor.class);
                intent.putExtra("index", EditStatus.indexTextColor);
                startActivityForResult(intent, REQUEST_CODE_TEXT);
            }
        });

        //Event change background color
        btnBackgroundColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditColor.this, ListColor.class);
                intent.putExtra("index", EditStatus.indexBackground);
                startActivityForResult(intent, REQUEST_CODE_BACKGROUND);
            }
        });

        //Event change color of frame start
       btnStartColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditColor.this, ListColor.class);
                intent.putExtra("index", EditStatus.indexStartColor);
                startActivityForResult(intent, REQUEST_CODE_START);
            }
        });

        //Event change color of frame center
        btnCenterColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditColor.this, ListColor.class);
                intent.putExtra("index", EditStatus.indexCenterColor);
                startActivityForResult(intent, REQUEST_CODE_CENTER);
            }
        });

        //Event change color of frame end
        btnEndColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditColor.this, ListColor.class);
                intent.putExtra("index", EditStatus.indexEndColor);
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

                if (!EditStatus.shapeLayout.equals("RECTANGLE"))
                    gd.setCornerRadius(15);

                gd.setColors(new int[]{
                        Color.parseColor(arrayColor[EditStatus.indexStartColor]),
                        Color.parseColor(arrayColor[EditStatus.indexCenterColor]),
                        Color.parseColor(arrayColor[EditStatus.indexEndColor])
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
//                Bundle bundle = new Bundle();
//                bundle.putInt("indexTextColor", indexTextColor);
//                bundle.putInt("indexBackground", indexBackground);
//                bundle.putInt("indexStartColor", indexStartColor);
//                bundle.putInt("indexCenterColor", indexCenterColor);
//                bundle.putInt("indexEndColor", indexEndColor);
//                bundle.putBoolean("orientationVertical", orientationVertical);
//                intent.putExtra("data", bundle);
                setResult(RESULT_OK, intent);
               finish();
           }
       });



        findViewById(R.id.border).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isOneBackgroundColor = false;
                borderType = BODER_TYPE_1;
                EditStatus.shapeLayout = "RECTANGLE";
                shapeText   = "OVAL";

                btnBackgroundColor.setVisibility(View.VISIBLE);
                btnStartColor.setVisibility(View.VISIBLE);
                btnCenterColor.setVisibility(View.VISIBLE);
                btnEndColor.setVisibility(View.VISIBLE);
                buttonColorOritation.setVisibility(View.VISIBLE);


                //set boder text
                GradientDrawable gd = new GradientDrawable();

                gd.setShape(GradientDrawable.RECTANGLE);
                gd.setColor(Color.parseColor(arrayColor[indexBackground]));
                gd.setCornerRadius(15);
                txtStatus.setBackground(gd);


                //set boder layout
                GradientDrawable gd1 = new GradientDrawable();

                //Specify the shape of drawable
                gd1.setShape(GradientDrawable.RECTANGLE);


                // Create a 2 pixels width red colored border for drawable
                //gd.setStroke(2, Color.RED);
                gd1.setColors(new int[]{
                        Color.parseColor(arrayColor[EditStatus.indexStartColor]),
                        Color.parseColor(arrayColor[EditStatus.indexCenterColor]),
                        Color.parseColor(arrayColor[EditStatus.indexEndColor])
                });

                if (orientationVertical){
                    gd1.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);

                }else {
                    gd1.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
                }


                frameLinearStatus.setBackground(gd1);



            }
        });



        findViewById(R.id.border1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isOneBackgroundColor = false;
                borderType = BODER_TYPE_1;
                EditStatus.shapeLayout = "RECTANGLE";
                shapeText   = "RECTANGLE";
                btnBackgroundColor.setVisibility(View.VISIBLE);
                btnStartColor.setVisibility(View.VISIBLE);
                btnCenterColor.setVisibility(View.VISIBLE);
                btnEndColor.setVisibility(View.VISIBLE);
                buttonColorOritation.setVisibility(View.VISIBLE);


                //set boder text
                GradientDrawable gd = new GradientDrawable();

                gd.setShape(GradientDrawable.RECTANGLE);
                gd.setColor(Color.parseColor(arrayColor[indexBackground]));
                txtStatus.setBackground(gd);


                //set boder layout
                GradientDrawable gd1 = new GradientDrawable();

                //Specify the shape of drawable
                gd1.setShape(GradientDrawable.RECTANGLE);


                // Create a 2 pixels width red colored border for drawable
                //gd.setStroke(2, Color.RED);
                gd1.setColors(new int[]{
                        Color.parseColor(arrayColor[EditStatus.indexStartColor]),
                        Color.parseColor(arrayColor[EditStatus.indexCenterColor]),
                        Color.parseColor(arrayColor[EditStatus.indexEndColor])
                });
                if (orientationVertical){
                    gd1.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);

                }else {
                    gd1.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
                }
                frameLinearStatus.setBackground(gd1);



            }
        });

//        findViewById(R.id.border2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                isOneBackgroundColor = false;
//                EditStatus.borderType = 0;
//                EditStatus.shapeLayout = "OVAL";
//                shapeText   = "OVAL";
//                btnBackgroundColor.setVisibility(View.VISIBLE);
//                btnStartColor.setVisibility(View.VISIBLE);
//                btnCenterColor.setVisibility(View.VISIBLE);
//                btnEndColor.setVisibility(View.VISIBLE);
//                buttonColorOritation.setVisibility(View.VISIBLE);
//                GradientDrawable gd = new GradientDrawable();
//                gd.setShape(GradientDrawable.RECTANGLE);
//                gd.setColor(Color.parseColor(arrayColor[indexBackground]));
//                gd.setCornerRadius(15);
//                txtStatus.setBackground(gd);
//
//                //set boder layout
//                GradientDrawable gd1 = new GradientDrawable();
//
//                //Specify the shape of drawable
//                gd1.setShape(GradientDrawable.RECTANGLE);
//
//                gd1.setCornerRadius(15);
//                gd1.setColors(new int[]{
//                        Color.parseColor(arrayColor[EditStatus.indexStartColor]),
//                        Color.parseColor(arrayColor[EditStatus.indexCenterColor]),
//                        Color.parseColor(arrayColor[EditStatus.indexEndColor])
//                });
//                if (orientationVertical){
//                    gd1.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
//
//                }else {
//                    gd1.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
//                }
//
//                frameLinearStatus.setBackground(gd1);
//            }
//        });

        findViewById(R.id.border3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isOneBackgroundColor = false;
                borderType = BODER_TYPE_2;
                EditStatus.shapeLayout = "RECTANGLE";
                shapeText   = "OVAL";
                btnBackgroundColor.setVisibility(View.GONE);
                btnStartColor.setVisibility(View.VISIBLE);
                btnCenterColor.setVisibility(View.VISIBLE);
                btnEndColor.setVisibility(View.VISIBLE);
                buttonColorOritation.setVisibility(View.VISIBLE);
                txtStatus.setBackgroundColor(Color.parseColor(TRANSPARENT_COLOR));

                //set boder layout
                GradientDrawable gd1 = new GradientDrawable();

                //Specify the shape of drawable
                gd1.setShape(GradientDrawable.RECTANGLE);


                // Create a 2 pixels width red colored border for drawable
                //gd.setStroke(2, Color.RED);
                gd1.setColors(new int[]{
                        Color.parseColor(arrayColor[EditStatus.indexStartColor]),
                        Color.parseColor(arrayColor[EditStatus.indexCenterColor]),
                        Color.parseColor(arrayColor[EditStatus.indexEndColor])
                });
                if (orientationVertical){
                    gd1.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);

                }else {
                    gd1.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
                }
                frameLinearStatus.setBackground(gd1);
            }
        });

//        findViewById(R.id.border4).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                isOneBackgroundColor = false;
//                EditStatus.borderType = 1;
//                EditStatus.shapeLayout = "OVAL";
//                shapeText   = "OVAL";
//                btnBackgroundColor.setVisibility(View.GONE);
//                btnStartColor.setVisibility(View.VISIBLE);
//                btnCenterColor.setVisibility(View.VISIBLE);
//                btnEndColor.setVisibility(View.VISIBLE);
//                buttonColorOritation.setVisibility(View.VISIBLE);
//                txtStatus.setBackgroundColor(Color.parseColor(EditStatus.TRANSPARENT_COLOR));
//
//                //set boder layout
//                GradientDrawable gd1 = new GradientDrawable();
//
//                //Specify the shape of drawable
//                gd1.setShape(GradientDrawable.RECTANGLE);
//
//                gd1.setCornerRadius(15);
//                gd1.setColors(new int[]{
//                        Color.parseColor(arrayColor[EditStatus.indexStartColor]),
//                        Color.parseColor(arrayColor[EditStatus.indexCenterColor]),
//                        Color.parseColor(arrayColor[EditStatus.indexEndColor])
//                });
//                if (orientationVertical){
//                    gd1.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
//
//                }else {
//                    gd1.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
//                }
//                frameLinearStatus.setBackground(gd1);
//            }
//        });

        findViewById(R.id.border5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isOneBackgroundColor = true;
                indexStartColor  = indexBackground;
                indexCenterColor = indexBackground;
                indexEndColor    = indexBackground;
                borderType = BODER_TYPE_3;
                EditStatus.shapeLayout = "RECTANGLE";
                shapeText   = "OVAL";
                btnBackgroundColor.setVisibility(View.VISIBLE);
                btnStartColor.setVisibility(View.GONE);
                btnCenterColor.setVisibility(View.GONE);
                btnEndColor.setVisibility(View.GONE);
                buttonColorOritation.setVisibility(View.GONE);
                txtStatus.setBackgroundColor(Color.parseColor(TRANSPARENT_COLOR));
                //set boder layout
                GradientDrawable gd1 = new GradientDrawable();

                //Specify the shape of drawable
                gd1.setShape(GradientDrawable.RECTANGLE);


                // Create a 2 pixels width red colored border for drawable
                //gd.setStroke(2, Color.RED);
                gd1.setColors(new int[]{
                        Color.parseColor(arrayColor[indexStartColor]),
                        Color.parseColor(arrayColor[indexCenterColor]),
                        Color.parseColor(arrayColor[indexEndColor])
                });
                if (orientationVertical){
                    gd1.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);

                }else {
                    gd1.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
                }
                frameLinearStatus.setBackground(gd1);
            }
        });

//        findViewById(R.id.border6).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                isOneBackgroundColor = true;
//                indexStartColor  = indexBackground;
//                indexCenterColor = indexBackground;
//                indexEndColor    = indexBackground;
//                EditStatus.borderType = 1;
//                EditStatus.shapeLayout = "OVAL";
//                shapeText   = "OVAL";
//                btnBackgroundColor.setVisibility(View.VISIBLE);
//                btnStartColor.setVisibility(View.GONE);
//                btnCenterColor.setVisibility(View.GONE);
//                btnEndColor.setVisibility(View.GONE);
//                buttonColorOritation.setVisibility(View.GONE);
//                txtStatus.setBackgroundColor(Color.parseColor(EditStatus.TRANSPARENT_COLOR));
//                //set boder layout
//                GradientDrawable gd1 = new GradientDrawable();
//
//                //Specify the shape of drawable
//                gd1.setShape(GradientDrawable.RECTANGLE);
//
//                gd1.setCornerRadius(15);
//                gd1.setColors(new int[]{
//                        Color.parseColor(arrayColor[indexStartColor]),
//                        Color.parseColor(arrayColor[indexCenterColor]),
//                        Color.parseColor(arrayColor[indexEndColor])
//                });
//                if (orientationVertical){
//                    gd1.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
//
//                }else {
//                    gd1.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
//                }
//                frameLinearStatus.setBackground(gd1);
//            }
//        });
//






            // Initialize a new GradientDrawable
            GradientDrawable gd = new GradientDrawable();

            //Specify the shape of drawable
            gd.setShape(GradientDrawable.RECTANGLE);


            if(orientationVertical)
                 gd.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
            else
                gd.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);

            txtStatus.setTextColor(Color.parseColor(arrayColor[indexTextColor]));
            gd.setColors(new int[]{
                    Color.parseColor(arrayColor[indexStartColor]),
                    Color.parseColor(arrayColor[indexCenterColor]),
                    Color.parseColor(arrayColor[indexEndColor])
            });


            frameLinearStatus.setBackground(gd);

            GradientDrawable gd1 = new GradientDrawable();
            gd1.setShape(GradientDrawable.RECTANGLE);

            if(borderType != BODER_TYPE_1) {
                gd1.setColor(Color.parseColor(TRANSPARENT_COLOR));
               // txtStatus.setBackgroundColor(Color.parseColor(TRANSPARENT_COLOR));

            }
            else {
                gd1.setColor(Color.parseColor(arrayColor[indexBackground]));
               // txtStatus.setBackgroundColor(Color.parseColor(arrayColor[indexBackground]));
            }

            if (!shapeText.equals("RECTANGLE"))
                gd1.setCornerRadius(15);
            txtStatus.setBackground(gd1);



    }

    private void addControls() {
        txtStatus            = (TextView) findViewById(R.id.txtStatus);
        frameLinearStatus    = (LinearLayout) findViewById(R.id.layoutFrame);
        arrayColor           = getResources().getStringArray(R.array.aray_color);
        buttonColorOritation = (Button) findViewById(R.id.buttonColorOritation);
        buttonOK             = (Button) findViewById(R.id.buttonOK);

        btnTextColor         = (Button) findViewById(R.id.buttonTextColor);
        btnBackgroundColor   = (Button) findViewById(R.id.buttonBackgroundColor);
        btnStartColor        = (Button) findViewById(R.id.buttonColorStart);
        btnCenterColor       = (Button) findViewById(R.id.buttonColorCenter);
        btnEndColor          = (Button) findViewById(R.id.buttonColorEnd);
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


           if (!EditStatus.shapeLayout.equals("RECTANGLE"))
                 gd.setCornerRadius(15);


            if (requestCode == REQUEST_CODE_TEXT && resultCode == RESULT_OK && data != null) {
                txtStatus.setTextColor(Color.parseColor(arrayColor[position]));
                indexTextColor = position;
            }

            if (requestCode == REQUEST_CODE_BACKGROUND && resultCode == RESULT_OK && data != null) {
                indexBackground = position;
                if (isOneBackgroundColor){
                    indexStartColor  = indexBackground;
                    indexCenterColor = indexBackground;
                    indexEndColor    = indexBackground;
                }
                GradientDrawable gd1 = new GradientDrawable();
                gd1.setShape(GradientDrawable.RECTANGLE);

                if (orientationVertical){
                    gd1.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);

                }else {
                    gd1.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
                }

                if (!shapeText.equals("RECTANGLE"))
                    gd1.setCornerRadius(15);
                gd1.setColor(Color.parseColor(arrayColor[position]));
                txtStatus.setBackground(gd1);
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

            if (orientationVertical){
                gd.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);

            }else {
                gd.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
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
