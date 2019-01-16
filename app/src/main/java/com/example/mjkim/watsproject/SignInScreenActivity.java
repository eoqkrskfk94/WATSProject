package com.example.mjkim.watsproject;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignInScreenActivity extends AppCompatActivity {

    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_screen);
        myDialog = new Dialog(this); //회원가입 팝업 변수 선언


        Button backButton = (Button)findViewById(R.id.back_button); //돌아가기 버튼 선언




        //돌아가기 버튼 눌렀을때 전 화면을 돌아간다
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }

    //회원가입 버튼 눌렀을때
    public void RegisterButton (View view){
        myDialog.setContentView(R.layout.congratulation_popup);
        myDialog.setCancelable(false);

        Button closeButton = (Button) myDialog.findViewById(R.id.ok_button);



        //닫기 버튼을 눌렀을때
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignInScreenActivity.this,MainScreenActivity.class);
                startActivity(intent);
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
        myDialog.show();
    }



}
