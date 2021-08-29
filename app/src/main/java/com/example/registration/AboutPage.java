package com.example.registration;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AboutPage extends AppCompatActivity {
    ViewPager K_ViewPager;
    LinearLayout K_mDotLayout;
    Button backbtn, nextbtn;
    //K
    TextView[] dots;
    ViewPagerAdapter viewPagerAdapter;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);
        backbtn = findViewById(R.id.backbtn);
        nextbtn = findViewById(R.id.nextbtn);


        backbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                if (getitem(0) > 0){

                    K_ViewPager.setCurrentItem(getitem(-1),true);

                }

            }
        });
//A
        nextbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                if (getitem(0) < 6)
                    K_ViewPager.setCurrentItem(getitem(1),true);
                else {

                    Intent i = new Intent(AboutPage.this,Menu.class);
                    startActivity(i);
                    finish();

                }

            }
        });

//Z

        K_ViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        K_mDotLayout = (LinearLayout) findViewById(R.id.indicator_layout);

        viewPagerAdapter = new ViewPagerAdapter(this);

        K_ViewPager.setAdapter(viewPagerAdapter);

        setUpindicator(0);
        K_ViewPager.addOnPageChangeListener(viewListener);

    }

    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setUpindicator(int pos)
    {

        dots = new TextView[6];
        K_mDotLayout.removeAllViews();

        for (int k = 0 ; k < dots.length ; k++){

            dots[k] = new TextView(this);
            dots[k].setText(Html.fromHtml("&#8226"));
            dots[k].setTextSize(35);
            dots[k].setTextColor(getResources().getColor(R.color.inactive,getApplicationContext().getTheme()));
            K_mDotLayout.addView(dots[k]);

        }

        dots[pos].setTextColor(getResources().getColor(R.color.active,getApplicationContext().getTheme()));

    }
    //I
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener()
    {
        @Override
        public void onPageScrolled(int pos, float positionOffset, int positionOffsetPixels)
        {

        }

        @SuppressLint("NewApi")
        @Override
        public void onPageSelected(int pos) {

            setUpindicator(pos);
//R
            //
            if (pos >0)
            {


                backbtn.setVisibility(View.VISIBLE);

            } else {

                backbtn.setVisibility(View.INVISIBLE);


            }
            //
            if (pos < 5)
            {

                nextbtn.setVisibility(View.VISIBLE);


            } else {


                nextbtn.setVisibility(View.INVISIBLE);

            }

        }



        //A
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private int getitem(int k){

        return K_ViewPager.getCurrentItem() + k;
    }

}

