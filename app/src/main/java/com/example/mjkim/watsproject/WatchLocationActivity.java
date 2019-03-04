package com.example.mjkim.watsproject;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.example.mjkim.watsproject.Convert.GeoTrans;
import com.example.mjkim.watsproject.Convert.GeoTransPoint;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;

public class WatchLocationActivity extends Activity implements OnMapReadyCallback{
    private MapView mapView;
    private Button backButton;
    public static String shortLocationName, location_name, location_category, location_address, location_number;
    public static double location_x, location_y;
    private Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_location);

        // 뒤로가기
        backButton = (Button)findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 장소 정보 받아옴
//        int index = getIntent().getExtras().getString("NAME").indexOf(" , ");
//        location_name = getIntent().getExtras().getString("NAME").substring(0, index);
        location_name = getIntent().getExtras().getString("NAME");
        System.out.println("tired : " + location_name);
        int nameIndex = location_name.indexOf(" ,");
        shortLocationName = location_name.substring(0, nameIndex+1);
        location_category = getIntent().getExtras().getString("CATEGORY");
        location_address = getIntent().getExtras().getString("ADDRESS");
        location_number = getIntent().getExtras().getString("TELEPHONE");
        location_x = getIntent().getExtras().getDouble("MAPX");
        location_y = getIntent().getExtras().getDouble("MAPY");

        System.out.println("newmap1 : " + location_address + "  " + location_x + "  " + location_y);

        // 맵 띄우기
        mapView = findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    // 지도 실질적 명령
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        // 지도 세팅
        naverMap.getUiSettings().setScaleBarEnabled(false);
        naverMap.getUiSettings().setZoomControlEnabled(true);
        naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_TRANSIT, true);
        naverMap.setIndoorEnabled(true);

        // 마커 등록
        GeoTransPoint oKA = new GeoTransPoint(location_x, location_y);
        GeoTransPoint oGeo = GeoTrans.convert(GeoTrans.KATEC, GeoTrans.GEO, oKA);
        marker = new Marker(new LatLng(oGeo.getY(), oGeo.getX()));
        marker.setHeight(110);
        marker.setWidth(80);
        marker.setCaptionText(shortLocationName);
        marker.setHideCollidedSymbols(true);
        marker.setCaptionTextSize(16);
        marker.setCaptionColor(Color.parseColor("#1502F8"));
        marker.setMap(naverMap);
        System.out.println("newmap5 : " + location_x + location_y);

        // 카메라 위치 이동
        naverMap.setCameraPosition(new CameraPosition(new LatLng(oGeo.getY(), oGeo.getX()), 14));
    }
}
