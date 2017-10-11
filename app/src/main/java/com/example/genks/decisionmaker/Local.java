package com.example.genks.decisionmaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Local extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        Intent intent = getIntent();
        intent.getStringExtra(binaryChoice.EXTRA_MESSAGE);
    }
}
