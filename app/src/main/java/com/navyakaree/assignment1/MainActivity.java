package com.navyakaree.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements UserDetailsFragment.LoginListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void createMeme(String username, String password) {
        Intent intent = new Intent(MainActivity.this, ListviewActivity.class);
        startActivity(intent);
    }
}
