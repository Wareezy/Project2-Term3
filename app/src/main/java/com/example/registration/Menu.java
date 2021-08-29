package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnAbout=findViewById(R.id.btnAbout);

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Menu.this,AboutPage.class);//Edvalter about page
                startActivity(intent);

            }});
        Button btnContact=findViewById(R.id.btnContactPage);
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent= new Intent(Menu.this,ContactPage.class);//Contact page here
               //startActivity(intent);

            }
        });
        Button btnFunctionPage=findViewById(R.id.btnFunctionPage);

        btnFunctionPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Menu.this,FunctionActivity.class);
                startActivity(intent);

            }

        });
        Button btnReturnToLoginArea=findViewById(R.id.btnReturnToLogin);

        btnReturnToLoginArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Menu.this,Login.class);
                startActivity(intent);

            }

        });
    }
}