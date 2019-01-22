package com.example.mjkim.watsproject;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChangeInfoScreenActivity extends AppCompatActivity {

    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_info_screen);

        myDialog = new Dialog(this); //회원가입 팝업 변수 선언
        Button changeButton = (Button)findViewById(R.id.change_button);

        //수정하기 버튼을 눌렀을때
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.setContentView(R.layout.change_popup);
                myDialog.setCancelable(false);

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
            }
        });


    }
}
