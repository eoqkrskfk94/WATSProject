package com.example.mjkim.watsproject;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.mjkim.watsproject.Naver.NaverBlogAdapter;
import com.example.mjkim.watsproject.Naver.NaverBlogList;
import com.example.mjkim.watsproject.Naver.NaverBlogSearch;
import com.example.mjkim.watsproject.Review.ReviewAdapter;
import com.example.mjkim.watsproject.Review.ReviewFirebaseJson;
import com.example.mjkim.watsproject.Review.ReviewList;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class LocationDetailScreenActivity extends AppCompatActivity {

    Dialog myDialog;
    ScrollView scrollView;
    private String blogName;
    public static ArrayList<NaverBlogList> blogList;
    public static ArrayList<ReviewList> reviewLists;
    private NaverBlogSearch naverBlogSearch;
    private NaverBlogAdapter naverBlogAdapter;
    ViewPager viewPager;
    LinearLayout sliderDots;
    public int dotCounts;
    public ImageView[] dots;
    private ReviewAdapter reviewAdapter;
    public static int length; //리뷰 갯수

    //리뷰 변수들 선언
    Boolean tag1, tag2, tag3, tag4, tag5, tag6;
    String locationName, locationCategory, locationAddress, locationNumber, reviewDescription, userEmail, userName, key, reviewDate;
    double locationMapx, locationMapy;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail_screen);



        // 제일 위부터 보기
        scrollView = new ScrollView(this);
        scrollView.findViewById(R.id.scroll_view);
        scrollView.scrollTo(0,600);


        myDialog = new Dialog(this); //회원가입 팝업 변수 선언
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
        final String location_name, location_category, location_addess, location_number;
        final Integer location_x, location_y;

        location_name = getIntent().getExtras().getString("NAME");
        location_category = getIntent().getExtras().getString("CATEGORY");
        location_addess = getIntent().getExtras().getString("ADDRESS");
        location_number = getIntent().getExtras().getString("TELEPHONE");
        location_x = getIntent().getExtras().getInt("MAPX");
        location_y = getIntent().getExtras().getInt("MAPY");

        locationNameText.setText(location_name);
        locationCategoryText.setText(location_category);
        locationAddressText.setText(location_addess);
        locationNumberText.setText(location_number);

        //전화번호 눌렀을때 통화 기능
        locationNumberText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getIntent().getExtras().getString("TELEPHONE")));
                startActivity(intent1);
            }
        });



        Button backButton = (Button)findViewById(R.id.back_button);
        Button createReviewButton = (Button)findViewById(R.id.c_review_button);

        //돌아가기 버튼 눌렀을때 전 화면을 돌아간다
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //후기 작성하기 버튼을 눌렀을때 후기 작성 페이지로 이동
        createReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LocationDetailScreenActivity.this,CreateReviewScreenActivity.class);
                intent.putExtra("NAME", location_name);
                intent.putExtra("CATEGORY", location_category);
                intent.putExtra("ADDRESS", location_addess);
                intent.putExtra("TELEPHONE", location_number);
                intent.putExtra("MAPX", location_x);
                intent.putExtra("MAPY", location_y);
                startActivity(intent);
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

        naverBlogAdapter = new NaverBlogAdapter(LocationDetailScreenActivity.this, blogList);
        blogListView.setAdapter(naverBlogAdapter);

        Button moreBlogButton = (Button)findViewById(R.id.see_more_blog);  //블로그 전체보기 버튼
        moreBlogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openBlogTab();}

        });


        //해당 장소의 리뷰들을 리스트로 저장하고 JSON 파싱을한다
        int[] tag_array = {0,0,0,0,0,0};

        if(ReviewFirebaseJson.reviewJson.size() > intent.getExtras().getInt("NUMBER")){

            reviewLists = new ArrayList<ReviewList>();
            int num = 0;


            String json = ReviewFirebaseJson.reviewJson.get(intent.getExtras().getInt("NUMBER")).getReview_json_string();
            length = ReviewFirebaseJson.reviewJson.get(intent.getExtras().getInt("NUMBER")).getReview_count();
            JSONArray IDs = ReviewFirebaseJson.reviewJson.get(intent.getExtras().getInt("NUMBER")).getReview_json_userID();
            String Fire_locationName = ReviewFirebaseJson.reviewJson.get(intent.getExtras().getInt("NUMBER")).getLocation_name();

            try{
                JSONObject obj = new JSONObject(json);


                for (int i = 0; i < length; i++) {
                    JSONObject jsonObj = obj.getJSONObject(IDs.getString(i));
                    locationName = jsonObj.getString("location_name");
                    locationCategory = jsonObj.getString("location_category");
                    locationAddress = jsonObj.getString("location_address");
                    locationMapx = jsonObj.getInt("mapx");
                    locationMapy = jsonObj.getInt("mapy");
                    locationNumber = jsonObj.getString("phone_number");

                    tag1 = jsonObj.getBoolean("tag1");
                    if(tag1 == true) tag_array[0]  = tag_array[0] +  1;
                    tag2 = jsonObj.getBoolean("tag2");
                    if(tag2 == true) tag_array[1]  = tag_array[1] +  1;
                    tag3 = jsonObj.getBoolean("tag3");
                    if(tag3 == true) tag_array[2]  = tag_array[2] +  1;
                    tag4 = jsonObj.getBoolean("tag4");
                    if(tag4 == true) tag_array[3]  = tag_array[3] +  1;
                    tag5 = jsonObj.getBoolean("tag5");
                    if(tag5 == true) tag_array[4]  = tag_array[4] +  1;
                    tag6 = jsonObj.getBoolean("tag6");
                    if(tag6 == true) tag_array[5]  = tag_array[5] +  1;

                    reviewDescription = jsonObj.getString("review_description");
                    userName = jsonObj.getString("userName");
                    userEmail = jsonObj.getString("email");
                    reviewDate = jsonObj.getString("date");

/*                    imageUrl1 = jsonObj.getString("imageUrl1");
                    imageUrl2 = jsonObj.getString("imageUrl2");
                    imageUrl3 = jsonObj.getString("imageUrl3");
                    imageUrl4 = jsonObj.getString("imageUrl4");
                    imageUrl5 = jsonObj.getString("imageUrl5");
                    imageUrl6 = jsonObj.getString("imageUrl6");
                    imageUrl7 = jsonObj.getString("imageUrl7");
                    imageUrl8 = jsonObj.getString("imageUrl8");
                    imageUrl9 = jsonObj.getString("imageUrl9");*/



                    if(intent.getExtras().getString("NAME").equals(location_name)) {

                        reviewLists.add(num++, new ReviewList(intent.getExtras().getString("NAME"), locationAddress, locationNumber, locationCategory, reviewDescription, locationMapx, locationMapx,
                                tag1, tag2, tag3, tag4, tag5, tag6, reviewDate, userName, key));

                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }


        //리뷰 보기
        ReviewAdapter.select = 1;

        ListView reviewListView = (ListView) findViewById(R.id.review_list);
        if(reviewLists.size() == 0) {

            reviewListView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 50));
        }
        else if(reviewLists.size() == 1) {

            reviewListView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 600));
        }
        else if(reviewLists.size() == 2) {

            reviewListView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1500));
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ListView reviewListView = (ListView) findViewById(R.id.review_list);
                reviewAdapter = new ReviewAdapter(LocationDetailScreenActivity.this, reviewLists);
                reviewListView.setAdapter(reviewAdapter);
            }
        }, 900);

        Button moreReviewButton = (Button)findViewById(R.id.see_more_review);  //리뷰 전체보기 버튼
        moreReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openReviewTab();}

        });


        //태그 기준 설정 및 출력
        if(tag_array[0] > length/2 &&  tag_array[0] != 0) tagShow1.setImageResource(R.drawable.restroom);
        System.out.println("태그1: " + tag_array[0]);
        if(tag_array[1] > length/2 &&  tag_array[1] != 0) tagShow2.setImageResource(R.drawable.parking);
        System.out.println("태그2: " + tag_array[1]);
        if(tag_array[2] > length/2 &&  tag_array[2] != 0) tagShow3.setImageResource(R.drawable.elevator);
        System.out.println("태그3: " + tag_array[2]);
        if(tag_array[3] > length/2 &&  tag_array[3] != 0) tagShow4.setImageResource(R.drawable.slope);
        System.out.println("태그4: " + tag_array[3]);
        if(tag_array[4] > length/2 &&  tag_array[4] != 0) tagShow5.setImageResource(R.drawable.table);
        System.out.println("태그5: " + tag_array[4]);
        if(tag_array[5] > length/2 &&  tag_array[5] != 0) tagShow6.setImageResource(R.drawable.assistant);
        System.out.println("태그6: " + tag_array[5]);



    }


    //블로그 전체보기 버튼 기능
    public void openBlogTab(){
        Intent intent = new Intent(this, MoreBlogScreenActivity.class);
        startActivity(intent);
    }

    private class ActivityMainBinding {
    }

    //리뷰 전체보기 버튼 기능
    public void openReviewTab(){
        Intent intent = new Intent(this, MoreReviewScreenActivity.class);
        startActivity(intent);
    }
}
