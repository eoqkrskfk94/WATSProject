package com.example.mjkim.watsproject.FirstSreenFraments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mjkim.watsproject.LoginScreenActivity;
import com.example.mjkim.watsproject.MainScreenActivity;
import com.example.mjkim.watsproject.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class MypageFragment extends Fragment {
    private FirebaseAuth auth;


    public MypageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        auth= FirebaseAuth.getInstance();
        View v = inflater.inflate(R.layout.fragment_mypage, container,false);

        Button logout=(Button) v.findViewById(R.id.log_out_button);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                auth.signOut();

                Intent intent =new Intent(getActivity(), LoginScreenActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
//
//    public void LogOutButton (View view){
//
//        Toast.makeText(getActivity(),"눌렀어",Toast.LENGTH_SHORT).show();
//        System.out.println("눌렀어");
//    }

}
