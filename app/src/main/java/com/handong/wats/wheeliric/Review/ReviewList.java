package com.handong.wats.wheeliric.Review;

public class ReviewList {

    private String key;
    private Boolean tag1;
    private Boolean tag2;
    private Boolean tag3;
    private Boolean tag4;
    private Boolean tag5;
    private Boolean tag6;
    private Boolean averageTag1,averageTag2,averageTag3,averageTag4,averageTag5,averageTag6;
    private String location_name;
    private String location_category;
    private String short_category;
    private String location_address;
    private String phone_number;
    private String review_description;
    private String userEmail;
    private double mapx;
    private double mapy;
    private String date;
    private String userName;
    private String userNickName;
    private String imageUrl1;
    private String imageUrl2;
    private String imageUrl3;
    private String imageUrl4;
    private String imageUrl5;
    private String imageUrl6;
    private String imageUrl7;
    private String imageUrl8;
    private String imageUrl9;

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



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getShort_category() { return short_category; }

    public void setShort_category(String short_category) { this.short_category = short_category; }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

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

    public Boolean getAverageTag1() {
        return averageTag1;
    }

    public void setAverageTag1(Boolean averageTag1) {
        this.averageTag1 = averageTag1;
    }

    public Boolean getAverageTag2() {
        return averageTag2;
    }

    public void setAverageTag2(Boolean averageTag2) {
        this.averageTag2 = averageTag2;
    }

    public Boolean getAverageTag3() {
        return averageTag3;
    }

    public void setAverageTag3(Boolean averageTag3) {
        this.averageTag3 = averageTag3;
    }

    public Boolean getAverageTag4() {
        return averageTag4;
    }

    public void setAverageTag4(Boolean averageTag4) {
        this.averageTag4 = averageTag4;
    }

    public Boolean getAverageTag5() {
        return averageTag5;
    }

    public void setAverageTag5(Boolean averageTag5) {
        this.averageTag5 = averageTag5;
    }

    public Boolean getAverageTag6() {
        return averageTag6;
    }

    public void setAverageTag6(Boolean averageTag6) {
        this.averageTag6 = averageTag6;
    }

    public ReviewList(){};

    public ReviewList (String locationName, String locationAddress, String phoneNumber, String locationCategory, String shortCategory, String reviewDescription,
                       double mapx, double mapy, Boolean tag1, Boolean tag2, Boolean tag3, Boolean tag4, Boolean tag5, Boolean tag6, String date, String userName, String userNickName, String key,
                       String imageUrl1, String imageUrl2,String imageUrl3,String imageUrl4,String imageUrl5,String imageUrl6,String imageUrl7,String imageUrl8,String imageUrl9){

        this.location_name = locationName;
        this.location_address = locationAddress;
        this.phone_number = phoneNumber;
        this.location_category = locationCategory;
        this.short_category = shortCategory;
        this.review_description = reviewDescription;
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
        this.userNickName = userNickName;
        this.key = key;
        this.imageUrl1=imageUrl1;
        this.imageUrl2=imageUrl2;
        this.imageUrl3=imageUrl3;
        this.imageUrl4=imageUrl4;
        this.imageUrl5=imageUrl5;
        this.imageUrl6=imageUrl6;
        this.imageUrl7=imageUrl7;
        this.imageUrl8=imageUrl8;
        this.imageUrl9=imageUrl9;

    }
    public ReviewList (String locationName, String locationAddress, String phoneNumber, String locationCategory, String shortCategory, String reviewDescription,
                       double mapx, double mapy, Boolean tag1, Boolean tag2, Boolean tag3, Boolean tag4, Boolean tag5, Boolean tag6, String date, String userName, String userNickName, String key){

        this.location_name = locationName;
        this.location_address = locationAddress;
        this.phone_number = phoneNumber;
        this.location_category = locationCategory;
        this.short_category = shortCategory;
        this.review_description = reviewDescription;
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
        this.userNickName = userNickName;
        this.key = key;
    }


}
