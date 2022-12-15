package com.example.calcpro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText firstNumberInput;
    private EditText secondNumberInput;
    private Button makeSumButton;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNumberInput = findViewById(R.id.firstNumberInput);
        secondNumberInput = findViewById(R.id.secondNumberInput);
        makeSumButton = findViewById(R.id.makeSumButton);
        resultText = findViewById(R.id.resultText);

        makeSumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double x1 = Double.parseDouble(String.valueOf(firstNumberInput.getText()));
                double x2 = Double.parseDouble(String.valueOf(secondNumberInput.getText()));
                resultText.setText(String.valueOf(x1+x2));
            }
        });
    }
}