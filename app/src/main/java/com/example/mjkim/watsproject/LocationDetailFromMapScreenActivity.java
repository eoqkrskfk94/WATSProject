package com.example.mjkim.watsproject;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.mjkim.watsproject.Naver.NaverBlogAdapter;
import com.example.mjkim.watsproject.Naver.NaverBlogList;
import com.example.mjkim.watsproject.Naver.NaverBlogSearch;
import com.example.mjkim.watsproject.Review.MapReviewAdapter;
import com.example.mjkim.watsproject.Review.ReviewAdapter;
import com.example.mjkim.watsproject.Review.ReviewList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class LocationDetailFromMapScreenActivity extends AppCompatActivity {

    Dialog myDialog;
    ScrollView scrollView;
    private String blogName;
    public static ArrayList<NaverBlogList> blogList;
    public static ArrayList<ReviewList> reviewLists;
    private NaverBlogSearch naverBlogSearch;
    private NaverBlogAdapter naverBlogAdapter;
    private MapReviewAdapter mapReviewAdapter;
    public static int length; //리뷰 갯수
    private FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    FirebaseAuth auth;
    private DatabaseReference mDatabase;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private int num;

    //리뷰 변수들 선언
    Boolean tag1, tag2, tag3, tag4, tag5, tag6;
    String locationName, locationCategory, shortCategory, locationAddress, locationNumber, reviewDescription, userEmail, userName, userNickName, key, reviewDate;
    double locationMapx, locationMapy;

    String location_name, location_category, short_category, location_address, location_number;
    double location_x, location_y;
    String imageUrl1,imageUrl2,imageUrl3,imageUrl4,imageUrl5,imageUrl6,imageUrl7,imageUrl8,imageUrl9;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail_from_map_screen);

        mAuth = FirebaseAuth.getInstance(); // 로그인 작업의 onCreate 메소드에서 FirebaseAuth 개체의 공유 인스턴스를 가져옵니다
        currentUser = mAuth.getCurrentUser();

        // 제일 위부터 보기
        scrollView = new ScrollView(this);
        scrollView.findViewById(R.id.scroll_view);
        scrollView.scrollTo(0,600);


        myDialog = new Dialog(this); //팝업 변수 선언
        Intent intent = getIntent();
        blogList = new ArrayList<NaverBlogList>(); //블로그 리스트 선언
        naverBlogSearch = new NaverBlogSearch();
        reviewLists = new ArrayList<ReviewList>(); //리뷰 리스트 선언
        key = "";


        TextView locationNameText = (TextView)findViewById(R.id.location_name); //장소 이름
        TextView locationCategoryText = (TextView)findViewById(R.id.location_category); //장소 분류
        TextView locationAddressText = (TextView)findViewById(R.id.location_address); //장소 주소
        TextView locationNumberText = (TextView)findViewById(R.id.phone_number); //장소 번호

        ImageView tagShow1 = (ImageView)findViewById(R.id.tag_done_1); //태그 이미지 선언
        ImageView tagShow2 = (ImageView)findViewById(R.id.tag_done_2);
        ImageView tagShow3 = (ImageView)findViewById(R.id.tag_done_3);
        ImageView tagShow4 = (ImageView)findViewById(R.id.tag_done_4);
        ImageView tagShow5 = (ImageView)findViewById(R.id.tag_done_5);
        ImageView tagShow6 = (ImageView)findViewById(R.id.tag_done_6);

        tagShow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //태그1 눌렀을때 정보 팝업창 띄우기

                myDialog.setContentView(R.layout.tag_restroom_popup);
                Button closeButton = (Button) myDialog.findViewById(R.id.ok_button);

                //닫기 버튼을 눌렀을때
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) { myDialog.dismiss(); }});

                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                myDialog.show();
            }
        });

        tagShow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //태그2 눌렀을때 정보 팝업창 띄우기

                myDialog.setContentView(R.layout.tag_parking_popup);
                Button closeButton = (Button) myDialog.findViewById(R.id.ok_button);

                //닫기 버튼을 눌렀을때
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) { myDialog.dismiss(); }});

                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                myDialog.show();
            }
        });

        tagShow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //태그3 눌렀을때 정보 팝업창 띄우기

                myDialog.setContentView(R.layout.tag_elevator_popup);
                Button closeButton = (Button) myDialog.findViewById(R.id.ok_button);

                //닫기 버튼을 눌렀을때
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) { myDialog.dismiss(); }});

                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                myDialog.show();
            }
        });

        tagShow4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myDialog.setContentView(R.layout.tag_slope_popup);
                Button closeButton = (Button) myDialog.findViewById(R.id.ok_button);

                //닫기 버튼을 눌렀을때
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) { myDialog.dismiss(); }});

                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                myDialog.show();
            }
        });

        tagShow5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //태그5 눌렀을때 정보 팝업창 띄우기

                myDialog.setContentView(R.layout.tag_table_popup);
                Button closeButton = (Button) myDialog.findViewById(R.id.ok_button);

                //닫기 버튼을 눌렀을때
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) { myDialog.dismiss(); }});

                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                myDialog.show();
            }
        });

        tagShow6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //태그6 눌렀을때 정보 팝업창 띄우기

                myDialog.setContentView(R.layout.tag_assistant_popup);
                Button closeButton = (Button) myDialog.findViewById(R.id.ok_button);

                //닫기 버튼을 눌렀을때
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) { myDialog.dismiss(); }});

                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                myDialog.show();
            }
        });





        //장소 정보 화면 출력
//        String location_name, location_category, location_addess, location_number;
//        double location_x, location_y;

        location_name = getIntent().getExtras().getString("NAME");
        location_category = getIntent().getExtras().getString("CATEGORY");
        short_category = location_category.substring(location_category.lastIndexOf(">")+1);
        location_address = getIntent().getExtras().getString("ADDRESS");
        location_number = getIntent().getExtras().getString("TELEPHONE");
        location_x = getIntent().getExtras().getDouble("MAPX");
        location_y = getIntent().getExtras().getDouble("MAPY");

        System.out.println("newmap3 : " + location_address + "  " + location_x + "  " + location_y);

        int index = location_name.indexOf(" , ");
        String correct_location_name = location_name.substring(0, index);

        locationNameText.setText(correct_location_name);
        locationCategoryText.setText(location_category);
        locationAddressText.setText(location_address);
        locationNumberText.setText(location_number);

        // 주소 누르면 지도 뜸
        locationAddressText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationDetailFromMapScreenActivity.this, WatchLocationActivity.class);
                intent.putExtra("NAME", location_name);
                intent.putExtra("CATEGORY", location_category);
                intent.putExtra("ADDRESS", location_address);
                intent.putExtra("TELEPHONE", location_number);
                intent.putExtra("MAPX", location_x);
                intent.putExtra("MAPY", location_y);
                startActivity(intent);
            }
        });

        // 전화번호 눌렀을때 통화 기능
        locationNumberText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getIntent().getExtras().getString("TELEPHONE")));
                startActivity(intent);
            }
        });



        Button backButton = (Button)findViewById(R.id.back_button);
        Button createReviewButton = (Button)findViewById(R.id.c_review_button);

        // 돌아가기 버튼 눌렀을때 전 화면을 돌아간다
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LocationDetailFromMapScreenActivity.this, MainScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });


        //후기 작성하기 버튼을 눌렀을때 후기 작성 페이지로 이동
        createReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentUser == null){
                    myDialog.setContentView(R.layout.login_popup);
                    myDialog.setCancelable(false);

                    Button loginButton = (Button) myDialog.findViewById(R.id.login_button);
                    Button closeButton = (Button) myDialog.findViewById(R.id.cancel_button);

                    //로그인 버튼을 눌렀을때
                    loginButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent=new Intent(LocationDetailFromMapScreenActivity.this,LoginScreenActivity.class);
                            startActivity(intent);
                        }
                    });

                    //닫기 버튼을 눌렀을때
                    closeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            myDialog.dismiss();
                        }
                    });

                    myDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                    myDialog.show();
                }

                else {
                    Intent intent = new Intent(LocationDetailFromMapScreenActivity.this, CreateReviewScreenActivity.class);
                    int index = location_name.indexOf(" , ");
                    String new_location_name = location_name.substring(0, index);
                    intent.putExtra("NAME", new_location_name);
                    intent.putExtra("CATEGORY", location_category);
                    intent.putExtra("ADDRESS", location_address);
                    intent.putExtra("TELEPHONE", location_number);
                    intent.putExtra("MAPX", location_x);
                    intent.putExtra("MAPY", location_y);
                    startActivity(intent);
                }
            }
        });


        //블로그 리스트 출력
        try {
            blogList = naverBlogSearch.execute(intent.getExtras().getString("NAME")).get();
        }catch (Exception e){
            e.printStackTrace();
        }

        NaverBlogAdapter.select = 1;

        ListView blogListView = (ListView) findViewById(R.id.blog_list);


        // 동적으로 리스트뷰 높이 할당
        if(blogList.size() == 0) {
            blogListView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 50));
        }
        else if(blogList.size() == 1) {
            blogListView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 700));
        }
        else if(blogList.size() == 2) {
            blogListView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1500));
        }

        naverBlogAdapter = new NaverBlogAdapter(LocationDetailFromMapScreenActivity.this, blogList);
        blogListView.setAdapter(naverBlogAdapter);

        Button moreBlogButton = (Button)findViewById(R.id.see_more_blog);  //블로그 전체보기 버튼
        moreBlogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openBlogTab();}

        });

        reviewLists = new ArrayList<ReviewList>();

        // 리뷰 전부 가지고 오기
        auth = FirebaseAuth.getInstance();
        mDatabase = database.getReference();
        mDatabase.child("review lists").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                long length = dataSnapshot.getChildrenCount();
                System.out.println("길이용 : " +  dataSnapshot.getChildrenCount());
                int[] tag_array = {0,0,0,0,0,0};
                num = 0;

                mDatabase.child("review lists").child(dataSnapshot.getKey()).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        ReviewList myreview = dataSnapshot.getValue(ReviewList.class);


                        //주소 빼고 이름만 사용
                        int index = myreview.getLocation_name().indexOf(" , ");
                        String location_name = myreview.getLocation_name().substring(0, index);

                        System.out.println("장보정보: " + myreview.getLocation_name() + myreview.getLocation_category());
                        locationAddress = myreview.getLocation_address();
                        locationNumber = myreview.getPhone_number();
                        locationCategory = myreview.getLocation_category();
                        reviewDescription = myreview.getReview_description();
                        locationMapx = myreview.getMapx();
                        locationMapy = myreview.getMapy();
                        userName = myreview.getUserName();
                        reviewDate = myreview.getDate();
                        tag1 = myreview.getTag1();
                        tag2 = myreview.getTag2();
                        tag3 = myreview.getTag3();
                        tag4 = myreview.getTag4();
                        tag5 = myreview.getTag5();
                        tag6 = myreview.getTag6();
                        imageUrl1 = myreview.getImageUrl1();
                        imageUrl2 = myreview.getImageUrl2();
                        imageUrl3 = myreview.getImageUrl3();
                        imageUrl4 = myreview.getImageUrl4();
                        imageUrl5 = myreview.getImageUrl5();
                        imageUrl6 = myreview.getImageUrl6();
                        imageUrl7 = myreview.getImageUrl7();
                        imageUrl8 = myreview.getImageUrl8();
                        imageUrl9 = myreview.getImageUrl9();


                        String nameAndAddress = location_name + " , " + locationAddress;
                        System.out.println("여기는 될까요2" + intent.getExtras().getString("NAME"));

                        if(intent.getExtras().getString("NAME").equals(nameAndAddress)) {
                            System.out.println("여기는 될까요?????????");
                            reviewLists.add(num++, new ReviewList(intent.getExtras().getString("NAME"), locationAddress, locationNumber, locationCategory, shortCategory, reviewDescription, locationMapx, locationMapy,
                                    tag1, tag2, tag3, tag4, tag5, tag6, reviewDate, userName, userNickName, key,imageUrl1,imageUrl2,imageUrl3,imageUrl4,imageUrl5,imageUrl6,
                                    imageUrl7,imageUrl8,imageUrl9));

                        }

                        System.out.println("리스트 길이: " + reviewLists.size());
                    }


                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        System.out.println("리스트 길이3: " + reviewLists.size());
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        System.out.println("리스트 길이5: " + reviewLists.size());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        System.out.println("errororororororor");
                    }
                });

                System.out.println("리스트 길이2: " + reviewLists.size());

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




        //리뷰 보기
        //ReviewAdapter.select = 1;
        System.out.println("리스트 길이6: " + reviewLists.size());




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ListView reviewListView = (ListView) findViewById(R.id.review_list);
                if(reviewLists.size() == 0) {

                    reviewListView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 50));
                }
                else if(reviewLists.size() == 1) {

                    reviewListView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                }
                else if(reviewLists.size() >= 2) {

                    View view = getLayoutInflater().inflate(R.layout.review_list_box, null);
                    View view2 = view.findViewById(R.id.list_size);
                    reviewListView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, view2.getLayoutParams().height * 2 + 150));
                }

                mapReviewAdapter = new MapReviewAdapter(LocationDetailFromMapScreenActivity.this, reviewLists);
                reviewListView.setAdapter(mapReviewAdapter);
                System.out.println("리스트 길이3: " + reviewLists.size());
            }
        }, 900);

        Button moreReviewButton = (Button)findViewById(R.id.see_more_review);  //리뷰 전체보기 버튼
        moreReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openReviewTab();}

        });


        //태그 기준 설정 및 출력
        if(intent.getExtras().getBoolean("TAG1")) tagShow1.setImageResource(R.drawable.restroom);
        if(intent.getExtras().getBoolean("TAG2")) tagShow2.setImageResource(R.drawable.parking);
        if(intent.getExtras().getBoolean("TAG3")) tagShow3.setImageResource(R.drawable.elevator);
        if(intent.getExtras().getBoolean("TAG4")) tagShow4.setImageResource(R.drawable.slope);
        if(intent.getExtras().getBoolean("TAG5")) tagShow5.setImageResource(R.drawable.table);
        if(intent.getExtras().getBoolean("TAG6")) tagShow6.setImageResource(R.drawable.assistant);
    }

    //블로그 전체보기 버튼 기능
    public void openBlogTab(){
        Intent intent = new Intent(this, MoreBlogScreenActivity.class);
        intent.putExtra("SELECT", 1);
        startActivity(intent);
    }

    private class ActivityMainBinding {
    }

    //리뷰 전체보기 버튼 기능
    public void openReviewTab(){
        Intent intent = new Intent(this, MoreReviewScreenActivity.class);
        intent.putExtra("SELECT", 1);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainScreenActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}


