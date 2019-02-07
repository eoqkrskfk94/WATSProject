package com.example.mjkim.watsproject.Review;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mjkim.watsproject.LocationDetailFromMapScreenActivity;
import com.example.mjkim.watsproject.LocationDetailScreenActivity;
import com.example.mjkim.watsproject.MyReviewScreenActivity;
import com.example.mjkim.watsproject.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;


public class CategoryAdapter extends BaseAdapter {
    private DatabaseReference mDatabase;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private LayoutInflater mInflater;
    private Activity m_activity;
    private ArrayList<ReviewList> arr;
    private int my_review_index;
    Context context;
    Activity activity;
    Dialog myDialog;
    private ImageView tagShow1,tagShow2,tagShow3,tagShow4,tagShow5,tagShow6;



    public CategoryAdapter(Activity act, ArrayList<ReviewList> arr_item,Context context, Activity activity,int my_review_index) {
        this.m_activity = act;
        arr = arr_item;
        mInflater = (LayoutInflater)m_activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context=context;
        this.activity=activity;
        this.my_review_index=my_review_index;
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int position) { return arr.get(position); }

    public long getItemId(int position){ return position; }

    @Override

    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null){

            int res = 0;
            res = R.layout.category_review_list_box;
            convertView = mInflater.inflate(res, parent, false);

        }

        String locationName = arr.get(position).getLocation_name();

        TextView locationNameTextView = (TextView)convertView.findViewById(R.id.location_name);
        TextView locationCategoryTextView = (TextView)convertView.findViewById(R.id.location_category);
        TextView locationAddressTextView = (TextView)convertView.findViewById(R.id.location_address);
        TextView locationPhoneTextView = (TextView)convertView.findViewById(R.id.location_phone);
        LinearLayout layout_view =  (LinearLayout)convertView.findViewById(R.id.vi_view);

        tagShow1 = (ImageView) convertView.findViewById(R.id.tag_done_1);
        tagShow2 = (ImageView) convertView.findViewById(R.id.tag_done_2);
        tagShow3 = (ImageView) convertView.findViewById(R.id.tag_done_3);
        tagShow4 = (ImageView) convertView.findViewById(R.id.tag_done_4);
        tagShow5 = (ImageView) convertView.findViewById(R.id.tag_done_5);
        tagShow6 = (ImageView) convertView.findViewById(R.id.tag_done_6);

        if(arr.get(position).getAverageTag1() == true) tagShow1.setImageResource(R.drawable.restroom);
        else tagShow1.setImageResource(R.drawable.restroom_dimmed);

        if(arr.get(position).getAverageTag2() == true) {tagShow2.setImageResource(R.drawable.parking); System.out.println("너는 누구인가? ");}
        else tagShow2.setImageResource(R.drawable.parking_dimmed);

        if(arr.get(position).getAverageTag3() == true) tagShow3.setImageResource(R.drawable.elevator);
        else tagShow3.setImageResource(R.drawable.elevator_dimmed);

        if(arr.get(position).getAverageTag4() == true) tagShow4.setImageResource(R.drawable.slope);
        else tagShow4.setImageResource(R.drawable.slope_dimmed);

        if(arr.get(position).getAverageTag5() == true) tagShow5.setImageResource(R.drawable.table);
        else tagShow5.setImageResource(R.drawable.table_dimmed);

        if(arr.get(position).getAverageTag6() == true) tagShow6.setImageResource(R.drawable.assistant);
        else tagShow6.setImageResource(R.drawable.assistant_dimmed);



        //주소 빼고 이름만 사용
        int index = locationName.indexOf(" , ");
        String correctionLocationName = locationName.toString().substring(0, index);
//        String correctionLocationName = "< " + newLocationName + " >";


        locationNameTextView.setText(correctionLocationName);
        locationCategoryTextView.setText(arr.get(position).getLocation_category());
        locationAddressTextView.setText(arr.get(position).getLocation_address());
        locationPhoneTextView.setText(arr.get(position).getPhone_number());


        /*  버튼에 이벤트처리를 하기위해선 setTag를 이용해서 사용할 수 있습니다.

         *   Button btn 가 있다면, btn.setTag(position)을 활용해서 각 버튼들

         *   이벤트처리를 할 수 있습니다.

         */

        layout_view.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                GoReview(position);

            }

        });

        return convertView;
    }

    private void delete(int a) {
        mDatabase = database.getReference();
        mDatabase.child("review lists").child(arr.get(a).getLocation_name()).child(arr.get(a).getKey()).setValue(null);
        System.out.println("\n 삭제테스트 1: "+ arr.get(a).getKey()+"  ");
        my_review_index--;
        Intent intent = new Intent(activity, MyReviewScreenActivity.class);
        m_activity.finish();
        m_activity.startActivity(intent);

    }


    public void GoReview(int a){
        // 피니시해야 안 꺼짐
        m_activity.finish();
        Intent intent = new Intent(m_activity, LocationDetailFromMapScreenActivity.class);
        intent.putExtra("IMAGE1", arr.get(a).getImageUrl1());
        intent.putExtra("IMAGE2", arr.get(a).getImageUrl2());
        intent.putExtra("IMAGE3", arr.get(a).getImageUrl3());
        intent.putExtra("IMAGE4", arr.get(a).getImageUrl4());
        intent.putExtra("IMAGE5", arr.get(a).getImageUrl5());
        intent.putExtra("IMAGE6", arr.get(a).getImageUrl6());
        intent.putExtra("IMAGE7", arr.get(a).getImageUrl7());
        intent.putExtra("IMAGE8", arr.get(a).getImageUrl8());
        intent.putExtra("IMAGE9", arr.get(a).getImageUrl9());

        intent.putExtra("Email", arr.get(a).getUserEmail());
        intent.putExtra("USERNAME", arr.get(a).getUserName());
        intent.putExtra("Review", arr.get(a).getReview_description());
        intent.putExtra("ADDRESS", arr.get(a).getLocation_address());
        intent.putExtra("CATEGORY", arr.get(a).getLocation_category());
        intent.putExtra("NAME", arr.get(a).getLocation_name());
        intent.putExtra("TELEPHONE", arr.get(a).getPhone_number());
        intent.putExtra("MAPX", arr.get(a).getMapx());
        intent.putExtra("MAPY", arr.get(a).getMapy());
        intent.putExtra("ReviewDescription", arr.get(a).getReview_description());
        intent.putExtra("Date",arr.get(a).getDate());
        intent.putExtra("TAG1",arr.get(a).getAverageTag1());
        intent.putExtra("TAG2",arr.get(a).getAverageTag2());
        intent.putExtra("TAG3",arr.get(a).getAverageTag3());
        intent.putExtra("TAG4",arr.get(a).getAverageTag4());
        intent.putExtra("TAG5",arr.get(a).getAverageTag5());
        intent.putExtra("TAG6",arr.get(a).getAverageTag6());

        // 장소리스트에서 후기 볼 때만 뒤로가기 누르면 다시 액티비티 시작하게 함
        intent.putExtra("Check", 1);


        m_activity.startActivity(intent);
    }


}