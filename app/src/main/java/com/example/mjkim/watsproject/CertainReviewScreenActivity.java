package com.example.mjkim.watsproject;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mjkim.watsproject.OtherClasses.PagerInAdapter;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
public class CertainReviewScreenActivity extends AppCompatActivity {

    Dialog myDialog;

    List<Drawable> temp;
    List<StorageReference> refList;
    String image1,image2,image3,image4,image5,image6,image7,image8,image9;
    StorageReference ref,ref2,ref3,ref4,ref5,ref6,ref7,ref8,ref9;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certain_review_screen);


        Button backButton = (Button)findViewById(R.id.back_button); //돌아가기 버튼 선언

        myDialog = new Dialog(this);

        //돌아가기 버튼 눌렀을때 전 화면을 돌아간다
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        TextView userName = (TextView) findViewById(R.id.vi_name);
        TextView reviewDate = (TextView) findViewById(R.id.vi_date);
        TextView reviewDescription = (TextView) findViewById(R.id.vi_description);

        userName.setText(getIntent().getExtras().getString("Name"));
        reviewDate.setText(getIntent().getExtras().getString("Date"));
        reviewDescription.setText(getIntent().getExtras().getString("Review"));

        ImageView tagShow1 = (ImageView)findViewById(R.id.tag_done_1); //태그 이미지 선언
        ImageView tagShow2 = (ImageView)findViewById(R.id.tag_done_2);
        ImageView tagShow3 = (ImageView)findViewById(R.id.tag_done_3);
        ImageView tagShow4 = (ImageView)findViewById(R.id.tag_done_4);
        ImageView tagShow5 = (ImageView)findViewById(R.id.tag_done_5);
        ImageView tagShow6 = (ImageView)findViewById(R.id.tag_done_6);

        if(getIntent().getExtras().getBoolean("Tag1") == true) tagShow1.setImageResource(R.drawable.restroom);
        if(getIntent().getExtras().getBoolean("Tag2") == true) tagShow2.setImageResource(R.drawable.parking);
        if(getIntent().getExtras().getBoolean("Tag3") == true) tagShow3.setImageResource(R.drawable.elevator);
        if(getIntent().getExtras().getBoolean("Tag4") == true) tagShow4.setImageResource(R.drawable.slope);
        if(getIntent().getExtras().getBoolean("Tag5") == true) tagShow5.setImageResource(R.drawable.table);
        if(getIntent().getExtras().getBoolean("Tag6") == true) tagShow6.setImageResource(R.drawable.assistant);

        image1 = getIntent().getExtras().getString("IMAGE1");
        image2 = getIntent().getExtras().getString("IMAGE2");
        image3 = getIntent().getExtras().getString("IMAGE3");
        image4 = getIntent().getExtras().getString("IMAGE4");
        image5 = getIntent().getExtras().getString("IMAGE5");
        image6 = getIntent().getExtras().getString("IMAGE6");
        image7 = getIntent().getExtras().getString("IMAGE7");
        image8 = getIntent().getExtras().getString("IMAGE8");
        image9 = getIntent().getExtras().getString("IMAGE9");

        tagShow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //태그1 눌렀을때 정보 팝업창 띄우기

                myDialog.setContentView(R.layout.tag_restroom_popup);
                Button closeButton = (Button) myDialog.findViewById(R.id.ok_button);

                //닫기 버튼을 눌렀을때
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) { myDialog.dismiss(); }});

                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                myDialog.show();
            }
        });

        tagShow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //태그2 눌렀을때 정보 팝업창 띄우기

                myDialog.setContentView(R.layout.tag_parking_popup);
                Button closeButton = (Button) myDialog.findViewById(R.id.ok_button);

                //닫기 버튼을 눌렀을때
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) { myDialog.dismiss(); }});

                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                myDialog.show();
            }
        });

        tagShow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //태그3 눌렀을때 정보 팝업창 띄우기

                myDialog.setContentView(R.layout.tag_elevator_popup);
                Button closeButton = (Button) myDialog.findViewById(R.id.ok_button);

                //닫기 버튼을 눌렀을때
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) { myDialog.dismiss(); }});

                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                myDialog.show();
            }
        });

        tagShow4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myDialog.setContentView(R.layout.tag_slope_popup);
                Button closeButton = (Button) myDialog.findViewById(R.id.ok_button);

                //닫기 버튼을 눌렀을때
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) { myDialog.dismiss(); }});

                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                myDialog.show();
            }
        });

        tagShow5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //태그5 눌렀을때 정보 팝업창 띄우기

                myDialog.setContentView(R.layout.tag_table_popup);
                Button closeButton = (Button) myDialog.findViewById(R.id.ok_button);

                //닫기 버튼을 눌렀을때
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) { myDialog.dismiss(); }});

                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                myDialog.show();
            }
        });

        tagShow6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //태그6 눌렀을때 정보 팝업창 띄우기

                myDialog.setContentView(R.layout.tag_assistant_popup);
                Button closeButton = (Button) myDialog.findViewById(R.id.ok_button);

                //닫기 버튼을 눌렀을때
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) { myDialog.dismiss(); }});

                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                myDialog.show();
            }
        });

        ref = FirebaseStorage.getInstance().getReference("images/"+image1);
        ref2 = FirebaseStorage.getInstance().getReference("images/"+image2);
        ref3 = FirebaseStorage.getInstance().getReference("images/"+image3);
        ref4 = FirebaseStorage.getInstance().getReference("images/"+image4);
        ref5 = FirebaseStorage.getInstance().getReference("images/"+image5);
        ref6 = FirebaseStorage.getInstance().getReference("images/"+image6);
        ref7 = FirebaseStorage.getInstance().getReference("images/"+image7);
        ref8 = FirebaseStorage.getInstance().getReference("images/"+image8);
        ref9 = FirebaseStorage.getInstance().getReference("images/"+image9);

        temp = new ArrayList<>();
        temp.add(ContextCompat.getDrawable(CertainReviewScreenActivity.this,R.drawable.restroom));
        temp.add(ContextCompat.getDrawable(CertainReviewScreenActivity.this,R.drawable.restroom));
        temp.add(ContextCompat.getDrawable(CertainReviewScreenActivity.this,R.drawable.restroom));

        System.out.println("신지현바보1"+image2);
        refList=new ArrayList<>();
        if(!image1.equals(""))
            refList.add(ref);
        if(!image2.equals(""))
         refList.add(ref2);
        if(!image3.equals(""))
          refList.add(ref3);
        if(!image4.equals(""))
           refList.add(ref4);
        if(!image5.equals(""))
           refList.add(ref5);
        if(!image6.equals(""))
          refList.add(ref6);
        if(!image7.equals(""))
          refList.add(ref7);
        if(!image8.equals(""))
           refList.add(ref8);
        if(!image9.equals(""))
            refList.add(ref9);


  //      PagerInAdapter adapter=new PagerInAdapter(temp,this);
       PagerInAdapter adapter=new PagerInAdapter(refList,this);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.photos_viewpager);
//        PagerInAdapter adapter = new PagerInAdapter(this);
        viewPager.setAdapter(adapter);


        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager, true);
    }
}
