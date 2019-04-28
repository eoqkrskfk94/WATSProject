package com.handong.wats.wheeliric;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.handong.wats.wheeliric.OtherClasses.PagerInAdapter;
import com.handong.wats.wheeliric.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
public class CertainReviewScreenActivity extends AppCompatActivity {

    Dialog myDialog;
    List<Drawable> temp;
    List<StorageReference> refList;

    List<String> stringList;
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


        TextView userNickName = (TextView) findViewById(R.id.vi_name);
        TextView reviewDate = (TextView) findViewById(R.id.vi_date);
        TextView reviewDescription = (TextView) findViewById(R.id.vi_description);

        userNickName.setText(getIntent().getExtras().getString("NickName"));
        reviewDate.setText(getIntent().getExtras().getString("Date"));
        reviewDescription.setText(getIntent().getExtras().getString("Review"));
        if(reviewDescription.getText().toString().isEmpty()) {
            reviewDescription.setHint("글 내용이 없습니다. ");
        }

        ImageView tagShow1 = (ImageView)findViewById(R.id.tag_done_1); //태그 이미지 선언
        ImageView tagShow2 = (ImageView)findViewById(R.id.tag_done_2);
        ImageView tagShow3 = (ImageView)findViewById(R.id.tag_done_3);
        ImageView tagShow4 = (ImageView)findViewById(R.id.tag_done_4);
        ImageView tagShow5 = (ImageView)findViewById(R.id.tag_done_5);
        ImageView tagShow6 = (ImageView)findViewById(R.id.tag_done_6);

        if(getIntent().getExtras().getBoolean("Tag1") == true) tagShow1.setImageResource(R.drawable.restroom_green);
        if(getIntent().getExtras().getBoolean("Tag2") == true) tagShow2.setImageResource(R.drawable.parking_green);
        if(getIntent().getExtras().getBoolean("Tag3") == true) tagShow3.setImageResource(R.drawable.elevator_green);
        if(getIntent().getExtras().getBoolean("Tag4") == true) tagShow4.setImageResource(R.drawable.slope_green);
        if(getIntent().getExtras().getBoolean("Tag5") == true) tagShow5.setImageResource(R.drawable.table_green);
        if(getIntent().getExtras().getBoolean("Tag6") == true) tagShow6.setImageResource(R.drawable.assistant_green);

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


        refList=new ArrayList<>();
        stringList=new ArrayList<>();

        if(!image1.equals(""))
        {    refList.add(ref);
            System.out.println("이미지 image1 : "+image1);
            stringList.add(image1); }
        if(!image2.equals(""))
        {    refList.add(ref2);   stringList.add(image2);}
        if(!image3.equals(""))
        {    refList.add(ref3);   stringList.add(image3);}
        if(!image4.equals(""))
        {    refList.add(ref4);   stringList.add(image4);}
        if(!image5.equals(""))
        {    refList.add(ref5);   stringList.add(image5);}
        if(!image6.equals(""))
        {    refList.add(ref6);   stringList.add(image6);}
        if(!image7.equals(""))
        {    refList.add(ref7);   stringList.add(image7);}
        if(!image8.equals(""))
        {    refList.add(ref8);   stringList.add(image8);}
        if(!image9.equals(""))
        {    refList.add(ref9);   stringList.add(image9);}



       PagerInAdapter adapter=new PagerInAdapter(refList,stringList,this);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.photos_viewpager);

        //사진이 하나라도 있으면 등록된 사진이 없다는 뒷배경을 null로 만들어줌.
        if(!image1.equals("") || !image2.equals("") || !image3.equals("") || !image4.equals("") || !image5.equals("") || !image6.equals("") || !image7.equals("") || !image8.equals("") ||  !image2.equals("")) {
            viewPager.setBackground(null);
        }

        viewPager.setAdapter(adapter);

        //사진 밑에 동그라미(사진 갯수)
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager, true);

    }

}
