package com.example.hw03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class StatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        int status=getIntent().getExtras().getInt(TriviaActivity.CORRECT_ANSWERS);

        TextView success= (TextView) findViewById(R.id.textView4);
        success.setText(status + " %");
        ProgressBar progress = (ProgressBar) findViewById(R.id.progressBar);
        progress.setProgress(status);

        findViewById(R.id.quit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra(TriviaActivity.BUTTON_KEY, "quit");
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        findViewById(R.id.tryAgain_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra(TriviaActivity.BUTTON_KEY, "try");
                setResult(RESULT_OK,intent);
                finish();;
            }
        });
    }
}
