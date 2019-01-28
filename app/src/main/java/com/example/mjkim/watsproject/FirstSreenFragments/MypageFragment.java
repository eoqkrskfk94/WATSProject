package com.example.mjkim.watsproject.FirstSreenFragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mjkim.watsproject.ChangeInfoScreenActivity;
import com.example.mjkim.watsproject.LoginScreenActivity;
import com.example.mjkim.watsproject.MainScreenActivity;
import com.example.mjkim.watsproject.R;
import com.example.mjkim.watsproject.SearchScreenActivity;
import com.example.mjkim.watsproject.User.UserInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class MypageFragment extends Fragment {
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    public UserInformation userInformation=new UserInformation();

    public MypageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        auth= FirebaseAuth.getInstance();
        final String userEmail = new String(auth.getCurrentUser().getEmail()); //Useremail이 현재 사용자 이메일이다.
        mDatabase = database.getReference();
        final DatabaseReference myreview = database.getReference();

        View v = inflater.inflate(R.layout.fragment_mypage, container,false);

        Button logout=(Button) v.findViewById(R.id.log_out_button);
        Button informationButton=(Button)v.findViewById(R.id.see_information_button);
        Button myreviewButton=(Button)v.findViewById(R.id.see_review_button);
        Button changeButton=(Button)v.findViewById(R.id.change_info_button);



        final TextView textId=(TextView)v.findViewById(R.id.textView_id);

        //닉네입을 보여줌.
        mDatabase.child("user lists").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                // 데이타베이스에 저장되어있는 이름값(ex: 남준영)의 userEmail값이 사용자와 같을때 아래구문 실행.
                mDatabase.child("user lists").child(dataSnapshot.getKey()).orderByChild("userEmail").equalTo(userEmail).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        UserInformation userInformation = dataSnapshot.getValue(UserInformation.class);
                        textId.setText(userInformation.getUserNickname()); // 그 유저에 해당하는 닉네임값을 보여줌.
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


        //로그아웃 눌렀을때
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                auth.signOut();
                Intent intent =new Intent(getActivity(), LoginScreenActivity.class);
                startActivity(intent);
            }
        });

        //회원정보 수정 버튼 눌럿을때
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),ChangeInfoScreenActivity.class);
                startActivity(intent);
            }
        });

        //내가 작성한 후기 버튼 눌렀을때
        myreviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"준비중입니다.",Toast.LENGTH_LONG).show();
            }
        });


        //어플정보를 눌렀을때
        informationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("1.01버전");
                builder.setMessage("개발자: 김명진 남준영 정희석");
                builder.setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();
            }
        });

        return v;
    }




}
