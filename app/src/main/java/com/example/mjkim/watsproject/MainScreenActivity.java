package com.example.mjkim.watsproject;

import android.Manifest;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.mjkim.watsproject.FirstSreenFragments.ListFragment;
import com.example.mjkim.watsproject.FirstSreenFragments.MypageFragment;
import com.example.mjkim.watsproject.OtherClasses.BackPressCloseHandler;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.LocationOverlay;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.FusedLocationSource;

import java.util.ArrayList;


public class MainScreenActivity extends AppCompatActivity implements OnMapReadyCallback, NaverMap.OnLocationChangeListener {
    // 쥐피에스
    private FusedLocationSource locationSource;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private static final int REQUEST_CODE_LOCATION = 2;
    private BackPressCloseHandler backPressCloseHandler;
    private static double myLatitude, myLongitude;
    private static Location location;
    private static LatLng latLng;

    int check = 0;
    private ArrayList<Marker> markers;


    private BottomNavigationView mMainNav; //하단 메뉴 아이콘
    private FrameLayout mMainFrame;
    private FrameLayout statsFrame;
    private Dialog reviewDialog;

    //화면에서의 fragment들 선언
    private MapFragment mapFragment; //NaverMapFragment안쓰고 따로 네이버에서 제공하는 클래스 사용하는 것임
    private ListFragment listFragment;
    private MypageFragment mypageFragment;
    private StatsFragment statsFragment;

    Marker marker;



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

        reviewDialog = new Dialog(this); //회원가입 팝업 변수 선언

        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);
        statsFrame = (FrameLayout)findViewById(R.id.stats_frame_layout);

        listFragment = new ListFragment();
        mypageFragment = new MypageFragment();
        statsFragment = new StatsFragment();

        locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);



        // 위치 설정 권한 없을 때 넣기
        int permssionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permssionCheck!= PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(this,"위치 권한 승인을 해주세요",Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_CODE_LOCATION);
            }
//                        finish();
//            Intent intent=new Intent(this,LoadingScreenActivity.class);
//            startActivity(intent);
        }

        // 기본 화면 지도 뜨게 함
        setMapFragment();



        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.nav_map:
                        check = 0;
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

    // 뒤로가기 버튼 두번눌렀을때
    @Override
    public void onBackPressed() { backPressCloseHandler.onBackPressed(); }


    // fragment 화면을 출력해주는 함수
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
        statsFrame.setVisibility(View.INVISIBLE);
    }


    // 지도 띄우는 프래그먼트 설정
    private void setMapFragment() {


        if (mapFragment == null) {

            mapFragment = MapFragment.newInstance();
            System.out.println("첫번째 : " + mapFragment.toString());
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, mapFragment).addToBackStack("parent").commit();

        }
        else {
            mapFragment = MapFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, mapFragment).commit();
            System.out.println("두번째 : " + mapFragment.toString());
            // 원래 꺼를 지워줘야 지도가 다시 실행되도 안 튕김
            getSupportFragmentManager().beginTransaction().remove(mapFragment);
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, mapFragment).addToBackStack("parent").commit();

        }

        mapFragment.getMapAsync(this);
    }


    // 런타임 권한 받기
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    // 지도 관련 정보 사용할 때
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {

        // 현위치 버튼 o, 축척바 x 등 기타 지도 세팅
        naverMap.getUiSettings().setLocationButtonEnabled(true);
        naverMap.getUiSettings().setScaleBarEnabled(false);
        naverMap.getUiSettings().setZoomControlEnabled(true);
        naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_TRANSIT, true);
        naverMap.setIndoorEnabled(true);

        System.out.println("세번째 : " + mapFragment.toString());
        // 마커 객체 생성, 설정
        markers = new ArrayList<Marker>();
        markers.add(0, new Marker(new LatLng(36.102905, 129.388745)));
        markers.get(0).setHeight(110);
        markers.get(0).setWidth(80);
        markers.get(0).setCaptionText("한동대학교");
        markers.get(0).setMap(naverMap);
        markers.add(1, new Marker(new LatLng(36.086844, 129.409631)));
        markers.get(1).setHeight(110);
        markers.get(1).setWidth(80);
        markers.get(1).setCaptionText("포항대학교");
        markers.get(1).setMap(naverMap);

        // 마커 누르는 이벤트
        markers.get(1).setOnClickListener(overlay -> {
//            //누르면 프래그먼트 뜨기, 다이얼로그로 해보려고 잠궈둠
//            statsFrame.setVisibility(View.VISIBLE);
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.stats_frame_layout, statsFragment);
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();

            reviewDialog.setContentView(R.layout.click_review_popup);
            Button closeButton = (Button) reviewDialog.findViewById(R.id.category);

            //닫기 버튼을 눌렀을때
            closeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { reviewDialog.dismiss(); }});

            reviewDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
            reviewDialog.getWindow().setGravity(Gravity.BOTTOM);
            reviewDialog.show();


            return false;
        });




//        for(Marker marker : markers) {
//            marker.
//        }


        // gps로 내 위치 잡음
        naverMap.setLocationSource(locationSource);
//        System.out.println("위치테스트 : " + naverMap.getCameraPosition().toString());
        naverMap.setLocationTrackingMode(LocationTrackingMode.NoFollow);

        // 위치 오버레이 생성, 보이기 설정
        LocationOverlay locationOverlay = naverMap.getLocationOverlay();
        locationOverlay.setVisible(false);
        locationOverlay.setOnClickListener(overlay -> {
            statsFrame.setVisibility(View.INVISIBLE);
            return false;
        });


        // 위치 변경시 발동, 위치 정보가 담긴 location은 밖에선 못 사용, 람다식으로 함수만들어서 여기서만 가능
        naverMap.addOnLocationChangeListener((Location location) -> {
            myLatitude = location.getLatitude();
            myLongitude = location.getLongitude();
            latLng = new LatLng(myLatitude, myLongitude);

            // 처음에만 내 위치로 이동함
            if(check == 0) {
                naverMap.setCameraPosition(new CameraPosition(latLng, 13));
                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(latLng);//.animate(CameraAnimation..Easing);
//                Toast.makeText(this, "myLatitude : " + myLatitude + ",,,,myLongtitude : " + myLongitude, Toast.LENGTH_LONG).show();
                naverMap.moveCamera(cameraUpdate);
                check ++;
            }
        });
    }


    @Override
    public void onLocationChange(@NonNull Location location) {
        // 작동 되는 꼴을 못 봄
    }
}
