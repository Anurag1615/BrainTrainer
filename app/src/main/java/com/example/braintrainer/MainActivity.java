package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
Button button,button1,button2,button3,button4,button5;
TextView timer,qus,res;
TextView result;
GridLayout layout;
TextToSpeech textToSpeech;
ArrayList<Integer>answer =new ArrayList<>();
int locationCurrectAnswer;
int score=0;
int noofQues=0;
public  void genratequestion(){

    Random random=new Random();
    int a=random.nextInt(21);

    int b=random.nextInt(21);
    qus.setText(Integer.toString(a)+"+"+Integer.toString(b));
    locationCurrectAnswer=random.nextInt(4);
    answer.clear();

    int incorrectAns;
    for(int i=0;i<4;i++){
        if(i==locationCurrectAnswer){
            answer.add(a+b);

        }
        else {
            incorrectAns=random.nextInt(41);
            while (incorrectAns==a+b){
                incorrectAns=random.nextInt(41);
            }
            answer.add(incorrectAns);
        }
    }
    button1.setText(Integer.toString(answer.get(0)));
    button2.setText(Integer.toString(answer.get(1)));
    button3.setText(Integer.toString(answer.get(2)));
    button4.setText(Integer.toString(answer.get(3)));


}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        button1=findViewById(R.id.btn2);
        button2=findViewById(R.id.btn3);
        button3=findViewById(R.id.btn4);
        button4=findViewById(R.id.btn5);
        result=findViewById(R.id.textView);
        timer=findViewById(R.id.timer);
       textToSpeech= new TextToSpeech(this,this);
        qus=findViewById(R.id.qus);
        res=findViewById(R.id.scor);
        button5=findViewById(R.id.playagain);
       layout=findViewById(R.id.grid);
        genratequestion();
       playAgain(findViewById(R.id.playagain));

    }

    public void start(View view) {
        button.setVisibility(View.INVISIBLE);
    }

    public void chososeAnswer(View view) {
        if(view.getTag().toString().equals(Integer.toString(locationCurrectAnswer))){
            score++;
            result.setText("currect");

        }
        else {
            result.setText("incurrect");
        }
        noofQues++;

        res.setText(Integer.toString(score)+"/"+Integer.toString(noofQues));

genratequestion();
    }

    public void playAgain(View view) {
    score=0;
    noofQues=0;
    res.setText("0/0");
    timer.setText("00:00");
    layout.setVisibility(View.VISIBLE);

    button5.setVisibility(View.INVISIBLE);

        new CountDownTimer(31000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() { button5.setVisibility(View.VISIBLE);
                timer.setText("00:00");
                result.setText(Integer.toString(score)+"/"+Integer.toString(noofQues));
                layout.setVisibility(View.INVISIBLE);
                textToSpeech.setSpeechRate(0.5f);
                textToSpeech.speak("your score is "+ Integer.toString(score)+"out of "+Integer.toString(noofQues),TextToSpeech.QUEUE_FLUSH,null);

            }
        }.start();

    }

    @Override
    public void onInit(int status) {

    }
}
