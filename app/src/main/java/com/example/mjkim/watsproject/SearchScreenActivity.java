package com.example.mjkim.watsproject;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mjkim.watsproject.Naver.NaverLocationAdapter;
import com.example.mjkim.watsproject.Naver.NaverLocationList;
import com.example.mjkim.watsproject.Naver.NaverLocationSearch;

import java.util.ArrayList;

public class SearchScreenActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_screen);

        Button backButton = (Button)findViewById(R.id.back_button); //돌아가기 버튼 선언
        Button searchButton = (Button)findViewById(R.id.search_button);

        //돌아가기 버튼 눌렀을때 전 화면을 돌아간다
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //검색하기 버튼을 눌렀을때
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SearchScreenActivity.this,SearchDetailScreenActivity.class);
                startActivity(intent);
            }
        });


    }
}
