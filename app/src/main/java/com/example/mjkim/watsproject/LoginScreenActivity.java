package com.example.mjkim.watsproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);


        Button loginButton = (Button)findViewById(R.id.login_button); //로그인 버튼 선언
        Button findIDButton = (Button)findViewById(R.id.find_id); //아이디 찾기 선언
        Button findPasswButton = (Button)findViewById(R.id.find_passw); //비밀번호 찾기 버튼 선언
        Button createUserButton = (Button)findViewById(R.id.create_acc); //회원가입 버튼 선언


        //회원가입 버튼을 눌렀을때
        createUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginScreenActivity.this,SignInScreenActivity.class);
                startActivity(intent);
            }
        });

        //아이디 찾기 버튼을 눌렀을때
        findIDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginScreenActivity.this,"준비중입니다",Toast.LENGTH_SHORT).show();
            }
        });

        //비밀번호 찾기 버튼을 눌렀을때
        findPasswButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginScreenActivity.this,"준비중입니다",Toast.LENGTH_SHORT).show();
            }
        });



    }
}
