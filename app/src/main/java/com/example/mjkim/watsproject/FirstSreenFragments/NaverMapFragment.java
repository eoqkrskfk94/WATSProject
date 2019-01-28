package com.example.mjkim.watsproject.FirstSreenFragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mjkim.watsproject.R;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.NaverMap;

/**
 * 지도는 이 클래스 사용안함, 네이버에서 자동으로 가져오는 클래스 있어서 그거 씀
 */
public class NaverMapFragment extends Fragment {
    private Context context;
    private NaverMap naverMap;

    public NaverMapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        context = container.getContext();
//        LatLng coord = new LatLng(37.5670135, 126.9783740);
//
//        Toast.makeText(getContext(), "위도: " + coord.latitude + ", 경도: " + coord.longitude, Toast.LENGTH_SHORT).show();


//        naverMap = new NaverMap(getContext(), )
//        // 줌 최소, 최대 범위 설정
//        naverMap.setMinZoom(5.0);
//        naverMap.setMaxZoom(18.0);


//        MapFragment mapFragment = (MapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
//        if (mapFragment == null) {
//            mapFragment = MapFragment.newInstance();
//            getSupportFragmentManager().beginTransaction().add(R.id.map, mapFragment).commit();
//        }





        // 지도 띄우기
        return inflater.inflate(R.layout.fragment_map, container, false);
    }


    // 현재 카메라 좌표 가져오기
    // CameraPosition은 대상 지점 new LatLng(위도, 경도)과 , 줌레벨 필요
    public CameraPosition getCameraPosition() {
        CameraPosition cameraPosition = naverMap.getCameraPosition();
        return cameraPosition;
    }




}