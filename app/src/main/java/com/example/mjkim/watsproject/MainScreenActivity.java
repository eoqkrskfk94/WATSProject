package com.example.mjkim.watsproject;

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

import com.example.mjkim.watsproject.FirstSreenFragments.ListFragment;
import com.example.mjkim.watsproject.FirstSreenFragments.MypageFragment;
import com.example.mjkim.watsproject.OtherClasses.BackPressCloseHandler;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;


public class MainScreenActivity extends AppCompatActivity implements OnMapReadyCallback {

    private BackPressCloseHandler backPressCloseHandler;


    private BottomNavigationView mMainNav; //하단 메뉴 아이콘
    private FrameLayout mMainFrame;

    //화면에서의 fragment들 선언
    private MapFragment mapFragment; //NaverMapFragment안쓰고 따로 네이버에서 제공하는 클래스 사용하는 것임
    private ListFragment listFragment;
    private MypageFragment mypageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {     
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        backPressCloseHandler = new BackPressCloseHandler(this);

        Button searchButton = (Button)findViewById(R.id.search_button);

        //검색하기 버튼을 눌렀을때
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainScreenActivity.this,SearchScreenActivity.class);
                startActivity(intent);
            }
        });

        MapFragment mapFragment = (MapFragment)getSupportFragmentManager().findFragmentById(R.id.main_frame);



        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);


        mapFragment = new MapFragment();
        listFragment = new ListFragment();
        mypageFragment = new MypageFragment();

        setMapFragment();

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.nav_map:
                        setMapFragment();
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

    // 지도 띄우는 프래그먼트 설정
    private void setMapFragment() {


        if (mapFragment == null) {
            System.out.println("절레절레");
            mapFragment = MapFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, mapFragment).commit();

        }
        else {
            System.out.println("도리도리");
            mapFragment = MapFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, mapFragment).commit();

        }

        mapFragment.getMapAsync(this);



    }


    //뒤로가기 버튼 두번눌렀을때
    @Override
    public void onBackPressed() { backPressCloseHandler.onBackPressed(); }


    // 지도 관련 정보 사용할 때
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        System.out.println("면담언제해");
    }
}
