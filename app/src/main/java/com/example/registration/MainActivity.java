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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
        private TextView Register,Login;
        private EditText editStudentNumber,editStudentEmail,editPassword,editConfirmPassword;
        private ProgressBar progressBar;
        private FirebaseAuth mAuth;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            mAuth=FirebaseAuth.getInstance();
            Login=(TextView)findViewById(R.id.alreadyHaveAccount);
            Login.setOnClickListener(this);

            Register=(Button) findViewById(R.id.btnRegister);
            Register.setOnClickListener(this);



            editStudentNumber=(EditText) findViewById(R.id.inputStudentNumber);
            editStudentEmail=(EditText) findViewById(R.id.inputStudentEmail);
            editPassword=(EditText) findViewById(R.id.inputPassword);
            editConfirmPassword=(EditText)findViewById(R.id.inputConfirmPassword);

            progressBar=(ProgressBar) findViewById(R.id.progressBar);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.alreadyHaveAccount:
                    startActivity(new Intent(this,Login.class));
                    break;
                case R.id.btnRegister:
                    Register();
                    break;


            }

        }

        private void Register() {
            String inputStudentNumber = editStudentNumber.getText().toString().trim();
            String inputStudentEmail = editStudentEmail.getText().toString().trim();
            String inputPassword = editPassword.getText().toString().trim();
            String inputConfirmPassword = editConfirmPassword.getText().toString().trim();

            if (inputStudentNumber.isEmpty()) {
                editStudentNumber.setError("Student Number is required!");
                editStudentNumber.requestFocus();
                return;

            }
            if (inputStudentEmail.isEmpty()) {
                editStudentEmail.setError("Student email is required!");
                editStudentEmail.requestFocus();
                return;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(inputStudentEmail).matches()) {
                editStudentEmail.setError("Please provide valid email");
                editStudentEmail.requestFocus();
                return;
            }
            if (inputPassword.isEmpty()) {
                editPassword.setError("Password is required!");
                editPassword.requestFocus();
                return;
            }
            if (inputPassword.length() < 6) {
                editPassword.setError("Password length should be 6 characters");
                editPassword.requestFocus();
                return;
            }

            if (inputConfirmPassword.isEmpty()) {
                editConfirmPassword.setError("Confirm Password is required!");
                editConfirmPassword.requestFocus();
                return;
            }
            if (inputConfirmPassword.length() < 6) {
                editConfirmPassword.setError("Password length should be 6 characters");
                editConfirmPassword.requestFocus();
                return;
            }
            progressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(inputStudentEmail, inputPassword)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Users user = new Users(inputStudentNumber, inputStudentEmail, inputPassword);
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                  @Override
                                                                                  public void onComplete(@NonNull Task<Void> task) {

                                                                                      if (task.isSuccessful()) {
                                                                                          Toast.makeText(MainActivity.this, "User has been registered successfully",
                                                                                                  Toast.LENGTH_LONG).show();
                                                                                          progressBar.setVisibility(View.GONE);
//redirect to login layout
                                                                                      } else {
                                                                                          Toast.makeText(MainActivity.this, "Failed to register try again", Toast.LENGTH_LONG).show();
                                                                                          progressBar.setVisibility(View.GONE);
                                                                                      }
                                                                                  }
                                                                              }
                                );
                            }
                        }
                    });

        }}
