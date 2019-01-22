package com.example.mjkim.watsproject;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreenActivity extends AppCompatActivity {
    public static int save = 0; // 로그인 되어있음을 확인하는 변수
    private FirebaseUser currentUser;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        mAuth = FirebaseAuth.getInstance(); // 로그인 작업의 onCreate 메소드에서 FirebaseAuth 개체의 공유 인스턴스를 가져옵니다
        currentUser = mAuth.getCurrentUser();

        Button loginButton = (Button)findViewById(R.id.login_button); //로그인 버튼 선언
        Button findIDButton = (Button)findViewById(R.id.find_id); //아이디 찾기 선언
        Button findPasswButton = (Button)findViewById(R.id.find_passw); //비밀번호 찾기 버튼 선언
        Button createUserButton = (Button)findViewById(R.id.create_acc); //회원가입 버튼 선언
        final EditText idEdit=(EditText)findViewById(R.id.id_text); //입력 Id
        final EditText passEdit=(EditText)findViewById(R.id.pass_text); // 입력 pass

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
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=idEdit.getText().toString();
                String password=passEdit.getText().toString();
                loginStart(email,password); // 성공했을때 메인페이지로 보냄.
            }
        });
    }

    //로그인 버튼을 눌렀을때 일어나는 함수.
    public void loginStart(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e) {
                        Toast.makeText(LoginScreenActivity.this, "존재하지 않는 계정입니다 ", Toast.LENGTH_SHORT).show();
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        Toast.makeText(LoginScreenActivity.this, "이메일 형식이 맞지 않습니다", Toast.LENGTH_SHORT).show();
                    } catch (FirebaseNetworkException e) {
                        Toast.makeText(LoginScreenActivity.this, "네트워크 문제가 발생했습니다", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(LoginScreenActivity.this, "Exception", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    save = 1;
                    currentUser = mAuth.getCurrentUser();
                    Toast.makeText(LoginScreenActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(LoginScreenActivity.this, MainScreenActivity.class));
                    finish();
                }
            }
        });
    }
    //    로그아웃 안했으면, 즉 로그인 되어있으면 자동으로 메인페이지로 이동시키기
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        if(currentUser != null){
            save = 1;
            Toast.makeText(LoginScreenActivity.this,"자동로그인 되었습니다.",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginScreenActivity.this, MainScreenActivity.class));
            finish();
        }
    }

}