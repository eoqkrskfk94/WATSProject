package com.example.mjkim.watsproject.Naver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mjkim.watsproject.BlogScreenActivity;
import com.example.mjkim.watsproject.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NaverBlogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mInflater;
    private Activity m_activity;
    private ArrayList<NaverBlogList> arr;
    public static int select = 0; //출력되는 블로그 개수 선택 1이면 5개 2이면 최대 20개


    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView title;
        protected TextView description;
        protected TextView bloggername;
        protected TextView postdate;
        protected LinearLayout layoutview;

        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.blog_name);
            description = view.findViewById(R.id.blog_description);
            bloggername = view.findViewById(R.id.blog_blogger);
            postdate = view.findViewById(R.id.blog_date);

        }
    }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_list_box,parent, false );
            return new ViewHolder(view);

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position){

            ViewHolder viewHolder = (ViewHolder) holder;


            viewHolder.title.setText(arr.get(position).getName());
            viewHolder.bloggername.setText(arr.get(position).getBloogername());
            viewHolder.description.setText(arr.get(position).getDescription());
            viewHolder.postdate.setText(arr.get(position).getPostdate());

        }

        @Override
        public int getItemCount() {
            if (select == 1) {
                if (NaverBlogSearch.total_num < 3) return arr.size();
                else return 3;
            } else return arr.size();
        }






    public void GoIntent(int a) {

        Intent intent = new Intent(m_activity, BlogScreenActivity.class);
        intent.putExtra("LINK", arr.get(a).getLink()); //해당 블로그 링크를 보내준다
        m_activity.startActivity(intent);
    }

}
