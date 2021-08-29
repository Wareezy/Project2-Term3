package com.example.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class Login extends AppCompatActivity implements View.OnClickListener
{

    private TextView signUp,forgot;
    private EditText editTextEmail, editTextPassword;
    private Button logIn;


    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        signUp = (TextView) findViewById(R.id.textViewSignUp);
        signUp.setOnClickListener(this);

        TextView forgot=findViewById(R.id.forgotPassword);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,forgotPassword.class);
                startActivity(intent);}});

        logIn = (Button) findViewById(R.id.btnLogin);
        logIn.setOnClickListener(this);


        editTextEmail = (EditText) findViewById(R.id.inputEmail);
        editTextPassword = (EditText) findViewById(R.id.inputPassword);

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.textViewSignUp:
                startActivity(new Intent( this, MainActivity.class)); // register page linked here
                break;

            case R.id.btnLogin:
                userLogin();
                break;
        }

    }

    private void userLogin()
    {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty())
        {
            editTextEmail.setError("Your email is invalid");
            editTextEmail.requestFocus();
            return;
        }

        if(password.length() < 6)
        {
            editTextPassword.setError("Min password length is 5 characters");
            editTextPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified())
                    {

                        startActivity(new Intent(Login.this, Menu.class)); // function page linked here

                    }
                    else
                    {
                        user.sendEmailVerification();
                        Toast.makeText(Login.this, "Check your gmail account for verification!", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(Login.this, "Failed to Login! Please check your credentials", Toast.LENGTH_LONG).show();
                }


            }
        });


    }
}
