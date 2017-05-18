package com.status.aka.statusfacebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.status.aka.model.User;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private LoginButton loginButton;
    private Button btnLogout;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private DatabaseReference mMyRef;
    private CallbackManager callbackManager;
    private ShareDialog shareDialog;

    private static String mGender   = "";
    private static String mBirthday = "";
    private static String mLocation = "";

    private static final  String TAG = "MAIN_ACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-8707500979920735/2957491208");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
        .build();
        mAdView.loadAd(adRequest);


        adđControls();


        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
//                if (user != null){
//                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
//                    startActivity(intent);
//                    finish();
//                }else {
//                    Toast.makeText(MainActivity.this, "Error!!!", Toast.LENGTH_SHORT).show();
//                }
            }
        };
        loginButton.setReadPermissions("email", "public_profile", "user_birthday",
                "user_location", "user_hometown");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {
                                    mGender   = object.getString("gender");
                                    mBirthday = object.getString("birthday");
                                    mLocation = object.getJSONObject("location").getString("name");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                Toast.makeText(
                                        MainActivity.this,
                                        "thanh công!!!",
                                        Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                );

                Bundle parameters = new Bundle();
                // Cac truong truy xuat lay thong tin from facebook
                parameters.putString("fields", "id,name,email,gender,birthday,location");
                //get day du hon
                // parameters.putString("fields", "id,name,email,gender,birthday,hometown,locale,location");
                request.setParameters(parameters);
                request.executeAsync();


            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "that bai", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(MainActivity.this, "lổi!!!", Toast.LENGTH_SHORT).show();
            }});

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginButton.performClick();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    private void adđControls() {
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
        btnLogout = (Button) findViewById(R.id.buttonLgout);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        mAuth       = FirebaseAuth.getInstance();
        mMyRef      = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthStateListener!= null){
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
                        Toast.makeText(MainActivity.this, "Success !!!!!!", Toast.LENGTH_SHORT).show();

                        FirebaseUser user = mAuth.getCurrentUser();
                        String id = user.getUid();
                        String name = user.getDisplayName();
                        String email = user.getEmail();
                        String urlProfile = user.getPhotoUrl().toString();

                        User user1 = new User(id, name, email, urlProfile, mGender, mBirthday, mLocation);

                        mMyRef.child("User").child(id).setValue(user1);

                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
}
