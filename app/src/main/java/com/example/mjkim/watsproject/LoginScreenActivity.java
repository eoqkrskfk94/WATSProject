package com.example.mjkim.watsproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mjkim.watsproject.User.UserInformation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginScreenActivity extends AppCompatActivity {
    public static int save = 0; // 로그인 되어있음을 확인하는 변수
    private FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        mAuth = FirebaseAuth.getInstance(); // 로그인 작업의 onCreate 메소드에서 FirebaseAuth 개체의 공유 인스턴스를 가져옵니다
        mAuth.setLanguageCode("ko"); //한국어
        currentUser = mAuth.getCurrentUser();


        Button loginButton = (Button)findViewById(R.id.login_button); //로그인 버튼 선언
        Button findIDButton = (Button)findViewById(R.id.find_id); //아이디 찾기 선언
        Button findPasswButton = (Button)findViewById(R.id.find_passw); //비밀번호 찾기 버튼 선언
        Button createUserButton = (Button)findViewById(R.id.create_acc); //회원가입 버튼 선언
        Button nonMemberButton = (Button)findViewById(R.id.nonmember_button); //비회원 이용하기 버튼 선언
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

        //비회원 이용하기 버튼을 눌렀을때
        createUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginScreenActivity.this,MainScreenActivity.class);
                startActivity(intent);
            }
        });

        //아이디 찾기 버튼을 눌렀을때
        findIDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FindId();
            }
        });

        //비밀번호 찾기 버튼을 눌렀을때
        findPasswButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FindPass();
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



    /// 아이디 찾는 메소드
    public  void FindId(){
        Context context=getApplicationContext();
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout=inflater.inflate(R.layout.find_id_popup,null);

        AlertDialog.Builder aDialog=new AlertDialog.Builder(this);
        aDialog.setView(layout);

        final EditText name=(EditText)layout.findViewById(R.id.edit_id);
        final EditText year=(EditText)layout.findViewById(R.id.edit_year);
        final EditText month=(EditText)layout.findViewById(R.id.edit_month);
        final EditText day=(EditText)layout.findViewById(R.id.edit_day);
        final DatabaseReference mDatabase = database.getReference();
        aDialog.setPositiveButton("입력",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //항목중에 빈칸이 있을 경우 실행x
                        if (name.getText().toString().equals("") || year.getText().toString().equals("") || month.getText().toString().equals("") || day.getText().toString().equals("")) {
                            Toast.makeText(LoginScreenActivity.this, "모든 항목을 입력해주세요.....", Toast.LENGTH_LONG).show(); }

                        //모든 항목에 입력 되었을경우
                        else {
                            mDatabase.child("user lists").addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                    //입력한 이름과 데이타베이스에 저장되어있는 이름이 같을때 아래 구문 실행.
                                    final String key = dataSnapshot.getKey();
                                    mDatabase.child("user lists").child(key).orderByChild("userName").equalTo(name.getText().toString()).addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                            mDatabase.child("user lists").child(key).orderByChild("userYear").equalTo(year.getText().toString()).addChildEventListener(new ChildEventListener() {
                                                @Override
                                                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                                    mDatabase.child("user lists").child(key).orderByChild("userMonth").equalTo(month.getText().toString()).addChildEventListener(new ChildEventListener() {
                                                        @Override
                                                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                                            // 이름, 년도, 월이 같을때 아래구문 실행.
                                                            UserInformation myreview = dataSnapshot.getValue(UserInformation.class);

                                                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginScreenActivity.this);
                                                            builder.setTitle("가입했던 이메일");
                                                            builder.setMessage(myreview.getUserEmail());
                                                            builder.setPositiveButton("확인",
                                                                    new DialogInterface.OnClickListener() {
                                                                        public void onClick(DialogInterface dialog, int which) {

                                                                        }
                                                                    });
                                                            builder.show();
                                                        }
                                                        @Override
                                                        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
                                                        @Override
                                                        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }
                                                        @Override
                                                        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError databaseError) { }
                                                    });
                                                }
                                                @Override
                                                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
                                                @Override
                                                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }
                                                @Override
                                                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) { }
                                            });
                                        }
                                        @Override
                                        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
                                        @Override
                                        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }
                                        @Override
                                        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) { }
                                    });
                                }
                                @Override
                                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
                                @Override
                                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }
                                @Override
                                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) { }
                            });
                        }
                    }
                });
        aDialog.setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        aDialog.show();
    }

    //비밀번호 재설정 메소드
    public void FindPass(){
        final EditText edittext = new EditText(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("비밀번호 재설정합니다.");
        builder.setMessage("가입했던 이메일을 입력하면 재설정 메일이 전송됩니다.");
        builder.setView(edittext);
        builder.setPositiveButton("입력",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (!edittext.getText().toString().equals("")) {
                            mAuth.sendPasswordResetEmail(edittext.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(LoginScreenActivity.this, "전송 되었습니다. 이메일을 확인하세요", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(LoginScreenActivity.this, "이메일 전송에 실패했습니다.", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(LoginScreenActivity.this, "이메일을 정확히 입력해주세요", Toast.LENGTH_LONG).show();
                        }
                    }
                });
        builder.setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
    }
    //로그인 버튼을 눌렀을때 일어나는 함수.
    public void loginStart(String email, String password) {
        if(!email.equals("") || !password.equals("")) {
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