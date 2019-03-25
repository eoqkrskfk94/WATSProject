package com.example.mjkim.watsproject.Review;

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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mjkim.watsproject.CertainReviewScreenActivity;
import com.example.mjkim.watsproject.LocationDetailScreenActivity;
import com.example.mjkim.watsproject.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class MapReviewAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Activity m_activity;
    private ArrayList<ReviewList> arr;
    public static int select = 0; //출력되는 리뷰 개수 선택 1이면 3개 2이면 최대 20개
    String image1, image2, image3, image4;
    StorageReference ref1,ref2,ref3,ref4;

    

    public MapReviewAdapter(Activity act, ArrayList<ReviewList> arr_item) {
        this.m_activity = act;
        arr = arr_item;
        mInflater = (LayoutInflater) m_activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (select == 1) {
            if (arr.size() < 3) return arr.size();
            else return 3;
        } else return arr.size();
    }

    @Override
    public Object getItem(int position) {
        return arr.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            int res = 0;
            res = R.layout.review_list_box;
            convertView = mInflater.inflate(res, parent, false);

        }

        ImageView profilePic = (ImageView) convertView.findViewById(R.id.profile_pic);
        TextView reviewerName = (TextView) convertView.findViewById(R.id.vi_name);
        TextView description = (TextView) convertView.findViewById(R.id.vi_description);
        TextView postDate = (TextView) convertView.findViewById(R.id.vi_date);
        LinearLayout layout_view = (LinearLayout) convertView.findViewById(R.id.vi_view);
        ImageView picture1 = (ImageView) convertView.findViewById(R.id.sample_pic1);
        ImageView picture2 = (ImageView) convertView.findViewById(R.id.sample_pic2);
        ImageView picture3 = (ImageView) convertView.findViewById(R.id.sample_pic3);
        ImageView picture4 = (ImageView) convertView.findViewById(R.id.sample_pic4);

        picture1.setBackgroundResource(R.drawable.sample_pic);
        picture2.setBackgroundResource(R.drawable.sample_pic);
        picture3.setBackgroundResource(R.drawable.sample_pic);
        picture4.setBackgroundResource(R.drawable.sample_pic);



        reviewerName.setText(arr.get(position).getUserNickName());
        description.setText(arr.get(position).getReview_description());
        if(description.getText().toString().isEmpty()) {
            description.setHint("글 내용이 없습니다. ");
        }
        postDate.setText(arr.get(position).getDate());
        image1 = arr.get(position).getImageUrl1();
        image2 = arr.get(position).getImageUrl2();
        image3 = arr.get(position).getImageUrl3();
        image4 = arr.get(position).getImageUrl4();

        ref1 = FirebaseStorage.getInstance().getReference("images/"+image1);
        ref2 = FirebaseStorage.getInstance().getReference("images/"+image2);
        ref3 = FirebaseStorage.getInstance().getReference("images/"+image3);
        ref4 = FirebaseStorage.getInstance().getReference("images/"+image4);

        if(image1!="")
            Glide.with(m_activity /* context */)
                    .load(ref1)
                    .apply(new RequestOptions().centerCrop())
                    .into(picture1);
        if(image1.equals("")) picture1.setVisibility(View.GONE);

        if(image2!="")
            Glide.with(m_activity /* context */)
                    .load(ref2)
                    .apply(new RequestOptions().centerCrop())
                    .into(picture2);
        if(image2.equals("")) picture2.setVisibility(View.GONE);

        if(image3!="")
            Glide.with(m_activity /* context */)
                    .load(ref3)
                    .apply(new RequestOptions().centerCrop())
                    .into(picture3);
        if(image3.equals("")) picture3.setVisibility(View.GONE);

        if(image4!="")
            Glide.with(m_activity /* context */)
                    .load(ref4)
                    .apply(new RequestOptions().centerCrop())
                    .into(picture4);
        if(image4.equals("")) picture4.setVisibility(View.GONE);



        layout_view.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                GoIntent(position);

            }

        });

        return convertView;


    }

    public void GoIntent(int a){


        Intent intent = new Intent(m_activity, CertainReviewScreenActivity.class);


        intent.putExtra("Email", arr.get(a).getUserEmail());
        intent.putExtra("NickName", arr.get(a).getUserNickName());
        intent.putExtra("Name", arr.get(a).getUserName());
        intent.putExtra("Review", arr.get(a).getReview_description());
        intent.putExtra("Date",arr.get(a).getDate());
        intent.putExtra("Tag1",arr.get(a).getTag1());
        intent.putExtra("Tag2",arr.get(a).getTag2());
        intent.putExtra("Tag3",arr.get(a).getTag3());
        intent.putExtra("Tag4",arr.get(a).getTag4());
        intent.putExtra("Tag5",arr.get(a).getTag5());
        intent.putExtra("Tag6",arr.get(a).getTag6());
        intent.putExtra("IMAGE1",arr.get(a).getImageUrl1());
        intent.putExtra("IMAGE2",arr.get(a).getImageUrl2());
        intent.putExtra("IMAGE3",arr.get(a).getImageUrl3());
        intent.putExtra("IMAGE4",arr.get(a).getImageUrl4());
        intent.putExtra("IMAGE5",arr.get(a).getImageUrl5());
        intent.putExtra("IMAGE6",arr.get(a).getImageUrl6());
        intent.putExtra("IMAGE7",arr.get(a).getImageUrl7());
        intent.putExtra("IMAGE8",arr.get(a).getImageUrl8());
        intent.putExtra("IMAGE9",arr.get(a).getImageUrl9());
        System.out.println("리뷰어뎁터에서 확인"+arr.get(a).getImageUrl1()+"  "+ arr.get(a).getImageUrl2()+arr.get(a).getReview_description());
        m_activity.startActivity(intent);
    }
}
