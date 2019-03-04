package com.example.mjkim.watsproject.OtherClasses;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mjkim.watsproject.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ImageBigShowActivity extends AppCompatActivity {
    String image1,image2,image3,image4,image5,image6,image7,image8,image9;
    int position;
   StorageReference ref,ref2,ref3,ref4,ref5,ref6,ref7,ref8,ref9;
    List<StorageReference> refList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_big_show);

        position=getIntent().getExtras().getInt("position");


        image1 = getIntent().getExtras().getString("IMAGE1");
        image2 = getIntent().getExtras().getString("IMAGE2");
        image3 = getIntent().getExtras().getString("IMAGE3");
        image4 = getIntent().getExtras().getString("IMAGE4");
        image5 = getIntent().getExtras().getString("IMAGE5");
        image6 = getIntent().getExtras().getString("IMAGE6");
        image7 = getIntent().getExtras().getString("IMAGE7");
        image8 = getIntent().getExtras().getString("IMAGE8");
        image9 = getIntent().getExtras().getString("IMAGE9");

        ref = FirebaseStorage.getInstance().getReference("images/"+image1);
        ref2 = FirebaseStorage.getInstance().getReference("images/"+image2);
        ref3 = FirebaseStorage.getInstance().getReference("images/"+image3);
        ref4 = FirebaseStorage.getInstance().getReference("images/"+image4);
        ref5 = FirebaseStorage.getInstance().getReference("images/"+image5);
        ref6 = FirebaseStorage.getInstance().getReference("images/"+image6);
        ref7 = FirebaseStorage.getInstance().getReference("images/"+image7);
        ref8 = FirebaseStorage.getInstance().getReference("images/"+image8);
        ref9 = FirebaseStorage.getInstance().getReference("images/"+image9);


        System.out.println("이미지 포지션dll 1 " +position+" "+image1+"\n");
        System.out.println("이미지 포지션dll 2 " +position+" "+image2+"\n");
        System.out.println("이미지 포지션dll 3 " +position+" "+image3+"\n");
        System.out.println("이미지 포지션dll 4 " +position+" "+image4+"\n");


        refList=new ArrayList<>();
        if(position<=1)
            refList.add(ref);
        else if(position<=2) {
            refList.add(ref);
            refList.add(ref2);
        }
        else if(position<=3) {
            refList.add(ref);
            refList.add(ref2);
            refList.add(ref3);
        }
        else if(position<=4) {
            refList.add(ref);
            refList.add(ref2);
            refList.add(ref3);
            refList.add(ref4);
        }
        else if(position<=5) {
            refList.add(ref);
            refList.add(ref2);
            refList.add(ref3);
            refList.add(ref4);
            refList.add(ref5);
        }
        else if(position<=6) {
            refList.add(ref);
            refList.add(ref2);
            refList.add(ref3);
            refList.add(ref4);
            refList.add(ref5);
            refList.add(ref6);
        }
        else if(position<=7) {
            refList.add(ref);
            refList.add(ref2);
            refList.add(ref3);
            refList.add(ref4);
            refList.add(ref5);
            refList.add(ref6);
            refList.add(ref7);
        }
        else if(position<=8) {
            refList.add(ref);
            refList.add(ref2);
            refList.add(ref3);
            refList.add(ref4);
            refList.add(ref5);
            refList.add(ref6);
            refList.add(ref7);
            refList.add(ref8);
        }
        else if(position<=9) {
            refList.add(ref);
            refList.add(ref2);
            refList.add(ref3);
            refList.add(ref4);
            refList.add(ref5);
            refList.add(ref6);
            refList.add(ref7);
            refList.add(ref8);
            refList.add(ref9);
        }




        PagerInBigAdapter adapter=new PagerInBigAdapter(refList,this);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.photos_Big_viewpager);


        viewPager.setAdapter(adapter);
        //사진 밑에 동그라미(사진 갯수)
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager, true);
    }
}
