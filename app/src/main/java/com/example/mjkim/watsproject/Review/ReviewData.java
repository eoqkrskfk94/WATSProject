package com.example.mjkim.watsproject.Review;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReviewData {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    ReviewList reviewList = new ReviewList();

    public void saveData(String name, ReviewList temp_reviewList){

        reviewList = temp_reviewList;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        reviewList.setUserEmail(user.getEmail());
        reviewList.setLocation_name(name);

        DatabaseReference usersRef = databaseReference.child("review lists");


        usersRef.child(name).push().setValue(reviewList);// 기본 database 하위 message라는 child에 chatData를 list로 만들기

    }
}

