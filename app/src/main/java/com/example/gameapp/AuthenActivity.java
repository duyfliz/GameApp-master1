package com.example.gameapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import me.toptas.fancyshowcase.FancyShowCaseQueue;
import me.toptas.fancyshowcase.FancyShowCaseView;

public class AuthenActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText edt1, edt2;
    TextView txt;
    Button btn;
    private FirebaseAuth.AuthStateListener mAuthListener;
    public static final String PREFS_NAME = "MyPrefsFile1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authen);

        btn = (Button) findViewById(R.id.button_log);
        edt1 = (EditText) findViewById(R.id.text_id);
        edt2 = (EditText) findViewById(R.id.text_pwd);
        txt = (TextView) findViewById(R.id.txt_signup);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0); // Get preferences file (0 = no option flags set)
        boolean firstRun = settings.getBoolean("firstRun", true); // Is it first run? If not specified, use "true"

        if (firstRun) {
            //setContentView(R.layout.activity_clean_weather);
            IntroAnimation();
            SharedPreferences.Editor editor = settings.edit(); // Open the editor for our settings
            editor.putBoolean("firstRun", false); // It is no longer the first run
            editor.commit(); // Save all changed settings
        } else {

        }




        mAuth =FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    //Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    //Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };







        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.signInWithEmailAndPassword(edt1.getText().toString(), edt2.getText().toString())
                        .addOnCompleteListener(AuthenActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(AuthenActivity.this, "Fail",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(AuthenActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(AuthenActivity.this, WelcomeActivity.class);
                                    intent.putExtra("user", edt1.getText().toString());
                                    startActivity(intent);
                                    finish();
                                }

                            }
                        });

            }
        });

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AuthenActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }



    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    public void IntroAnimation() {
        FancyShowCaseView fancyShowCaseView1 = new FancyShowCaseView.Builder(this)
                .title("This is the login screen.\nYou can sign in if you already have an account or you can sign up a new account.")
                .build();

        FancyShowCaseView fancyShowCaseView2 = new FancyShowCaseView.Builder(this)
                .focusOn(edt1)
                .title("Type in your email address to sign in")
                .build();

        FancyShowCaseView fancyShowCaseView3 = new FancyShowCaseView.Builder(this)
                .focusOn(edt2)
                .title("Type the according password")
                .build();

        FancyShowCaseView fancyShowCaseView4 = new FancyShowCaseView.Builder(this)
                .focusOn(btn)
                .title("Press this button to log in to our application!")
                .build();

        new FancyShowCaseQueue().add(fancyShowCaseView1)
                .add(fancyShowCaseView2).add(fancyShowCaseView3).add(fancyShowCaseView4).show();
    }
}
