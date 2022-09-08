package com.example.mccgame;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Level extends AppCompatActivity {
    TextView txtLevel, txtQuestionNumber, txtQuestion, txtTimer;
    String level;
    CountDownTimer countDownTimer;
    int questionIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        txtLevel = findViewById(R.id.idtxtLevel);
        txtQuestionNumber = findViewById(R.id.idtxtQNum);
        txtQuestion = findViewById(R.id.idQuestion);
        txtTimer = findViewById(R.id.idtxtTimer);
        level = getIntent().getStringExtra("Level");
        txtLevel.setText(level);
        if(level.equals("0")){
            txtTimer.setVisibility(View.INVISIBLE);
        }
        setUpQuestion();
    }
    public void setUpQuestion(){
        if (!(level.equals("Level0"))) {
            setTimer();
        }

    }
    public void setTimer(){
        int miliSecond = 0;
        if (level.equals("Level1")) {
            miliSecond = 21000;
        } else if (level.equals("Level2")) {
            miliSecond = 11000;
        }
        countDownTimer = new CountDownTimer(miliSecond, 1000) {
            @Override
            public void onTick(long l) {
                txtTimer.setText("Timer:00:" + l / 1000);
            }

            @Override
            public void onFinish() {

            }
        }.start();

    }

}
