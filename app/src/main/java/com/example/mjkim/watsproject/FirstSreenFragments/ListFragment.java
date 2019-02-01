package com.example.mjkim.watsproject.FirstSreenFragments;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mjkim.watsproject.R;
import com.example.mjkim.watsproject.Review.CategoryAdapter;
import com.example.mjkim.watsproject.Review.ReviewList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    private CategoryAdapter categoryAdapter;
    Dialog myDialog;
    // 리뷰 정보 담을 변수
    private ReviewList reviewList;
    public static ArrayList<ReviewList> reviewLists;
    FirebaseAuth auth;
    private DatabaseReference mDatabase;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private String locationName, key, reviewerName, reviewDate, reviewDescription, locationNumber, userEmail, userName, locationCategory, locationAddress;
    private Boolean tag1, tag2, tag3, tag4, tag5, tag6;
    private String imageUrl1, imageUrl2, imageUrl3, imageUrl4, imageUrl5, imageUrl6, imageUrl7, imageUrl8, imageUrl9;
    private double mapx, mapy;
    static public int totalLocationCount;
    int count;

    final Context context;

    {
        context = getContext();
    }



    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container,false);


        reviewLists = new ArrayList<ReviewList>();
        totalLocationCount = 0;

        mDatabase = database.getReference();
        final DatabaseReference mymyreview = database.getReference();


        mDatabase.child("review lists").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                long length = dataSnapshot.getChildrenCount();
                int[] tag_array = {0,0,0,0,0,0};
                count = 0;

                mymyreview.child("review lists").child(dataSnapshot.getKey()).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        ReviewList myreview = dataSnapshot.getValue(ReviewList.class);
                        key=dataSnapshot.getKey();
                        locationName = myreview.getLocation_name();
                        System.out.println("모든 이름: "+locationName);
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
                        locationAddress = myreview.getLocation_address();
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

                        reviewList = new ReviewList(locationName, locationAddress, locationNumber, locationCategory, reviewDescription, mapx, mapy,
                                tag1, tag2, tag3, tag4, tag5, tag6, reviewDate, userName, key, imageUrl1, imageUrl2, imageUrl3, imageUrl4, imageUrl5, imageUrl6, imageUrl7, imageUrl8, imageUrl9);

                        reviewList.setUserName(myreview.getUserName());

                        // 겹치는거는 장소 리스트에 안 넣음
                        if(totalLocationCount != 0) {
                            for(int i = 0; i < totalLocationCount; i++) {
                                if(reviewLists.get(i).getLocation_name().equals(locationName)) { }
                                else {
                                    if(i == totalLocationCount-1) {
                                        reviewLists.add(totalLocationCount, reviewList);
                                        totalLocationCount++;
                                    }
                                }
                            }
                        } else {
                            reviewLists.add(totalLocationCount, reviewList);
                            totalLocationCount++;
                        }


                        if(myreview.getTag1() == true) tag_array[0]  = tag_array[0] +  1;
                        System.out.println("태그1 : " +  tag_array[0]);
                        if(myreview.getTag2() == true) tag_array[1]  = tag_array[1] +  1;
                        System.out.println("태그2 : " +  tag_array[1]);
                        if(myreview.getTag3() == true) tag_array[2]  = tag_array[2] +  1;
                        System.out.println("태그3 : " +  tag_array[2]);
                        if(myreview.getTag4() == true) tag_array[3]  = tag_array[3] +  1;
                        System.out.println("태그4 : " +  tag_array[3]);
                        if(myreview.getTag5() == true) tag_array[4]  = tag_array[4] +  1;
                        System.out.println("태그5 : " +  tag_array[4]);
                        if(myreview.getTag6() == true) tag_array[5]  = tag_array[5] +  1;
                        System.out.println("태그6 : " +  tag_array[5]);
                        count++;

                        if(count == length){

                        }

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

        myDialog = new Dialog(getContext()); //로딩 팝업 변수 선언
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



        // 리뷰 어댑터 끼우기
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ListView reviewListView = (ListView) v.findViewById(R.id.category_list);
                categoryAdapter = new CategoryAdapter(getActivity(), reviewLists, context, getActivity(), totalLocationCount);
                reviewListView.setAdapter(categoryAdapter);
            }
        }, 2300);


        return v;
    }

}
