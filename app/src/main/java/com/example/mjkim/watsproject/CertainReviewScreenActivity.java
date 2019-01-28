package com.example.mjkim.watsproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CertainReviewScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certain_review_screen);


        Button backButton = (Button)findViewById(R.id.back_button); //돌아가기 버튼 선언

        //돌아가기 버튼 눌렀을때 전 화면을 돌아간다
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        TextView userName = (TextView) findViewById(R.id.vi_name);
        TextView reviewDate = (TextView) findViewById(R.id.vi_date);
        TextView reviewDescription = (TextView) findViewById(R.id.vi_description);

        userName.setText(getIntent().getExtras().getString("Name"));
        reviewDate.setText(getIntent().getExtras().getString("Date"));
        reviewDescription.setText(getIntent().getExtras().getString("Review"));

        ImageView tagShow1 = (ImageView)findViewById(R.id.tag_done_1); //태그 이미지 선언
        ImageView tagShow2 = (ImageView)findViewById(R.id.tag_done_2);
        ImageView tagShow3 = (ImageView)findViewById(R.id.tag_done_3);
        ImageView tagShow4 = (ImageView)findViewById(R.id.tag_done_4);
        ImageView tagShow5 = (ImageView)findViewById(R.id.tag_done_5);
        ImageView tagShow6 = (ImageView)findViewById(R.id.tag_done_6);

        if(getIntent().getExtras().getBoolean("Tag1") == true) tagShow1.setImageResource(R.drawable.restroom);
        if(getIntent().getExtras().getBoolean("Tag2") == true) tagShow2.setImageResource(R.drawable.parking);
        if(getIntent().getExtras().getBoolean("Tag3") == true) tagShow3.setImageResource(R.drawable.elevator);
        if(getIntent().getExtras().getBoolean("Tag4") == true) tagShow4.setImageResource(R.drawable.slope);
        if(getIntent().getExtras().getBoolean("Tag5") == true) tagShow5.setImageResource(R.drawable.table);
        if(getIntent().getExtras().getBoolean("Tag6") == true) tagShow6.setImageResource(R.drawable.assistant);


    }
}
