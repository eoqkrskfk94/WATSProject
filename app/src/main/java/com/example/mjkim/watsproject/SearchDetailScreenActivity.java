package com.example.mjkim.watsproject;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import com.example.mjkim.watsproject.Review.ReviewFirebaseJson;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SearchDetailScreenActivity extends AppCompatActivity {

    private NaverLocationSearch naverLocationSearch;
    private NaverLocationAdapter naverLocationAdapter;
    ArrayList<NaverLocationList> naverLocationLists;
    private ReviewFirebaseJson reviewFirebaseJson = new ReviewFirebaseJson();
    Dialog myDialog;

    int i, save = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail_screen);

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

                myDialog = new Dialog(SearchDetailScreenActivity.this); //로딩 팝업 변수 선언


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
                }, 1500); // the timer will count 2.4 seconds....



                ReviewFirebaseJson.reviewJson.clear(); //검색할때 파이어베이서에 있는 리스트 초기화

                naverLocationSearch = new NaverLocationSearch();
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainScreenActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}
