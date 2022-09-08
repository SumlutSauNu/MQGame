package com.example.mccgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    RadioGroup rgLevel;
    RadioButton rbLevel0, rbLevel1, rbLevel2;
    Button btnStart, btnClear;
    String Level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        rgLevel = (RadioGroup) findViewById(R.id.idrg);
        rbLevel0 = (RadioButton) findViewById(R.id.idlevel0);
        rbLevel1 = (RadioButton) findViewById(R.id.idlevel1);
        rbLevel2 = (RadioButton) findViewById(R.id.idlevel2);
        btnStart = (Button) findViewById(R.id.idbtnStant);
        btnClear = (Button) findViewById(R.id.idclear);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (
                        rbLevel0.isChecked() || rbLevel1.isChecked() || rbLevel2.isChecked()) {
                    RadioButton rbChecked = findViewById(rgLevel.getCheckedRadioButtonId());
                    Level = rbChecked.getText().toString();
                    Intent intent = new Intent(SecondActivity.this, Level.class);
                    intent.putExtra("Level",Level);
                    startActivity(intent);

                }
            }

        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rgLevel.clearCheck();
            }
        });
    }

}