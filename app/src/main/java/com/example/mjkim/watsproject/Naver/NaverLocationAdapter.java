package com.example.mjkim.watsproject.Naver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mjkim.watsproject.LocationDetailScreen;
import com.example.mjkim.watsproject.R;

import java.util.ArrayList;

public class NaverLocationAdapter extends BaseAdapter{

    private LayoutInflater mInflater;
    private Activity m_activity;
    private ArrayList<NaverLocationList> arr;
    private int save;


    public NaverLocationAdapter(Activity act, ArrayList<NaverLocationList> arr_item, int save) {
        this.m_activity = act;
        arr = arr_item;
        mInflater = (LayoutInflater)m_activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.save = save;
    }



    @Override
    public int getCount() { return arr.size(); }

    @Override
    public Object getItem(int position) { return arr.get(position); }

    public long getItemId(int position){ return position; }

    @Override

    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null){

            int res = 0;
            res = R.layout.search_list_box;
            convertView = mInflater.inflate(res, parent, false);

        }


        TextView title = (TextView)convertView.findViewById(R.id.vi_name); //장소 이름
        TextView category = (TextView)convertView.findViewById(R.id.vi_category); //장소 분류
        TextView road_address = (TextView)convertView.findViewById(R.id.vi_address); // 장소 주소
        TextView telephone = (TextView)convertView.findViewById(R.id.vi_telephone); //장소 전화번호
        LinearLayout layout_view =  (LinearLayout)convertView.findViewById(R.id.vi_view); //형식


        title.setText(arr.get(position).getName());
        category.setText(arr.get(position).getCategory());
        road_address.setText(arr.get(position).getRoad_address());
        telephone.setText(arr.get(position).getTelephone());


        /*  버튼에 이벤트처리를 하기위해선 setTag를 이용해서 사용할 수 있습니다.

         *   Button btn 가 있다면, btn.setTag(position)을 활용해서 각 버튼들

         *   이벤트처리를 할 수 있습니다.
         */


        layout_view.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){ GoIntent(position); }
        });

        return convertView;

    }

    public void GoIntent(int a){
        Intent intent;



        // 이름으로 검색에서 리스트 뷰를 누르면 장소 정보를 자세히본다.
        // 리뷰 작성하기에서 리스트 뷰를 누르면 리뷰 작성으로 간다.
        if(save == 1) intent = new Intent(m_activity, LocationDetailScreen.class);
        else intent = new Intent(m_activity, LocationDetailScreen.class);

//putExtra 로 선택한 아이템의 정보를 인텐트로 넘겨 줄 수 있다.

        intent.putExtra("NAME", arr.get(a).getName());
        intent.putExtra("CATEGORY", arr.get(a).getCategory());
        intent.putExtra("LINK", arr.get(a).getLink());
        intent.putExtra("DESCRIPTION", arr.get(a).getDescription());
        intent.putExtra("ADDRESS", arr.get(a).getAddress());
        intent.putExtra("ROAD_ADDRESS", arr.get(a).getRoad_address());
        intent.putExtra("TELEPHONE", arr.get(a).getTelephone());
        intent.putExtra("MAPX", arr.get(a).getMapx());
        intent.putExtra("MAPY", arr.get(a).getMapy());
        intent.putExtra("NUMBER", a);


        m_activity.startActivity(intent);
    }


}
