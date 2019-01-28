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
    private String userEmail;
    private double mapx;
    private double mapy;
    private String date;
    private String userName;
    private String imageUrl1;
    private String imageUrl2;
    private String imageUrl3;
    private String imageUrl4;
    private String imageUrl5;
    private String imageUrl6;
    private String imageUrl7;
    private String imageUrl8;

    public String getImageUrl1() {
        return imageUrl1;
    }

    public void setImageUrl1(String imageUrl1) {
        this.imageUrl1 = imageUrl1;
    }

    public String getImageUrl2() {
        return imageUrl2;
    }

    public void setImageUrl2(String imageUrl2) {
        this.imageUrl2 = imageUrl2;
    }

    public String getImageUrl3() {
        return imageUrl3;
    }

    public void setImageUrl3(String imageUrl3) {
        this.imageUrl3 = imageUrl3;
    }

    public String getImageUrl4() {
        return imageUrl4;
    }

    public void setImageUrl4(String imageUrl4) {
        this.imageUrl4 = imageUrl4;
    }

    public String getImageUrl5() {
        return imageUrl5;
    }

    public void setImageUrl5(String imageUrl5) {
        this.imageUrl5 = imageUrl5;
    }

    public String getImageUrl6() {
        return imageUrl6;
    }

    public void setImageUrl6(String imageUrl6) {
        this.imageUrl6 = imageUrl6;
    }

    public String getImageUrl7() {
        return imageUrl7;
    }

    public void setImageUrl7(String imageUrl7) {
        this.imageUrl7 = imageUrl7;
    }

    public String getImageUrl8() {
        return imageUrl8;
    }

    public void setImageUrl8(String imageUrl8) {
        this.imageUrl8 = imageUrl8;
    }

    public String getImageUrl9() {
        return imageUrl9;
    }

    public void setImageUrl9(String imageUrl9) {
        this.imageUrl9 = imageUrl9;
    }

    private String imageUrl9;

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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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

    public ReviewList (String location_name, String location_address, String phone_number, String location_category, String review_description,
                       double mapx, double mapy, Boolean tag1, Boolean tag2, Boolean tag3, Boolean tag4, Boolean tag5, Boolean tag6, String date, String userName, String key){

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
        this.key = key;
    }

}
