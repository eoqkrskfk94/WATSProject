package com.example.mjkim.watsproject.Review;

public class ReviewList {

    private String key;
    private Boolean tag1;
    private Boolean tag2;
    private Boolean tag3;
    private Boolean tag4;
    private Boolean tag5;
    private Boolean tag6;
    private String location_name;
    private String location_category;
    private String location_address;
    private String phone_number;
    private String review_description;
    private String name;
    private String email;
    private double mapx;
    private double mapy;
    private String date;
    private String userName;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Boolean getTag1() {
        return tag1;
    }

    public void setTag1(Boolean tag1) {
        this.tag1 = tag1;
    }

    public Boolean getTag2() {
        return tag2;
    }

    public void setTag2(Boolean tag2) {
        this.tag2 = tag2;
    }

    public Boolean getTag3() {
        return tag3;
    }

    public void setTag3(Boolean tag3) {
        this.tag3 = tag3;
    }

    public Boolean getTag4() {
        return tag4;
    }

    public void setTag4(Boolean tag4) {
        this.tag4 = tag4;
    }

    public Boolean getTag5() {
        return tag5;
    }

    public void setTag5(Boolean tag5) {
        this.tag5 = tag5;
    }

    public Boolean getTag6() {
        return tag6;
    }

    public void setTag6(Boolean tag6) {
        this.tag6 = tag6;
    }

    public String getLocation_category() {
        return location_category;
    }

    public void setLocation_category(String location_category) {
        this.location_category = location_category;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public String getLocation_address() {
        return location_address;
    }

    public void setLocation_address(String location_address) {
        this.location_address = location_address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getReview_description() {
        return review_description;
    }

    public void setReview_description(String review) {
        this.review_description = review;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getMapx() {
        return mapx;
    }

    public void setMapx(double mapx) {
        this.mapx = mapx;
    }

    public double getMapy() {
        return mapy;
    }

    public void setMapy(double mapy) {
        this.mapy = mapy;
    }

    public ReviewList(){};

    public ReviewList (String location_name, String location_address, String phone_number, String location_category, String review_description, double mapx, double mapy, Boolean tag1, Boolean tag2, Boolean tag3, Boolean tag4, Boolean tag5, Boolean tag6, String date, String userName){

        this.location_name = location_name;
        this.location_address = location_address;
        this.phone_number = phone_number;
        this.location_category = location_category;
        this.review_description = review_description;
        this.mapx = mapx;
        this.mapy = mapy;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.tag4 = tag4;
        this.tag5 = tag5;
        this.tag6 = tag6;
        this.date = date;
        this.userName = userName;
    }

}
