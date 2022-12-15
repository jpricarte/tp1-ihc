package com.example.echo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView echoedText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        echoedText = findViewById(R.id.textToEcho);
        echoedText.setText(intent.getStringExtra("text_to_echo"));
    }
}