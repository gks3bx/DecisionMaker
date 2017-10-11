package com.example.genks.decisionmaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

public class binaryChoice extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.genks.decisionmaker.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binary_choice);
    }
    public void sendYesNoMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayYesNoMessageActivity.class);
        String message= "YES";
        Random randNum = new Random();
        int result= randNum.nextInt(2);
        if (result == 0){
            message = "Yes";
        }
        else{
            message= "No";
        }
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
    public void onClick(View view) {
        // Do something in response to button
        Intent intent = new Intent(binaryChoice.this, Local.class);
        startActivity(intent);
    }
}
