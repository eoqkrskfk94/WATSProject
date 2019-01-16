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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
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
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_screen);
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

        createUser(str_email,str_password); // 회원가입 함수로 이동.


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
        String str_name=name.getText().toString();
        String str_year=year.getText().toString();
        String str_month=month.getText().toString();
        String str_day=day.getText().toString();
        String str_nickname=nickname.getText().toString();

        //비밀번호와 비밀번호 확인이 다를경우 재시작.
        if (!str_password.equals(str_password_check)) {
            Toast.makeText(SignInScreenActivity.this, "비밀번호가 서로 다릅니다. 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SignInScreenActivity.this, SignInScreenActivity.class));
        } else {
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
                                currentUser = mAuth.getCurrentUser(); // 정보를 currentUser에 저장.
                                myDialog.show(); //회원가입 팝업창.

                            }
                        }
                    });
        }
    }


}
