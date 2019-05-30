package com.handong.wats.wheeliric;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.handong.wats.wheeliric.Tutorial.TutorialScreenActivity;

import java.util.Timer;
import java.util.TimerTask;

public class  LoadingScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 4000);

//        Handler handler =new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
////                Intent intent=new Intent(LoadingScreenActivity.this,TutorialScreenActivity.class);
//                Intent intent=new Intent(LoadingScreenActivity.this, MainScreenActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        },4000);
    }
}
