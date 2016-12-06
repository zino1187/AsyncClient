package com.solu.asyncclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    BoardAsync boardAsync;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boardAsync = new BoardAsync();
        boardAsync.execute("http://192.168.0.15:9090/device/board");
    }
}










