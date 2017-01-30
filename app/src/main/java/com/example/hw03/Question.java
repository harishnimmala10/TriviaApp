package com.example.hw03;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;


public class Question implements Serializable{

    String id,text,image,answer;
    ArrayList<String> choice;



    public Question createQuestion(JSONObject js) throws JSONException {
        Question question =new Question();

        question.setId(js.getString("id"));

        question.setText(js.getString("text"));
        if(js.has("image")) {
            question.setImage(js.getString("image"));
        }

        question.setAnswer(js.getJSONObject("choices").getString("answer"));

        question.setChoice(js.getJSONObject("choices").getJSONArray("choice"));
        Log.d("demo", question.getText());
        return  question;

    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public ArrayList<String> getChoice() {
        return choice;
    }

    public void setChoice(JSONArray choiceJSON) throws JSONException {
        choice = new ArrayList<>();
        //JSONArray jArray = (JSONArray)choiceJSON;
        if (choiceJSON != null) {
            for (int i=0;i<choiceJSON.length();i++){
                choice.add(choiceJSON.get(i).toString());
            }
        }
    }
}
