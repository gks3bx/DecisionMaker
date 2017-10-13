package com.example.genks.decisionmaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Random;

import static android.R.id.message;

public class Local extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.genks.decisionmaker.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        Intent intent = getIntent();
        intent.getStringExtra(binaryChoice.EXTRA_MESSAGE);
    }
    public void sendZip(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, ChoiceActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String code= editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, code);
        startActivity(intent);
    }
}

