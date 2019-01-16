package com.example.mjkim.watsproject.Naver;

import android.os.AsyncTask;
import android.text.Html;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class NaverLocationSearch extends AsyncTask<String, Void, ArrayList<NaverLocationList>> {


    final static String clientId = "fABlFcFP0h8Xidu9CIqO";//애플리케이션 클라이언트 아이디값";
    final static String clientSecret = "gKlwnfavi0";//애플리케이션 클라이언트 시크릿값";
    public static int total_num = 0;


    private ArrayList<String> location_result;


    @Override
    protected void onPreExecute() { super.onPreExecute(); }



    @Override
    protected ArrayList<NaverLocationList> doInBackground(String... strings) {




        String result = getJson(strings[0]);

        String name;
        String link;
        String category;
        String description;
        String telephone;
        String address;
        String road_address;
        int mapx;
        int mapy;
        int review_num;






        //파서기 시작
        location_result = new ArrayList<String>();
        ArrayList<NaverLocationList> naverLocationList = new ArrayList<NaverLocationList>();

        try {

            String new_response = getJson(strings[0]); //파서할 새로운 response
            JSONObject jsonObj = new JSONObject(new_response);

            JSONArray items = jsonObj.getJSONArray("items");
            total_num = jsonObj.getInt("total");
            int num = 0;

            for (int i = 0; i < items.length(); i++) {


                JSONObject obj = items.getJSONObject(i);
                name = obj.getString("title");
                name = Html.fromHtml(name).toString();
                //firebaseJson.getJson(name,num);

                link = obj.getString("link");
                category = obj.getString("category");
                description = obj.getString("description");
                telephone = obj.getString("telephone");
                address = obj.getString("address");
                road_address = obj.getString("roadAddress");
                mapx = obj.getInt("mapx");
                mapy = obj.getInt("mapy");


                naverLocationList.add(num++, new NaverLocationList(name, category, link, description, telephone, address, road_address, mapx, mapy, 0));

            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        //파서기 끝
        return naverLocationList;

    }

    @Override
    protected void onProgressUpdate(Void...params){ }

    @Override
    protected void onPostExecute(ArrayList<NaverLocationList> result){
        super.onPostExecute(result);
    }



    public String getJson(String string) {

        String response = "";

        try {
            int display = 10; //총 결과물 갯수
            String text = URLEncoder.encode(string, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/local?query="+ text + "&display=" + display + "&"; // json 결과
            //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer temp_response = new StringBuffer(); //문자열 담는 객체
            while ((inputLine = br.readLine()) != null) {
                temp_response.append(inputLine);
                temp_response.append("\n");
            }
            br.close();
            response = temp_response.toString();


        } catch (Exception e) {
        }

        System.out.println(response);
        return response;
    }
}
