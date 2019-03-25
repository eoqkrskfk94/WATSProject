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
import android.widget.Toast;

import com.example.mjkim.watsproject.User.UserInformation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//이메일 회원가입하는 액티비티.(로그인화면에서 회원가입 눌렀을때 나오는 화면)
public class SignInScreenActivity extends AppCompatActivity {

    Dialog myDialog;
    private EditText email;
    private EditText password;
    private EditText password_check;
    private EditText name;
    private EditText year;
    private EditText month;
    private EditText day;
    private EditText nickname;
    public UserInformation userInformation=new UserInformation();
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);
        myDialog = new Dialog(this); //회원가입 팝업 변수 선언
        mAuth = FirebaseAuth.getInstance();
        email=(EditText)findViewById(R.id.edit_id); //이메일 선언
        password=(EditText)findViewById(R.id.edit_passw); //비밀번호 선언
        password_check=(EditText)findViewById(R.id.edit_passw_check); // 비밀번호 체크 선언
        name=(EditText)findViewById(R.id.edit_name); // 이름 선언
        year=(EditText)findViewById(R.id.edit_year); // 년도 선언
        month=(EditText)findViewById(R.id.edit_month); // 월 선언
        day=(EditText)findViewById(R.id.edit_day); // 일 선언
        nickname=(EditText)findViewById(R.id.edit_nickname); // 별명 선언

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

        String str_email=email.getText().toString();
        String str_password=password.getText().toString();
         // 사용자에게 입력받은 값을 변수로 저장

        if(!str_email.equals("") || !str_password.equals(""))
            createUser(str_email,str_password); // 회원가입 함수로 이동.
        else{
            Toast.makeText(SignInScreenActivity.this,"모든 항목을 입력해주세요.",Toast.LENGTH_LONG).show();
        }

        //닫기 버튼을 눌렀을때
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignInScreenActivity.this,MainScreenActivity.class);
                startActivity(intent);
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
    }

    //회원가입 하는 메소드
    private void createUser(String str_email, String str_password) {
        String str_password_check=password_check.getText().toString();
        final String str_name=name.getText().toString();
        String str_year=year.getText().toString();
        String str_month=month.getText().toString();
        String str_day=day.getText().toString();
        String str_nickname=nickname.getText().toString();

        //파이어베이스에 유저정보 올리기.
        userInformation.setUserEmail(str_email);
        userInformation.setUserName(str_name);
        userInformation.setUserYear(str_year);
        userInformation.setUserMonth(str_month);
        userInformation.setUserDay(str_day);
        userInformation.setUserNickname(str_nickname);
        userInformation=new UserInformation(str_email,str_name,str_year,str_month,str_day,str_nickname);
        final DatabaseReference userRef=databaseReference.child("user lists");
         // email 에는 @가 들어가서 파이어베이스에 저장이 안됨.

        //비밀번호와 비밀번호 확인이 다를경우 재시작.
        if (!str_password.equals(str_password_check)) {
            Toast.makeText(SignInScreenActivity.this, "비밀번호가 서로 다릅니다. 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SignInScreenActivity.this, SignInScreenActivity.class));
        }
        else if(str_name.equals("")){
            Toast.makeText(SignInScreenActivity.this, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
        }
        else if(str_nickname.equals("")){
            Toast.makeText(SignInScreenActivity.this, "별명을 입력해주세요.", Toast.LENGTH_SHORT).show();
        }
        else if(str_year.equals("")){
            Toast.makeText(SignInScreenActivity.this, "날짜를 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
        }
        else if(str_month.equals("")){
            Toast.makeText(SignInScreenActivity.this, "날짜를 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
        }
        else if(str_day.equals("")){
            Toast.makeText(SignInScreenActivity.this, "날짜를 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
        }
        else
         {
            mAuth.createUserWithEmailAndPassword(str_email, str_password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                try {
                                    throw task.getException();
                                } catch (FirebaseAuthWeakPasswordException e) {
                                    Toast.makeText(SignInScreenActivity.this, "비밀번호가 간단합니다", Toast.LENGTH_SHORT).show();
                                } catch (FirebaseAuthInvalidCredentialsException e) {
                                    Toast.makeText(SignInScreenActivity.this, "이메일 형식에 맞지 않습니다", Toast.LENGTH_SHORT).show();
                                } catch (FirebaseAuthUserCollisionException e) {
                                    Toast.makeText(SignInScreenActivity.this, "이미 존재하는 계정 입니다.", Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    Toast.makeText(SignInScreenActivity.this, "다시 확인해주세요", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                //가입이 성공적일때.,
                                currentUser = mAuth.getCurrentUser(); // 정보를 currentUser에 저장.
                                mAuth.setLanguageCode("ko"); //한국어
                                userRef.child(str_name).push().setValue(userInformation);
//                                //이메일 인증메일 보내기.
//                                mAuth.getCurrentUser().sendEmailVerification()
//                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                            @Override
//                                            public void onComplete(@NonNull Task<Void> task) {
//                                                if(task.isSuccessful()){
//                                                //    Toast.makeText(SignInScreenActivity.this,"이메일에 인증메일을 보냈습니다.",Toast.LENGTH_SHORT).show();
//                                                }
//                                                else {
//                                                    //메일 전송 실패
//                                                }
//                                            }
//                                        });
                                myDialog.show(); //회원가입 팝업창.
                            }
                        }
                    });
        }
    }
}
