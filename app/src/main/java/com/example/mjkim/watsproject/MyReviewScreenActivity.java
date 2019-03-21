package com.example.mjkim.watsproject;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mjkim.watsproject.Review.ReviewList;
import com.example.mjkim.watsproject.Review.UserReviewAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MyReviewScreenActivity extends AppCompatActivity {

    public static ArrayList<ReviewList> reviewLists;
    FirebaseAuth auth;
    private DatabaseReference mDatabase;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    Dialog myDialog;
    ReviewList reviewList;
    static public int totalLocationCount;
    private UserReviewAdapter reviewAdapter;

    private String locationName, key, reviewerName, reviewDate, reviewDescription, locationNumber, userEmail, userName, userNickName, locationCategory, shortCategory, locationAddress;
    private Boolean tag1, tag2, tag3, tag4, tag5, tag6;
    private String imageUrl1, imageUrl2, imageUrl3, imageUrl4, imageUrl5, imageUrl6, imageUrl7, imageUrl8, imageUrl9;
    private double mapx, mapy;


    final Context context;

    {
        context = this;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_review_screen);

        reviewLists = new ArrayList<ReviewList>();
        myDialog = new Dialog(this); //로딩 팝업 변수 선언
        totalLocationCount = 0;



        // 내 리뷰 띄우기
        auth = FirebaseAuth.getInstance();
        final String user_email = new String(auth.getCurrentUser().getEmail()); //Useremail이 현재 사용자 이메일이다.
        System.out.println("이메일 : " + user_email);
        mDatabase = database.getReference();
        final DatabaseReference mymyreview = database.getReference();

        myDialog.setContentView(R.layout.loading_popup);
        myDialog.setCancelable(false);

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));

        myDialog.show(); //회원가입 팝업창.

        final Timer timer2 = new Timer();
        timer2.schedule(new TimerTask() {
            public void run() {
                myDialog.dismiss();
                timer2.cancel(); //this will cancel the timer of the system
            }
        }, 2400); // the timer will count 2.4 seconds....



        mDatabase.child("review lists").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {



                mymyreview.child("review lists").child(dataSnapshot.getKey()).orderByChild("userEmail").equalTo(user_email).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        ReviewList myreview = dataSnapshot.getValue(ReviewList.class);
                        key=dataSnapshot.getKey();
                        locationName = myreview.getLocation_name();
                        reviewDate = myreview.getDate();
                        tag1 = myreview.getTag1();
                        tag2 = myreview.getTag2();
                        tag3 = myreview.getTag3();
                        tag4 = myreview.getTag4();
                        tag5 = myreview.getTag5();
                        tag6 = myreview.getTag6();
                        reviewDescription = myreview.getReview_description();
                        locationNumber = myreview.getPhone_number();
                        locationCategory = myreview.getLocation_category();
                        shortCategory = locationCategory.substring(locationCategory.lastIndexOf(">")+1);
                        locationAddress = myreview.getLocation_address();
                        userNickName = myreview.getUserNickName();
                        mapx = myreview.getMapx();
                        mapy = myreview.getMapy();
                        userEmail = myreview.getUserEmail();
                        imageUrl1 = myreview.getImageUrl1();
                        imageUrl2 = myreview.getImageUrl2();
                        imageUrl3 = myreview.getImageUrl3();
                        imageUrl4 = myreview.getImageUrl4();
                        imageUrl5 = myreview.getImageUrl5();
                        imageUrl6 = myreview.getImageUrl6();
                        imageUrl7 = myreview.getImageUrl7();
                        imageUrl8 = myreview.getImageUrl8();
                        imageUrl9 = myreview.getImageUrl9();

                        reviewList = new ReviewList(locationName, locationAddress, locationNumber, locationCategory, shortCategory, reviewDescription, mapx, mapy,
                                tag1, tag2, tag3, tag4, tag5, tag6, reviewDate, userName, userNickName, key, imageUrl1, imageUrl2, imageUrl3, imageUrl4, imageUrl5, imageUrl6, imageUrl7, imageUrl8, imageUrl9);

                        reviewList.setUserName(myreview.getUserName());

                        reviewLists.add(totalLocationCount, reviewList);

                        totalLocationCount++;

                        // 리뷰 갯수
                        TextView review_count = (TextView) findViewById(R.id.review_count);
                        review_count.setText(Integer.toString(totalLocationCount));

                        ImageView medal = (ImageView)findViewById(R.id.medal);
                        if(totalLocationCount >= 10) medal.setImageResource(R.drawable.medal2);
                        if(totalLocationCount >= 20) medal.setImageResource(R.drawable.medal3);
                        if(totalLocationCount >= 30) medal.setImageResource(R.drawable.medal4);
                        if(totalLocationCount >= 40) medal.setImageResource(R.drawable.medal5);
                        if(totalLocationCount >= 50) medal.setImageResource(R.drawable.medal6);
                        if(totalLocationCount >= 60) medal.setImageResource(R.drawable.medal7);
                        if(totalLocationCount >= 70) medal.setImageResource(R.drawable.medal8);
                        if(totalLocationCount >= 80) medal.setImageResource(R.drawable.medal9);
                        if(totalLocationCount >= 90) medal.setImageResource(R.drawable.medal10);




                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                        ReviewList myreview = dataSnapshot.getValue(ReviewList.class);
//                        System.out.println(dataSnapshot.getKey() + " was " + myreview.getEmail() +myreview.getDate()+ " meters tall.");
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//                        ReviewList myreview = dataSnapshot.getValue(ReviewList.class);
//                        System.out.println(dataSnapshot.getKey() + " was " + myreview.getEmail() +myreview.getDate()+ " meters tall.");
                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                        ReviewList myreview = dataSnapshot.getValue(ReviewList.class);
//                        System.out.println(dataSnapshot.getKey() + " was " + myreview.getEmail() +myreview.getDate()+ " meters tall.");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


        // 리뷰 어댑터 끼우기
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ListView reviewListView = (ListView) findViewById(R.id.my_review_list);
                reviewAdapter = new UserReviewAdapter(MyReviewScreenActivity.this, reviewLists,context,MyReviewScreenActivity.this,totalLocationCount);
                reviewListView.setAdapter(reviewAdapter);



            }
        }, 2000);

    }


    // 뒤로가기 버튼 누르면 메인으로
    public void BackButton(View view) {
        System.out.println("cancel");
        finish();
    }



}
