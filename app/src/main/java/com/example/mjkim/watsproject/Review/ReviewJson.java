package com.example.mjkim.watsproject.Review;

import org.json.JSONArray;
import org.json.JSONObject;

public class ReviewJson {

    private int review_count;
    private String review_json_string;
    private JSONArray review_json_userID;
    private String location_name;

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public int getReview_count() {
        return review_count;
    }

    public void setReview_count(int review_count) {
        this.review_count = review_count;
    }

    public String getReview_json_string() {
        return review_json_string;
    }

    public void setReview_json_string(String review_json_string) {
        this.review_json_string = review_json_string;
    }

    public JSONArray getReview_json_userID() {
        return review_json_userID;
    }

    public void setReview_json_userID(JSONArray review_json_userID) {
        this.review_json_userID = review_json_userID;
    }


    public ReviewJson(){}

    public ReviewJson(String location_name, int review_count, String review_json_string, JSONArray review_json_userID){
        this.location_name = location_name;
        this.review_count = review_count;
        this.review_json_string = review_json_string;
        this.review_json_userID = review_json_userID;
    }

    public ReviewJson(int review_count, String review_json_string, JSONArray review_json_userID){
        this.review_count = review_count;
        this.review_json_string = review_json_string;
        this.review_json_userID = review_json_userID;
    }
}
