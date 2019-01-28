package com.example.mjkim.watsproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.mjkim.watsproject.Naver.NaverBlogAdapter;
import com.example.mjkim.watsproject.Review.ReviewAdapter;

public class MoreReviewScreenActivity extends AppCompatActivity {

    private ReviewAdapter reviewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_review_screen);

        Button cancelButton = (Button)findViewById(R.id.cancel_button);

        //cancel 버튼 눌렀을때 전 화면을 돌아간다
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        NaverBlogAdapter.select = 0;

        ListView lv = (ListView) findViewById(R.id.more_review_list);


        reviewAdapter = new ReviewAdapter(MoreReviewScreenActivity.this, LocationDetailScreenActivity.reviewLists);
        lv.setAdapter(reviewAdapter);
    }
}
