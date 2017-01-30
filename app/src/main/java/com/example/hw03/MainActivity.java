package com.example.hw03;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IData {

    ProgressBar bar;
    ImageView tReady;
    Button startTrivia, exit;
    public static String QUESTION_KEY="QUESTION";
    static ArrayList<Question> questionList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar= (ProgressBar)findViewById(R.id.progressBar2);
        tReady =(ImageView)findViewById(R.id.triviaReady_label);
        startTrivia = (Button)findViewById(R.id.start_button);
        exit = (Button)findViewById(R.id.exit_button);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        new GetQuestionsAsyncTask(this,bar).execute("http://dev.theappsdr.com/apis/trivia_json/index.php");

        startTrivia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Log.d("demoo","size before" + questionList.size());
                Intent intent = new Intent(MainActivity.this, TriviaActivity.class);

                intent.putExtra(QUESTION_KEY,questionList);

                startActivity(intent);
            }
        });


    }


    @Override
    public void sendData(Bitmap bitmap) {

    }

    @Override
    public void sendData(ArrayList<Question> list) {
        questionList=list;
        tReady.setVisibility(View.VISIBLE);
        bar.setVisibility(View.INVISIBLE);
        startTrivia.setEnabled(true);
        findViewById(R.id.trivia_ready).setVisibility(View.VISIBLE);
    }
}
