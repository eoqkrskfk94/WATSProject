package com.example.mjkim.watsproject.OtherClasses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.mjkim.watsproject.CertainReviewScreenActivity;
import com.example.mjkim.watsproject.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;
import java.util.zip.Inflater;
import uk.co.senab.photoview.PhotoViewAttacher;


public class PagerInAdapter extends PagerAdapter {

    Context context;
    BitmapFactory.Options options;
    List<StorageReference> obref;
    List<String> imageString;

    public PagerInAdapter(Context context) {
        this.context = context;
        options = new BitmapFactory.Options();
    }
    //ref를 List로 저장한걸 불러옴.
    public PagerInAdapter(List<StorageReference> res, Context context){
        obref = res;
        this.context = context;
    }
    public PagerInAdapter(List<StorageReference> res, List<String> imageStrings, Context context){
        obref = res;
        imageString=imageStrings;
        this.context = context;
    }

    //불러오는 사진의 갯수
    @Override
    public int getCount() {
        return obref.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        // 사진 띄우기
            Glide.with(context)
                    .load(obref.get(position))
                    .apply(new RequestOptions().centerCrop())
                    .into(imageView);
        ((ViewPager) container).addView(imageView, 0);

        //    //사진을 클릭하면 크게보는 엑티비티로 이동
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context , ImageBigShowActivity.class);

                int count=getCount();
                for(int i=0;i<count;i++) {
                    intent.putExtra("IMAGE"+(i+1), imageString.get(i));
                }
                intent.putExtra("position",count);


                  context.startActivity(intent);
            }
        });


        return imageView;
    }




    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }

}
