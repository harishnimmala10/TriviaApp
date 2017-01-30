package com.example.hw03;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;


public class GetQuestionsAsyncTask extends AsyncTask<String ,Void, ArrayList<Question>>{


    Context context;
    View rootView;
    IData activity;
    static ArrayList<Question> questionList=new ArrayList<>();

    public GetQuestionsAsyncTask(Context context,View view) {
        this.context = context;
        this.rootView = view;
        this.activity= (IData)context;
    }

    @Override
    protected void onPostExecute(ArrayList<Question> result) {
        for(int i=0;i<result.size();i++)
        {
            Log.d("Value",result.get(i).getText());
        }
        if(result!=null) {
            super.onPostExecute(result);
            Log.d("demo", "gone");
            //MainActivity.questionList=result;
            activity.sendData(result);
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();



       // dialog.show();

    }



    @Override
    protected ArrayList<Question> doInBackground(String... params) {

        try {

            URL url= new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statusCode = con.getResponseCode();
            if(statusCode==HttpsURLConnection.HTTP_OK){
                BufferedReader reader= new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder builder= new StringBuilder();
                String line = reader.readLine();
                while (line!=null){
                    builder.append(line);
                    line=reader.readLine();

                }
                Log.d("demo",builder.toString());
               questionList= QuestionsUtil.QuestionJSONParser.parseQuestions(builder.toString());
                return questionList;


            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        finally {
            Log.d("demo","done");
        }

        return null;
    }
}
