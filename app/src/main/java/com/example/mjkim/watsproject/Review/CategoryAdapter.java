package com.example.mjkim.watsproject.Review;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mjkim.watsproject.CertainReviewScreenActivity;
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
            res = R.layout.review_list_box;
            convertView = mInflater.inflate(res, parent, false);

        }


        TextView userName = (TextView)convertView.findViewById(R.id.vi_name);
        TextView reviewDescription = (TextView)convertView.findViewById(R.id.vi_description);
        TextView postDate = (TextView)convertView.findViewById(R.id.vi_date);
        LinearLayout layout_view =  (LinearLayout)convertView.findViewById(R.id.vi_view);





        userName.setText(arr.get(position).getUserName());
        reviewDescription.setText(arr.get(position).getReview_description());
        postDate.setText(arr.get(position).getDate());

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
        // 피니시해야 안 꺼
        m_activity.finish();
        Intent intent = new Intent(m_activity, CertainReviewScreenActivity.class);
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
        intent.putExtra("Name", arr.get(a).getUserName());
        intent.putExtra("Review", arr.get(a).getReview_description());
        intent.putExtra("LocationAddress", arr.get(a).getLocation_address());
        intent.putExtra("LocationCategory", arr.get(a).getLocation_category());
        intent.putExtra("LocationName", arr.get(a).getLocation_name());
        intent.putExtra("LocationNumber", arr.get(a).getPhone_number());
        intent.putExtra("Mapx", arr.get(a).getMapx());
        intent.putExtra("Mapy", arr.get(a).getMapy());
        intent.putExtra("ReviewDescription", arr.get(a).getReview_description());
        intent.putExtra("Date",arr.get(a).getDate());
        intent.putExtra("Tag1",arr.get(a).getTag1());
        intent.putExtra("Tag2",arr.get(a).getTag2());
        intent.putExtra("Tag3",arr.get(a).getTag3());
        intent.putExtra("Tag4",arr.get(a).getTag4());
        intent.putExtra("Tag5",arr.get(a).getTag5());
        intent.putExtra("Tag6",arr.get(a).getTag6());

        // 장소리스트에서 후기 볼 때만 뒤로가기 누르면 다시 액티비티 시작하게 함
        intent.putExtra("Check", 1);


        m_activity.startActivity(intent);
    }


}