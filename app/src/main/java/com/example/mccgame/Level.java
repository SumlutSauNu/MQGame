package com.example.mccgame;

import android.content.Intent;
import android.graphics.Path;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Debug;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TintableCheckedTextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Timer;

public class Level extends AppCompatActivity {
    TextView txtLevel, txtQuestionNumber, txtQuestion, txtTimer, txtScore;
    ImageView imgCong;
    String level;
    CountDownTimer countDownTimer;
    int questionIndex = 1;
    int answer = 0;
    int score = 0;
    int num1 = 0;
    int num2 = 0;
    int operatorNum = 0;
    String operator = "0";
    RadioGroup radioGroup;
    RadioButton Option1, Option2, Option3;
    ArrayList<Integer> OptionList;
    Button btnCheck, btnNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        txtLevel = findViewById(R.id.idtxtLevel);
        txtQuestionNumber = findViewById(R.id.idtxtQNum);
        txtQuestion = findViewById(R.id.idQuestion);
        txtTimer = findViewById(R.id.idtxtTimer);
        txtScore = findViewById(R.id.Score);
        imgCong = findViewById(R.id.idimgcong);
        level = getIntent().getStringExtra("Level");

        radioGroup = findViewById(R.id.RedioGroup);
        Option1 = findViewById(R.id.Option1);
        Option2 = findViewById(R.id.Option2);
        Option3 = findViewById(R.id.Option3);

        btnCheck = (Button) findViewById(R.id.Check);
        btnNext = (Button) findViewById(R.id.Next);
        imgCong.setVisibility(View.INVISIBLE);
        btnNext.setVisibility(View.INVISIBLE);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckAnswer();

            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionIndex<=10){
                    setUpQuestion();
                }
                else{
                    imgCong.setVisibility(View.VISIBLE);
                }
            }
        });

        txtLevel.setText(level);
        if (level.equals("Level0")) {
            txtTimer.setVisibility(View.INVISIBLE);
        }
        else{
            setTimer();
        }
        setUpQuestion();


    }

    public void setUpQuestion() {
        btnCheck.setVisibility(View.VISIBLE);
        btnNext.setVisibility(View.INVISIBLE);
        radioGroup.clearCheck();
        txtQuestionNumber.setText("Question" + questionIndex + "/10");
        questionIndex++;
        txtScore.setText("Score" + score);
        if (!(level.equals("Level0"))) {
            countDownTimer.start();
        }
        num1 = randomNumber(12, 1);
        num2 = randomNumber(12, 1);
        operatorNum = randomNumber(4, 1);
        if (operatorNum == 1) {
            operator = "+";
            answer = num1 + num2;
        }
        else if (operatorNum == 2) {
            while(num1<num2){
                num1 = randomNumber(12, 1);
                num2 = randomNumber(12, 1);
            }
            operator="-";
            answer = num1 - num2;
        } else if (operatorNum == 3) {
            operator = "*";
            answer = num1 * num2;
        } else if (operatorNum == 4) {
            operator = "/";
            answer = num1 / num2;
        }
        txtQuestion.setText(num1 + operator + num2 + "will be");
        setUpOption();
    }



    public int randomNumber(int max, int min) {
        int range = max - min + 1;
        int rand = (int) (Math.random() * range) + min;
        return rand;
    }

    public void setUpOption() {
        OptionList = new ArrayList<Integer>();
        OptionList.add(answer);
        for(int i=0 ; i<2 ; i++){
            int randNum = randomNumber(12,1);
            while (OptionList.contains(randNum)){
                randNum = randomNumber(12,1);
                Toast.makeText(Level.this,"During While",Toast.LENGTH_SHORT).show();
            }
            OptionList.add(randNum);
        }
        Collections.shuffle(OptionList);
        Option1.setText(String.valueOf(OptionList.get(0)));
        Option2.setText(String.valueOf(OptionList.get(1)));
        Option3.setText(String.valueOf(OptionList.get(2)));
    }
    public void setTimer() {
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
                if(questionIndex<=10){
                    setUpQuestion();
                }
                else{
                    imgCong.setVisibility(View.INVISIBLE);
                }
            }
        }.start();

    }
    public void CheckAnswer() {
        if (Option1.isChecked() || Option2.isChecked() || Option3.isChecked()) {
            RadioButton Check = findViewById(radioGroup.getCheckedRadioButtonId());
            if (answer == (Integer.parseInt(Check.getText().toString()))) {
                score++;
                txtScore.setText("Score:" + score);
            }
            btnNext.setVisibility(View.VISIBLE);
            btnCheck.setVisibility(View.INVISIBLE);
            if(answer==Integer.parseInt(Check.getText().toString())){
                score++;
                txtScore.setText("Score"+score);
                Toast.makeText(Level.this,"Correct!",Toast.LENGTH_SHORT).show();
                MediaPlayer ring= MediaPlayer.create(Level.this,R.raw.right);
                ring.start();
            }
            else{
                Toast.makeText(Level.this,"Incorrect!",Toast.LENGTH_SHORT).show();
                MediaPlayer ring= MediaPlayer.create(Level.this,R.raw.fall);
                ring.start();
            }

            if(!level.equals("0")){
                txtTimer.cancelLongPress();
            }
        }

        else {
            Toast.makeText(Level.this, "Please Choose one", Toast.LENGTH_SHORT).show();
        }
    }
}