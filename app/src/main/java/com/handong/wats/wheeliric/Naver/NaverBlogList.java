package com.handong.wats.wheeliric.Naver;

public class NaverBlogList {

    private String name;
    private String link;
    private String description;
    private String bloggerlink;
    private String bloggername;
    private String postdate;



    public String getName() { return name; }

    public String getLink() { return link; }

    public String getDescription() { return description; }

    public String getBloggerlink() { return bloggerlink; }

    public String getPostdate() { return postdate; }

    public String getBloogername() { return bloggername; }



    public NaverBlogList(){}

    public NaverBlogList(String name, String link, String description, String bloggerlink, String bloggername ,String postdate){
        this.name = name;
        this.link = link;
        this.description = description;
        this.bloggerlink = bloggerlink;
        this.bloggername = bloggername;
        this.postdate = postdate;

    }
}
