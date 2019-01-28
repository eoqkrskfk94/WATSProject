package com.example.mjkim.watsproject;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private String locationName, key, reviewerName, reviewDate, reviewDescription, locationNumber, userEmail, userName, locationCategory;
    private Boolean tag1, tag2, tag3, tag4, tag5, tag6;


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
        }, 2000); // the timer will count 5 seconds....



        mDatabase.child("review lists").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                System.out.println("이메일2 : " + mymyreview.child("review lists").child(dataSnapshot.getKey()).orderByChild("email"));


                mymyreview.child("review lists").child(dataSnapshot.getKey()).orderByChild("email").equalTo(user_email).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        ReviewList myreview = dataSnapshot.getValue(ReviewList.class);
                        key=dataSnapshot.getKey();
                        System.out.println("삭제삭제"+key);
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
                        userEmail = myreview.getUserEmail();
/*                        imageUrl1 = myreview.getImageUrl1();
                        imageUrl2 = myreview.getImageUrl2();
                        imageUrl3 = myreview.getImageUrl3();
                        imageUrl4 = myreview.getImageUrl4();
                        imageUrl5 = myreview.getImageUrl5();
                        imageUrl6 = myreview.getImageUrl6();
                        imageUrl7 = myreview.getImageUrl7();
                        imageUrl8 = myreview.getImageUrl8();
                        imageUrl9 = myreview.getImageUrl9();*/

                        reviewList = new ReviewList();

                        reviewList.setKey(key);
                        reviewList.setLocation_name(locationName);
                        reviewList.setTag1(tag1);
                        reviewList.setTag2(tag2);
                        reviewList.setTag3(tag3);
                        reviewList.setTag4(tag4);
                        reviewList.setTag5(tag5);
                        reviewList.setTag6(tag6);
                        reviewList.setReview_description(reviewDescription);
                        reviewList.setDate(reviewDate);
/*                        reviewList.setImageUrl1(imageUrl1);
                        reviewList.setImageUrl2(imageUrl2);
                        reviewList.setImageUrl3(imageUrl3);
                        reviewList.setImageUrl4(imageUrl4);
                        reviewList.setImageUrl5(imageUrl5);
                        reviewList.setImageUrl6(imageUrl6);
                        reviewList.setImageUrl7(imageUrl7);
                        reviewList.setImageUrl8(imageUrl8);
                        reviewList.setImageUrl9(imageUrl9);*/


                        //System.out.println("reviewList : " + reviewList.getLocation_name());
                        reviewList.setLocation_name(locationName);
                        reviewLists.add(totalLocationCount, reviewList);

                        totalLocationCount++;

                        // 리뷰 갯수
                        TextView review_count = (TextView) findViewById(R.id.review_count);
                        review_count.setText(Integer.toString(totalLocationCount));

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
        }, 2300);







    }
}
