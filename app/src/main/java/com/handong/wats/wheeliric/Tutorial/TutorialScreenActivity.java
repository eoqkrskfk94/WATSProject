package com.handong.wats.wheeliric.Tutorial;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.handong.wats.wheeliric.R;

public class TutorialScreenActivity extends AppCompatActivity {
    private ViewPager viewPager ;
    private Button closeButton;
    private Dialog myDialog;
    private int totalNum = 3, currentNum = 0;
    TextView pageNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_screen);
        myDialog = new Dialog(this);
        viewPager = (ViewPager) findViewById(R.id.viewPager) ;
        viewPager.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(0);
        pageNumber = (TextView)findViewById(R.id.pageNumber);
        CheckBox tutorialCheckBox = (CheckBox)findViewById(R.id.tutorial_check_box); // 튜토리얼 다시 안보기 저장

        // 튜토리얼 안보기 저장 가져오기
        SharedPreferences pref = getSharedPreferences("tutorialPref", Activity.MODE_PRIVATE);
        String id=pref.getString("tutorial_save", "");
        Boolean chk2 = pref.getBoolean("chk2", false);
        if(chk2 == true){
            tutorialCheckBox.setChecked(chk2);
        }
        // 기본값
        pageNumber.setText("( " + 1 + " / " + totalNum + ")");

        // 지영이가 준 코드, 현재 페이지가 몇 번인지 받아옴
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                currentNum = i;
                pageNumber.setText("( " + (currentNum+1) + " / " + totalNum + ")");
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

        // 닫기버튼
        closeButton = (Button)findViewById(R.id.close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.setContentView(R.layout.check_tutorial_popup);
                myDialog.setCancelable(false);

                Button closeButton = (Button) myDialog.findViewById(R.id.close_button);

                // 확인 버튼 눌렀을 때
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                        myDialog.dismiss();
                    }
                });


                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                myDialog.show();
            }
        });

    }
    private class pagerAdapter extends FragmentStatePagerAdapter
    {
        public pagerAdapter(android.support.v4.app.FragmentManager fm)
        {
            super(fm);
        }
        @Override
        public android.support.v4.app.Fragment getItem(int position)
        {
            switch(position)
            {
                case 0:
                    return new TutorialFragment1();
                case 1:
                    return new TutorialFragment2();
                case 2:
                    return new TutorialFragment3();
                default:
                    return null;
            }
        }
        @Override
        public int getCount()
        {
            return totalNum;
        }
    }

    // 튜토리얼 다시 안뜨게 자동 저장
    @Override
    public void onStop() {
        super.onStop();

        SharedPreferences pref = getSharedPreferences("tutorialPref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
//        EditText etId=(EditText)findViewById(R.id.id_text);
        CheckBox etIdSave=(CheckBox)findViewById(R.id.tutorial_check_box);

        //SharedPreferences에 각 아이디를 지정하고 EditText 내용을 저장한다.


//        editor.putString("id_save", etId.getText().toString());
//        editor.putBoolean("chk1", etIdSave.isChecked());

        editor.commit();

    }

}
