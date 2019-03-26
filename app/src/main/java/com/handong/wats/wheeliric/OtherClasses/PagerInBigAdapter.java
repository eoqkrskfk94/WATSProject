package com.handong.wats.wheeliric.OtherClasses;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

public class PagerInBigAdapter extends PagerAdapter {

    Context context;
    BitmapFactory.Options options;
    List<StorageReference> obref;
    PhotoViewAttacher mAttacher;
    public PagerInBigAdapter(Context context) {
        this.context = context;
        options = new BitmapFactory.Options();
    }
    //ref를 List로 저장한걸 불러옴.
    public PagerInBigAdapter(List<StorageReference> res, Context context){
        obref = res;
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

        mAttacher=new PhotoViewAttacher(imageView);
        mAttacher.setScaleType(ImageView.ScaleType.FIT_XY);

        // 사진 띄우기
        Glide.with(context)
                .load(obref.get(position))
                .into(imageView);
        ((ViewPager) container).addView(imageView, 0);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }

}
