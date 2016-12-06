package com.solu.asyncclient;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    BoardAsync boardAsync;
    ViewPager viewPager;
    MyPagerAdapter myPagerAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager=(ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(myPagerAdapter);

        boardAsync = new BoardAsync();
        boardAsync.execute("http://192.168.0.15:9090/device/board");
    }
}










