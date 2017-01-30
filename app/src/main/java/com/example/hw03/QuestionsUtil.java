package com.example.hw03;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class QuestionsUtil {

    public static class QuestionJSONParser {

        public static ArrayList<Question> parseQuestions(String in) throws JSONException {

            ArrayList<Question> questionList = new ArrayList<>();

            JSONObject root = new JSONObject(in);
            JSONArray questionJSONArray = root.getJSONArray("questions");

            Log.d("demo","length "+questionJSONArray.length());
            for (int i = 0; i < questionJSONArray.length(); i++) {
                JSONObject questionJSONObject = questionJSONArray.getJSONObject(i);
                Question question = new Question();

                question=question.createQuestion(questionJSONObject);
                //Log.d("demo",question.getText());
                questionList.add(question);
            }
            return questionList;
        }
    }
}
