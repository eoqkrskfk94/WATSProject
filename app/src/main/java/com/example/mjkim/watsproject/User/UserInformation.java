package com.example.mjkim.watsproject.User;

public class UserInformation {
    private String userYear;
    private String userMonth;
    private String userDay;
    private String userNickname;
    private String userEmail;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserYear() {
        return userYear;
    }

    public void setUserYear(String userYear) {
        this.userYear = userYear;
    }

    public String getUserMonth() {
        return userMonth;
    }

    public void setUserMonth(String userMonth) {
        this.userMonth = userMonth;
    }

    public String getUserDay() {
        return userDay;
    }

    public void setUserDay(String userDay) {
        this.userDay = userDay;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public UserInformation(){}

    public UserInformation(String userEmail, String userName, String userYear,String userMonth, String userDay, String userNickname){
        this.userEmail=userEmail;
        this.userName=userName;
        this.userYear=userYear;
        this.userMonth=userMonth;
        this.userDay=userDay;
        this.userNickname=userNickname;

    }
}
