package com.example.mjkim.watsproject;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.mjkim.watsproject.FirstSreenFraments.ListFragment;
import com.example.mjkim.watsproject.FirstSreenFraments.MapFragment;
import com.example.mjkim.watsproject.FirstSreenFraments.MypageFragment;
import com.example.mjkim.watsproject.OtherClasses.BackPressCloseHandler;

public class FirstScreenActivity extends AppCompatActivity {

    private BackPressCloseHandler backPressCloseHandler;


    private BottomNavigationView mMainNav; //하단 메뉴 아이콘
    private FrameLayout mMainFrame;

    //화면에서의 frament들 선언
    private MapFragment mapFragment;
    private ListFragment listFragment;
    private MypageFragment mypageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {     
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_screen);

        Button searchButton = (Button)findViewById(R.id.search_button);

        //검색하기 버튼을 눌렀을때
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FirstScreenActivity.this,SearchScreenActivity.class);
                startActivity(intent);
            }
        });



        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);



        mapFragment = new MapFragment();
        listFragment = new ListFragment();
        mypageFragment = new MypageFragment();

        setFragment(mapFragment);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.nav_map:
                        setFragment(mapFragment);
                        return true;

                    case R.id.nav_list:
                        setFragment(listFragment);
                        return true;

                    case R.id.nav_mypage:
                        setFragment(mypageFragment);
                        return true;

                    default:
                        return false;
                }
            }
        });


    }

    //fragment 화면을 출력해주는 함수
    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }


    //뒤로가기 버튼 두번눌렀을때
    @Override
    public void onBackPressed() { backPressCloseHandler.onBackPressed(); }


}
