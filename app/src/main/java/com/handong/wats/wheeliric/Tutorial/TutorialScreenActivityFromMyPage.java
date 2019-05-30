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
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.handong.wats.wheeliric.R;

public class TutorialScreenActivityFromMyPage extends AppCompatActivity {
    private ViewPager viewPager ;
    private Button closeButton;
    static private int totalNum = 3, currentNum = 0;
    static TextView pageNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_screen);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(0);
        pageNumber = (TextView)findViewById(R.id.pageNumber);

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
        closeButton = (Button) findViewById(R.id.close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private class PagerAdapter extends FragmentStatePagerAdapter
    {
        public PagerAdapter(android.support.v4.app.FragmentManager fm)
        {
            super(fm);
        }
        @Override
        public android.support.v4.app.Fragment getItem(int position)
        {
            // 현재 페이지 띄우기
//            currentNum = position + 1;
//            pageNumber.setText("( " + (currentNum+1) + " / " + totalNum + ")");
            switch(position)
            {
                case 0:
//                    currentNum = 1;
//                    pageNumber.setText("( " + currentNum + " / " + totalNum + ")");
                    return new TutorialFragment1();
                case 1:
//                    currentNum = 2;
//                    pageNumber.setText("( " + currentNum + " / " + totalNum + ")");
                    return new TutorialFragment2();
                case 2:
//                    currentNum = 3;
//                    pageNumber.setText("( " + currentNum + " / " + totalNum + ")");
                    return new TutorialFragment3();
                default:
                    return null;
            }
        }
        @Override
        public int getCount()
        {
            return 3;
        }
    }


}
