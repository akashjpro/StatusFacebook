package com.status.aka.statusfacebook;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

public class ReviewStatusActivity extends AppCompatActivity {

    private ImageView imgstatus;
    private Button    btnFavorite, btnPostFb;
    private ShareDialog shareDialog;
    private  Bitmap bitmap;
    private FirebaseStorage mStorage;
    private StorageReference storageReference;
    private final int REQUEST_CODE_FOLDER = 123;




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

        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Calendar calendar = Calendar.getInstance();
                final StorageReference mountainsRef = storageReference.child("image" + calendar.getTimeInMillis() + ".png");
                Toast.makeText(ReviewStatusActivity.this, "Da bam", Toast.LENGTH_SHORT).show();

                // Get the data from an ImageView as bytes
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] data = baos.toByteArray();

                UploadTask uploadTask = mountainsRef.putBytes(data);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(ReviewStatusActivity.this, "Upload Fail!!!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        @SuppressWarnings("VisibleForTests")
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        Toast.makeText(ReviewStatusActivity.this, "Upload Success : \n" + downloadUrl, Toast.LENGTH_SHORT).show();

                        //
                    }
                });
            }
        });

        imgstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_FOLDER);
            }
        });
    }

    private void addControls() {

        mStorage = FirebaseStorage.getInstance();
        storageReference = mStorage.getReferenceFromUrl("gs://statusfacebook-d762c.appspot.com");
        shareDialog = new ShareDialog(this);
        imgstatus   = (ImageView) findViewById(R.id.imgStatus);
        btnFavorite = (Button) findViewById(R.id.btnFavorite);
        btnPostFb   = (Button) findViewById(R.id.btnPostFb);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgstatus.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
