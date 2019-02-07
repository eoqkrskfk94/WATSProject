package com.example.mjkim.watsproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.mjkim.watsproject.Naver.NaverBlogAdapter;

public class MoreBlogScreenActivity extends AppCompatActivity {

    private NaverBlogAdapter naverBlogAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_blog_screen);

        Button cancelButton = (Button)findViewById(R.id.cancel_button);

        //cancel 버튼 눌렀을때 전 화면을 돌아간다
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        NaverBlogAdapter.select = 0;

        ListView lv = (ListView) findViewById(R.id.more_blog_list);


        if(getIntent().getExtras().getInt("SELECT") == 0){
            naverBlogAdapter = new NaverBlogAdapter(MoreBlogScreenActivity.this, LocationDetailScreenActivity.blogList);
            lv.setAdapter(naverBlogAdapter);
        }

        if(getIntent().getExtras().getInt("SELECT") == 1){
            naverBlogAdapter = new NaverBlogAdapter(MoreBlogScreenActivity.this, LocationDetailFromMapScreenActivity.blogList);
            lv.setAdapter(naverBlogAdapter);
        }




    }
}
