package com.example.mccgame;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Level extends AppCompatActivity {
    TextView txtLevel,txtTimer,txtQuestionNumber,txtScore,txtQuestion;
    int questionIndex;
    CountDownTimer countDownTimer;
    String Level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        String Level = getIntent().getStringExtra("Level");
        txtLevel=(TextView) findViewById(R.id.Level);
        txtLevel.setText(Level);
        txtTimer=(TextView) findViewById(R.id.Timer);
        txtQuestionNumber=(TextView) findViewById(R.id.QuestionNumber);

        txtQuestion=(TextView) findViewById(R.id.Question);
        if(Level.equals("Level0")){
            txtTimer.setVisibility(View.INVISIBLE);
        }
        setUpQuestion();
    }
    public void setTimer(){
        int MiliSecond =0;
        if(Level.equals("Level1")) {
            MiliSecond = 21000;
        }
        else if (Level.equals("Level2")) {
            MiliSecond = 11000;
        }
        countDownTimer=new CountDownTimer(MiliSecond,1000) {
            @Override
            public void onTick(long l) {
                txtTimer.setText("Timer:00:"+l/1000);
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }
    public void setUpQuestion(){
        if(!(Level.equals("Level0"))){
            setTimer();
        }
    }
}
