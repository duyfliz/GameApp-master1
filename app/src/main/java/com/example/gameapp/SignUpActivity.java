package com.example.gameapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    EditText edt1, edt2;
    Button btn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        edt1 = (EditText) findViewById(R.id.signup_id);
        edt2 = (EditText) findViewById(R.id.signup_pwd);
        btn = (Button) findViewById(R.id.btn_signup);

       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               mAuth.createUserWithEmailAndPassword(edt1.getText().toString(), edt2.getText().toString())
                       .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {

                               if (task.isSuccessful()) {
                                   Toast.makeText(SignUpActivity.this, "Create account successfully",
                                           Toast.LENGTH_SHORT).show();
                                   Intent intent = new Intent(SignUpActivity.this, AuthenActivity.class);
                                   startActivity(intent);
                                   finish();
                               }

                               // ...
                           }
                       });
           }
       });
    }
}
