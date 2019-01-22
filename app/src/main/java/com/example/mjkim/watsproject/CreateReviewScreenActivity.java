package com.example.mjkim.watsproject;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mjkim.watsproject.Review.ReviewData;
import com.example.mjkim.watsproject.Review.ReviewList;
import com.example.mjkim.watsproject.User.UserInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateReviewScreenActivity extends AppCompatActivity {

    public ReviewList reviewList = new ReviewList();
    Dialog myDialog;
    CheckBox chk[] = new CheckBox[6]; //태그 체크박스
    String userName = ""; //유저 별명
    EditText review_text; //후기 적성부분
    Boolean tag1,tag2,tag3,tag4,tag5,tag6; //태그
    private FirebaseStorage storage;
    StorageReference storageRef;
    ReviewData reviewData = new ReviewData();

    //파이어베이스 유저 관련 변수 선언
    private DatabaseReference mDatabase;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_review_screen);
        myDialog = new Dialog(this); //등록하기 팝업 변수 선언

        //파이어베이스 유저 관련 변수 선언
        auth= FirebaseAuth.getInstance();
        mDatabase = database.getReference();
        final String userEmail = new String(auth.getCurrentUser().getEmail()); //Useremail이 현재 사용자 이메일이다.
        final DatabaseReference myreview = database.getReference();
        final TextView textId=(TextView)findViewById(R.id.id_name);


        //돌아가기 버튼 선언, 돌아가기 버튼 눌렀을때 전 화면을 돌아간다
        Button backButton = (Button)findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //체크박스 변수 선언
        Integer[] numChkIDs = { R.id.checkBox1, R.id.checkBox2, R.id.checkBox3,
                R.id.checkBox4, R.id.checkBox5, R.id.checkBox6};

        for(int i = 0; i < 6; i++){ chk[i] = (CheckBox)findViewById(numChkIDs[i]); }

        //유저 별명 불러오기
        mDatabase.child("user lists").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                // 데이타베이스에 저장되어있는 이름값(ex: 남준영)의 userEmail값이 사용자와 같을때 아래구문 실행.
                myreview.child("user lists").child(dataSnapshot.getKey()).orderByChild("userEmail").equalTo(userEmail).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        UserInformation userInformation = dataSnapshot.getValue(UserInformation.class);
                        textId.setText(userInformation.getUserNickname()); // 그 유저에 해당하는 닉네임값을 보여줌.
                        userName = userInformation.getUserNickname();
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        review_text = (EditText) findViewById(R.id.review_text); //후기 작성 부분

    }



    //등록하기 버튼 눌렀을때
    public void RegisterButton (View view){

        //리뷰 저장 부분
        //리뷰가 작성된 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.KOREA);
        Date currentTime = new Date();
        String mTime = simpleDateFormat.format(currentTime);


        Intent review_intent = getIntent();

        String location_name = review_intent.getExtras().getString("NAME");
        String location_category = review_intent.getExtras().getString("CATEGORY");
        String location_addess = review_intent.getExtras().getString("ADDRESS");
        String location_number = review_intent.getExtras().getString("TELEPHONE");
        double location_mapx = (double)review_intent.getExtras().getInt("MAPX");
        double location_mapy = (double)review_intent.getExtras().getInt("MAPY");

        //리뷰 문장 받아오기
        String review_description = review_text.getText().toString();

        if(chk[0].isChecked() == true) tag1 = true; else tag1 = false;
        if(chk[1].isChecked() == true) tag2 = true; else tag2 = false;
        if(chk[2].isChecked() == true) tag3 = true; else tag3 = false;
        if(chk[3].isChecked() == true) tag4 = true; else tag4 = false;
        if(chk[4].isChecked() == true) tag5 = true; else tag5 = false;
        if(chk[5].isChecked() == true) tag6 = true; else tag6 = false;

        reviewList = new ReviewList(location_name, location_addess, location_number, location_category, review_description, location_mapx, location_mapy, tag1, tag2, tag3, tag4, tag5, tag6, mTime, userName);

        String nameAndAdress = ""; //이름이랑 주소 같이 나오는 스트링
        reviewData.saveData(location_name, reviewList);


        myDialog.setContentView(R.layout.review_popup);
        myDialog.setCancelable(false);

        Button closeButton = (Button) myDialog.findViewById(R.id.ok_button);



        //닫기 버튼을 눌렀을때
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CreateReviewScreenActivity.this,MainScreenActivity.class);
                startActivity(intent);
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
        myDialog.show();
    }
}
