package com.example.mjkim.watsproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LocationDetailScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_detail_screen);

        TextView locationName = (TextView)findViewById(R.id.location_name); //장소 이름
        TextView locationCategory = (TextView)findViewById(R.id.location_category); //장소 분류
        TextView locationAddress = (TextView)findViewById(R.id.location_address); //장소 주소
        TextView locationNumber = (TextView)findViewById(R.id.phone_number); //장소 번호


        //장소 정보 화면 출력
        locationName.setText(getIntent().getExtras().getString("NAME"));
        locationCategory.setText(getIntent().getExtras().getString("CATEGORY"));
        locationAddress.setText(getIntent().getExtras().getString("ADDRESS"));
        locationNumber.setText(getIntent().getExtras().getString("TELEPHONE"));



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
                Intent intent=new Intent(LocationDetailScreen.this,CreateReviewScreenActivity.class);
                startActivity(intent);
            }
        });
    }
}
