package com.handong.wats.wheeliric;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.handong.wats.wheeliric.R;
import com.handong.wats.wheeliric.Review.MapReviewAdapter;
import com.handong.wats.wheeliric.Review.ReviewAdapter;

public class MoreReviewScreenActivity extends AppCompatActivity {

    private ReviewAdapter reviewAdapter;
    private MapReviewAdapter mapReviewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_review_screen);

        Button cancelButton = (Button)findViewById(R.id.cancel_button);

        //cancel 버튼 눌렀을때 전 화면을 돌아간다
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ReviewAdapter.select = 0;

        ListView lv = (ListView) findViewById(R.id.more_review_list);


        if(getIntent().getExtras().getInt("SELECT") == 0){
            reviewAdapter = new ReviewAdapter(MoreReviewScreenActivity.this, LocationDetailScreenActivity.reviewLists);
            lv.setAdapter(reviewAdapter);
        }

        if(getIntent().getExtras().getInt("SELECT") == 1){
            reviewAdapter = new ReviewAdapter(MoreReviewScreenActivity.this, LocationDetailFromMapScreenActivity.reviewLists);
            lv.setAdapter(reviewAdapter);
        }

    }
}
