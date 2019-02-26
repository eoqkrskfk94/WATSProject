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
import android.widget.EditText;

import com.example.mjkim.watsproject.User.UserInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangeInfoScreenActivity extends AppCompatActivity {
    Dialog myDialog;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    public UserInformation userInformation=new UserInformation();
    private EditText editName;
    private EditText editYear;
    private EditText editMonth;
    private EditText editDay;
    private EditText editNickname;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    final DatabaseReference userRef=databaseReference.child("user lists");
    public int count=0;
    String basicName,basicKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info_screen);

        editName=(EditText)findViewById(R.id.edit_name);
        editYear=(EditText)findViewById(R.id.edit_year);
        editMonth=(EditText)findViewById(R.id.edit_month);
        editDay=(EditText)findViewById(R.id.edit_day);
        editNickname=(EditText)findViewById(R.id.edit_nickname);

        myDialog = new Dialog(this); //수정하기 팝업 변수 선언

    }

    public void ChangeButton (View view){ //수정하기 버튼 눌렀을때

        myDialog.setContentView(R.layout.change_popup);
        myDialog.setCancelable(false);

        auth= FirebaseAuth.getInstance();
        final String userEmail = new String(auth.getCurrentUser().getEmail()); //Useremail이 현재 사용자 이메일이다.
        mDatabase = database.getReference();


        //닉네입을 보여줌.
        mDatabase.child("user lists").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                // 데이타베이스에 저장되어있는 이름값(ex: 남준영)의 userEmail값이 사용자와 같을때 아래구문 실행.
                mDatabase.child("user lists").child(dataSnapshot.getKey()).orderByChild("userEmail").equalTo(userEmail).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        UserInformation userInformation = dataSnapshot.getValue(UserInformation.class);
                        basicName=userInformation.getUserName();
                        basicKey=dataSnapshot.getKey();
                        if(count==0) {
                            userRef.child(basicName).child(basicKey).setValue(null);  //기존의 것을 지움.
                        }
                        userInformation.setUserEmail(userEmail);
                        userInformation.setUserName(editName.getText().toString());
                        userInformation.setUserYear(editYear.getText().toString());
                        userInformation.setUserMonth(editMonth.getText().toString());
                        userInformation.setUserDay(editDay.getText().toString());
                        userInformation.setUserNickname(editNickname.getText().toString());
                        userInformation=new UserInformation(userEmail,editName.getText().toString(),editYear.getText().toString(),editMonth.getText().toString(),editDay.getText().toString(),editNickname.getText().toString());

                        if(count==0){
                            userRef.child(editName.getText().toString()).push().setValue(userInformation);
                            count++;
                        }
                        else{ }
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

        Button closeButton = (Button) myDialog.findViewById(R.id.ok_button);

        //닫기 버튼을 눌렀을때
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChangeInfoScreenActivity.this,MainScreenActivity.class);
                startActivity(intent);
            }
        });


        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
        myDialog.show(); //수정하기 팝업창.



    }


    // 뒤로가기 버튼 누르면 메인으로
    public void BackButton(View view) {
        finish();
    }


}
