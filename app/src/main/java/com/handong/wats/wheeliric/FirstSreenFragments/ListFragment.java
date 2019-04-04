package com.handong.wats.wheeliric.FirstSreenFragments;


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

import com.handong.wats.wheeliric.MainScreenActivity;
import com.handong.wats.wheeliric.R;
import com.handong.wats.wheeliric.Review.CategoryAdapter;
import com.handong.wats.wheeliric.Review.ReviewList;
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
    private String categoryName, locationName, key, reviewerName, reviewDate, reviewDescription, locationNumber, userEmail, userName, userNickName, locationCategory, shortCategory, leftCategory, locationAddress;
    private Boolean tag1, tag2, tag3, tag4, tag5, tag6;
    private String imageUrl1, imageUrl2, imageUrl3, imageUrl4, imageUrl5, imageUrl6, imageUrl7, imageUrl8, imageUrl9;
    private double mapx, mapy;
    static public int totalLocationCount;

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

        // 카테고리 이름 받아옴
        MainScreenActivity mainScreenActivity = new MainScreenActivity();
        categoryName = mainScreenActivity.getCategoryName();
        System.out.println("blah : " + categoryName);

        reviewLists = new ArrayList<ReviewList>();
        totalLocationCount = 0;

        mDatabase = database.getReference();
        final DatabaseReference mymyreview = database.getReference();


        mDatabase.child("review lists").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                long length = dataSnapshot.getChildrenCount();
                int[] tag_array = {0,0,0,0,0,0};
                int[] count = {0};

                mymyreview.child("review lists").child(dataSnapshot.getKey()).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        ReviewList myreview = dataSnapshot.getValue(ReviewList.class);
                        key=dataSnapshot.getKey();
                        locationName = myreview.getLocation_name();
                        System.out.println("모든 이름: "+locationName);
                        locationCategory = myreview.getLocation_category();
                        // 카테고리 분류용 변수
                        leftCategory = locationCategory.substring(0, locationCategory.indexOf(">"));
                        System.out.println("leftcategory : " + leftCategory);
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
                        userNickName = myreview.getUserNickName();

                        reviewList = new ReviewList(locationName, locationAddress, locationNumber, locationCategory, shortCategory, reviewDescription, mapx, mapy,
                                tag1, tag2, tag3, tag4, tag5, tag6, reviewDate, userName, userNickName, key, imageUrl1, imageUrl2, imageUrl3, imageUrl4, imageUrl5, imageUrl6, imageUrl7, imageUrl8, imageUrl9);

                        reviewList.setUserName(myreview.getUserName());

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
                        count[0]++;
                        System.out.println("\n첫번째는: " + count[0] + "   두번째는: " + length);

                        // 카테고리 이름이 전체일 때 모든 장소리스트를 다 띄움
                        if(categoryName.equals("전체")) {

                            // 겹치는거는 장소 리스트에 안 넣음
                            if(count[0] == length){

                                if(tag_array[0] > length/2 &&  tag_array[0] != 0) reviewList.setAverageTag1(true);
                                else reviewList.setAverageTag1(false);

                                if(tag_array[1] > length/2 &&  tag_array[1] != 0) reviewList.setAverageTag2(true);
                                else reviewList.setAverageTag2(false);

                                if(tag_array[2] > length/2 &&  tag_array[2] != 0) reviewList.setAverageTag3(true);
                                else reviewList.setAverageTag3(false);

                                if(tag_array[3] > length/2 &&  tag_array[3] != 0) reviewList.setAverageTag4(true);
                                else reviewList.setAverageTag4(false);

                                if(tag_array[4] > length/2 &&  tag_array[4] != 0) reviewList.setAverageTag5(true);
                                else reviewList.setAverageTag5(false);

                                if(tag_array[5] > length/2 &&  tag_array[5] != 0) reviewList.setAverageTag6(true);
                                else reviewList.setAverageTag6(false);

                                reviewLists.add(totalLocationCount, reviewList);
                                totalLocationCount++;

                            }






                         // 카테고리 이름이 전체가 아니면 카테고리에 맞춰서 장소리스트 띄움
                        }
                       else {
                            // 카테고리 이름과 왼쪽 카테고리가 같을 떄만 리뷰리스트 저장
                            if(leftCategory.equals("건강,의료")) leftCategory = "병원,의원";
                            if(leftCategory.equals("관람,체험")) leftCategory = "여행,명소";
                            if(leftCategory.equals("양식") || leftCategory.equals("중식") || leftCategory.equals("한식") ||
                                    leftCategory.equals("일식") || leftCategory.equals("술집")) leftCategory = "음식점";
                            if(leftCategory.equals("종합도소매") || leftCategory.equals("생활,편의")) leftCategory = "쇼핑,유통";
                            if(leftCategory.equals("초등학교")) leftCategory = "교육,학문";
                            if(leftCategory.equals("숙박시설")) leftCategory = "숙박";
                            if(leftCategory.equals(categoryName)) {
                                // 이미 넣은거는 장소 리스트에 안 넣음

                                if(count[0] == length){

                                    if(tag_array[0] > length/2 &&  tag_array[0] != 0) reviewList.setAverageTag1(true);
                                    else reviewList.setAverageTag1(false);

                                    if(tag_array[1] > length/2 &&  tag_array[1] != 0) reviewList.setAverageTag2(true);
                                    else reviewList.setAverageTag2(false);

                                    if(tag_array[2] > length/2 &&  tag_array[2] != 0) reviewList.setAverageTag3(true);
                                    else reviewList.setAverageTag3(false);

                                    if(tag_array[3] > length/2 &&  tag_array[3] != 0) reviewList.setAverageTag4(true);
                                    else reviewList.setAverageTag4(false);

                                    if(tag_array[4] > length/2 &&  tag_array[4] != 0) reviewList.setAverageTag5(true);
                                    else reviewList.setAverageTag5(false);

                                    if(tag_array[5] > length/2 &&  tag_array[5] != 0) reviewList.setAverageTag6(true);
                                    else reviewList.setAverageTag6(false);

                                    reviewLists.add(totalLocationCount, reviewList);
                                    totalLocationCount++;



                                }
                            }
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
        }, 1500);



        // 리뷰 어댑터 끼우기
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ListView reviewListView = (ListView) v.findViewById(R.id.category_list);
                categoryAdapter = new CategoryAdapter(getActivity(), reviewLists, context, getActivity(), totalLocationCount);
                reviewListView.setAdapter(categoryAdapter);
            }
        }, 1500);


        return v;
    }

}
