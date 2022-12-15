package com.example.echo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText textInput;
    private Button echoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textInput = findViewById(R.id.textInput);
        echoButton = findViewById(R.id.echoButton);

        echoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showResultActivity(view);
            }
        });
    }

    private void showResultActivity(View view) {
        Intent resultActivity = new Intent(this, ResultActivity.class);
        resultActivity.putExtra("text_to_echo", String.valueOf(textInput.getText()));
        startActivity(resultActivity);
    }
}