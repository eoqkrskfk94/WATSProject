package com.example.mjkim.watsproject.Naver;

public class NaverLocationList {

    private String name;        //지역 이름
    private String link;        //지역 링크
    private String description; //지역 정보
    private String telephone;   //지역 번호
    private String address;     //지역 주소
    private String road_address;//지역 도로주소
    private String category;    //지역 분류
    private int mapx;           //지역 x좌표
    private int mapy;           //지역 y좌표
    private int review_num;     //후기 갯수

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public int getReview_num() { return review_num; }

    public void setReview_num(int review_num) {
        this.review_num = review_num;
    }

    public String getName() { return name; }

    public String getLink() { return link; }

    public String getDescription() { return description; }

    public String getTelephone() { return telephone; }

    public String getAddress() { return address; }

    public String getRoad_address() { return road_address; }

    public int getMapx() { return mapx; }

    public int getMapy() { return mapy; }

    public NaverLocationList(){}

    public NaverLocationList(String name, String category, String link, String description, String telephone, String address, String road_address, int mapx, int mapy, int review_num){

        this.name = name;
        this.link = link;
        this.category = category;
        this.description = description;
        this.telephone = telephone;
        this.address = address;
        this.road_address = road_address;
        this.mapx = mapx;
        this.mapy = mapy;
        this.review_num = review_num;
    }
}
