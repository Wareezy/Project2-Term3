package com.example.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotPassword extends AppCompatActivity {

        private EditText StudentEmailReset;
        private Button resetPasswordButton,backLogin;
        private ProgressBar progressBar;

        FirebaseAuth auth;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_forgot_password);

            StudentEmailReset=(EditText)findViewById(R.id.inputStudentEmailReset);
            resetPasswordButton=(Button) findViewById(R.id.btnResetPassword);
            backLogin=(Button) findViewById(R.id.btnBacklogin);
            backLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(forgotPassword.this,Login.class);
                    startActivity(intent);}});

            progressBar=(ProgressBar)findViewById(R.id.progressBar);

            auth=FirebaseAuth.getInstance();

            resetPasswordButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resetPassword();
                }
            });
        }
        private void resetPassword()
        {
            String inputStudentEmail=StudentEmailReset.getText().toString() .trim();

            if(inputStudentEmail.isEmpty())
            {
                StudentEmailReset.setError("Email is required");
                StudentEmailReset.requestFocus();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(inputStudentEmail).matches())
            {
                StudentEmailReset.setError("Please provide valid email!");
                StudentEmailReset.requestFocus();
                return;

            }
            progressBar.setVisibility(View.VISIBLE);
            auth.sendPasswordResetEmail(inputStudentEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(forgotPassword.this,"Check your email to reset your password!",Toast.LENGTH_LONG).show();
                    }else
                    {
                        Toast.makeText(forgotPassword.this,"Try again! Something wrong happened!",Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
    }

