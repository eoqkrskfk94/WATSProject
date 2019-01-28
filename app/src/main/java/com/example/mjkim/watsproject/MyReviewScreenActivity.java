package com.example.mjkim.watsproject;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mjkim.watsproject.Review.ReviewList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyReviewScreenActivity extends AppCompatActivity {

    public static ArrayList<ReviewList> reviewLists;
    FirebaseAuth auth;
    private DatabaseReference mDatabase;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    Dialog myDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_review_screen);

        reviewLists = new ArrayList<ReviewList>();
        myDialog = new Dialog(this); //로딩 팝업 변수 선언

        // 내 리뷰 띄우기
        auth = FirebaseAuth.getInstance();
        final String userEmail = new String(auth.getCurrentUser().getEmail()); //Useremail이 현재 사용자 이메일이다.
        mDatabase = database.getReference();
        final DatabaseReference mymyreview = database.getReference();

        myDialog.setContentView(R.layout.loading_popup);
        myDialog.setCancelable(false);

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));

        myDialog.show(); //회원가입 팝업창.







    }
}
