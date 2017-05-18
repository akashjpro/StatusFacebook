package com.status.aka.statusfacebook;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

public class ReviewStatusActivity extends AppCompatActivity {

    private ImageView imgstatus;
    private Button    btnFavorite, btnPostFb;
    private ShareDialog shareDialog;
    private  Bitmap bitmap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_status);
        addControls();
        addEvents();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            byte[] status = bundle.getByteArray("byteArray");
            bitmap = BitmapFactory.decodeByteArray(status, 0, status.length);
            imgstatus.setImageBitmap(bitmap);
        }
    }

    private void addEvents() {

        btnPostFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgstatus.getDrawable();
                Bitmap image = bitmapDrawable.getBitmap();

                SharePhoto photo = new SharePhoto.Builder()
                        .setBitmap(image
                        )
                        .build();

                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(photo)
                        .build();

                shareDialog.show(content, ShareDialog.Mode.AUTOMATIC);
            }


        });
    }

    private void addControls() {

        shareDialog = new ShareDialog(this);
        imgstatus   = (ImageView) findViewById(R.id.imgStatus);
        btnFavorite = (Button) findViewById(R.id.btnFavorite);
        btnPostFb   = (Button) findViewById(R.id.btnPostFb);
    }
}
