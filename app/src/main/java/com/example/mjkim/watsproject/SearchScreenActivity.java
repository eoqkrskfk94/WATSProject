package com.example.mjkim.watsproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SearchScreenActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);

        Button searchButton = (Button)findViewById(R.id.search_button);


        //검색하기 버튼을 눌렀을때
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SearchScreenActivity.this,SearchDetailScreenActivity.class);
                startActivity(intent);
            }
        });


    }

    // 돌아가기 버튼 눌렀을때 전 화면을 돌아간다
    public void BackButton(View view) {
        finish();
        Intent intent = new Intent(this, MainScreenActivity.class);
        startActivity(intent);
    }
}
