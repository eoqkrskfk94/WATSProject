package com.example.mjkim.watsproject;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mjkim.watsproject.Naver.NaverLocationAdapter;
import com.example.mjkim.watsproject.Naver.NaverLocationList;
import com.example.mjkim.watsproject.Naver.NaverLocationSearch;

import java.util.ArrayList;

public class SearchDetailScreenActivity extends AppCompatActivity {

    private NaverLocationSearch naverLocationSearch;
    private NaverLocationAdapter naverLocationAdapter;
    ArrayList<NaverLocationList> naverLocationLists;

    int i, save = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_detail_screen);

        Button cancelButton = (Button)findViewById(R.id.cancel_button);
        Button searchButton = (Button)findViewById(R.id.search_button);

        //cancel 버튼 눌렀을때 전 화면을 돌아간다
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




        //검색하기 버튼을 눌렀을때
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                naverLocationSearch = new NaverLocationSearch();
                naverLocationLists = new ArrayList<NaverLocationList>();
                naverLocationLists = new ArrayList<NaverLocationList>();

                EditText searchBar = (EditText) findViewById(R.id.editSearch);

                try {
                    naverLocationLists = naverLocationSearch.execute(searchBar.getText().toString()).get();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(naverLocationLists.isEmpty()) {
                    Toast.makeText(SearchDetailScreenActivity.this, "검색 결과가 없습니다",
                            Toast.LENGTH_SHORT).show();
                }



                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ListView list = (ListView) findViewById(R.id.location_list);
                        naverLocationAdapter = new NaverLocationAdapter(SearchDetailScreenActivity.this, naverLocationLists, save);
                        list.setAdapter(naverLocationAdapter);
                    }
                }, 1000);






            }
        });



    }
}
