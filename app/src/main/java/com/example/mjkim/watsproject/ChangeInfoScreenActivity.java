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
        setContentView(R.layout.activity_change_info_screen);

        myDialog = new Dialog(this); //수정하기 팝업 변수 선언



    }




    public void ChangeButton (View view){ //수정하기 버튼 눌렀을때

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
        myDialog.show(); //수정하기 팝업창.



    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainScreenActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

    // 뒤로가기 버튼 누르면 메인으로
    public void BackButton(View view) {
        finish();
        Intent intent = new Intent(this, MainScreenActivity.class);
        startActivity(intent);
    }


}
