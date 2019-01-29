package com.example.mjkim.watsproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BlogScreenActivity extends AppCompatActivity {

    WebView naverBlogView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_screen);

        Intent intent = getIntent();
        naverBlogView = (WebView) findViewById(R.id.NaverBlog);
        naverBlogView.setWebViewClient(new WebViewClient());
        naverBlogView.getSettings().setJavaScriptEnabled(true);
        naverBlogView.loadUrl(intent.getExtras().getString("LINK"));
        System.out.println(intent.getExtras().getString("LINK"));
    }
}
