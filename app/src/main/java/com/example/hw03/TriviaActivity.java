package com.example.hw03;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class TriviaActivity extends AppCompatActivity implements Serializable,IData {

    public static String BUTTON_KEY ;
    ArrayList<Question> questions;
    static int index=0;
    private TextView qnumber;
    private TextView text;
    private TextView time;
    //private TextView date;
    static int correct=0;
    RadioGroup rg;
    Question question;
    Button btn;
    ProgressBar bar;
    private static int REQ_CODE=100;
    static String CORRECT_ANSWERS="CORRECT";
    CountDownTimer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        //questions = MainActivity.questionList;
        questions= (ArrayList<Question>) getIntent().getExtras().getSerializable(MainActivity.QUESTION_KEY);
        qnumber = ((TextView) findViewById(R.id.question_number));
        text = ((TextView) findViewById(R.id.question));
        btn = (Button) findViewById(R.id.next);
        bar = (ProgressBar) findViewById(R.id.progressBar3);
        time = (TextView) findViewById(R.id.timer);


        showData(index);

        timer = new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {
                time.setText("Time Left: "+ millisUntilFinished / 1000 + " Seconds");
            }

            public void onFinish() {
                time.setText("done!");
                Intent intent = new Intent(TriviaActivity.this, StatusActivity.class);

                intent.putExtra(CORRECT_ANSWERS, (correct * 100) / questions.size());

                startActivityForResult(intent, REQ_CODE);
            }
        }.start();
        findViewById(R.id.quit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index=0;
                correct=0;
                finish();
            }
        });
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //option=
                checkAnswer(rg.getCheckedRadioButtonId(), Integer.parseInt(questions.get(index).getAnswer()));
                if (index == questions.size() - 1) {
                    //checkAnswer(rg.getCheckedRadioButtonId(),index);
                    Log.d("correct", "C" + correct);
                    timer.cancel();
                    time.setText("");
                    Intent intent = new Intent(TriviaActivity.this, StatusActivity.class);

                    intent.putExtra(CORRECT_ANSWERS, (correct * 100) / questions.size());

                    startActivityForResult(intent, REQ_CODE);
                } else {
                    index = index + 1;
                    showData(index);
                    //checkAnswer();
                }

            }
        });



    }
    public void showData(int index) {
       // int option=-1;
        qnumber.setText("Q"+(index+1));

        question=questions.get(index);
        text.setText(question.getText());
        findViewById(R.id.imageView).setVisibility(View.INVISIBLE);
        findViewById(R.id.progressBar3).setVisibility(View.VISIBLE);
        new GetImage(this,bar).execute(question.getImage());
        rg=(RadioGroup) findViewById(R.id.radiogroup);
        rg.clearCheck();
        if(index>0) {
            rg.removeAllViewsInLayout();
            //option=-1;
        }
        rg.setOrientation(LinearLayout.VERTICAL);

        for (int i = 0; i < question.getChoice().size(); i++) {
            RadioButton rdbtn = new RadioButton(this);
            rdbtn.setId(i+1);
            rdbtn.setText(question.getChoice().get(i));
            rg.addView(rdbtn);
        }

    }
    public void checkAnswer(int option,int answer)
    {


        Log.d("option",option+"");
            if(option==answer) {
                correct=correct+1;
            }

    }

    @Override
    public void sendData(Bitmap bitmap) {

        if(bitmap!=null) {


            ImageView img= (ImageView) findViewById(R.id.imageView);
            img.setImageBitmap(bitmap);
            //findViewById(R.id.progressBar3).setVisibility(View.INVISIBLE);
            findViewById(R.id.imageView).setVisibility(View.VISIBLE);
        }
        else {
            findViewById(R.id.progressBar3).setVisibility(View.INVISIBLE);
            findViewById(R.id.imageView).setVisibility(View.INVISIBLE);
        }


    }

    @Override
    public void sendData(ArrayList<Question> list) {
        //questions=list;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
                    String val= (String) data.getExtras().getSerializable(TriviaActivity.BUTTON_KEY);
            index=0;
            correct=0;
            Log.d("try/quit",val);
                   if(val.equals("try")) {

                       recreate();
                   }
                    else {

                      finish();
                   }

        }

    }
}
