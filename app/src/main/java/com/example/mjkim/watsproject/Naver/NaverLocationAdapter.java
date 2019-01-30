package com.example.mjkim.watsproject.Naver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mjkim.watsproject.LocationDetailScreenActivity;
import com.example.mjkim.watsproject.R;
import com.example.mjkim.watsproject.Review.ReviewFirebaseJson;
import com.example.mjkim.watsproject.Review.ReviewList;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class NaverLocationAdapter extends BaseAdapter{

    private LayoutInflater mInflater;
    private Activity m_activity;
    private ArrayList<NaverLocationList> arr;
    private int save;
    ImageView tagShow1,tagShow2,tagShow3,tagShow4,tagShow5,tagShow6;
    private Boolean tag1, tag2, tag3, tag4, tag5, tag6;

    public static ArrayList<ReviewList> reviewLists;
    private int length;


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

        int[] tag_array = {0,0,0,0,0,0};

        if(ReviewFirebaseJson.reviewJson.size() > position){

            reviewLists = new ArrayList<ReviewList>();
            int num = 0;


            String json = ReviewFirebaseJson.reviewJson.get(position).getReview_json_string();
            length = ReviewFirebaseJson.reviewJson.get(position).getReview_count();
            JSONArray IDs = ReviewFirebaseJson.reviewJson.get(position).getReview_json_userID();
            String location_name = ReviewFirebaseJson.reviewJson.get(position).getLocation_name();

            try{
                JSONObject obj = new JSONObject(json);



                for (int i = 0; i < length; i++) {
                    JSONObject jsonObj = obj.getJSONObject(IDs.getString(i));
                    tag1 = jsonObj.getBoolean("tag1");
                    if(tag1 == true) tag_array[0]  = tag_array[0] +  1;
                    tag2 = jsonObj.getBoolean("tag2");
                    if(tag2 == true) tag_array[1]  = tag_array[1] +  1;
                    tag3 = jsonObj.getBoolean("tag3");
                    if(tag3 == true) tag_array[2]  = tag_array[2] +  1;
                    tag4 = jsonObj.getBoolean("tag4");
                    if(tag4 == true) tag_array[3]  = tag_array[3] +  1;
                    tag5 = jsonObj.getBoolean("tag5");
                    if(tag5 == true) tag_array[4]  = tag_array[4] +  1;
                    tag6 = jsonObj.getBoolean("tag6");
                    if(tag6 == true) tag_array[5]  = tag_array[5] +  1;


                }


            }catch (Exception e) {
                e.printStackTrace();

            }

        }

        //태그 기준 설정 및 출력

        tagShow1 = (ImageView) convertView.findViewById(R.id.tag_done_1);
        tagShow2 = (ImageView) convertView.findViewById(R.id.tag_done_2);
        tagShow3 = (ImageView) convertView.findViewById(R.id.tag_done_3);
        tagShow4 = (ImageView) convertView.findViewById(R.id.tag_done_4);
        tagShow5 = (ImageView) convertView.findViewById(R.id.tag_done_5);
        tagShow6 = (ImageView) convertView.findViewById(R.id.tag_done_6);

        if(tag_array[0] > length/2 &&  tag_array[0] != 0) tagShow1.setImageResource(R.drawable.restroom);
        else tagShow1.setImageResource(R.drawable.restroom_dimmed);

        if(tag_array[1] > length/2 &&  tag_array[1] != 0) tagShow2.setImageResource(R.drawable.parking);
        else tagShow2.setImageResource(R.drawable.parking_dimmed);

        if(tag_array[2] > length/2 &&  tag_array[2] != 0) tagShow3.setImageResource(R.drawable.elevator);
        else tagShow3.setImageResource(R.drawable.elevator_dimmed);

        if(tag_array[3] > length/2 &&  tag_array[3] != 0) tagShow4.setImageResource(R.drawable.slope);
        else tagShow4.setImageResource(R.drawable.slope_dimmed);

        if(tag_array[4] > length/2 &&  tag_array[4] != 0) tagShow5.setImageResource(R.drawable.table);
        else tagShow5.setImageResource(R.drawable.table_dimmed);

        if(tag_array[5] > length/2 &&  tag_array[5] != 0) tagShow6.setImageResource(R.drawable.assistant);
        else tagShow6.setImageResource(R.drawable.assistant_dimmed);

        //태그 기준 설정 및 출력 끝






        TextView title = (TextView)convertView.findViewById(R.id.vi_name); //장소 이름
        TextView category = (TextView)convertView.findViewById(R.id.vi_category); //장소 분류
        TextView road_address = (TextView)convertView.findViewById(R.id.vi_address); // 장소 주소
        TextView telephone = (TextView)convertView.findViewById(R.id.vi_telephone); //장소 전화번호
        LinearLayout layout_view =  (LinearLayout)convertView.findViewById(R.id.vi_view); //형식

        String shortCategory = arr.get(position).getCategory().substring(arr.get(position).getCategory().lastIndexOf(">")+1);


        title.setText(arr.get(position).getName());
        category.setText(shortCategory);
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
        if(save == 1) intent = new Intent(m_activity, LocationDetailScreenActivity.class);
        else intent = new Intent(m_activity, LocationDetailScreenActivity.class);

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
