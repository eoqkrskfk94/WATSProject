package com.example.mjkim.watsproject.Review;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import org.json.JSONObject;
import java.util.ArrayList;

public class ReviewFirebaseJson {

    private static String json;
    private static String full_json;
    private static int review_num;
    public static int i = 0;

    public static ArrayList<ReviewJson> reviewJson = new ArrayList<ReviewJson>();
    public static ReviewJson fullReviewJson = new ReviewJson();
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();


    public void getJson(final String location_name, final int num){


        //System.out.println("데이터베이스: "+ databaseReference.getKey());

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                readData(dataSnapshot, location_name, num);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void getFullJson(){


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Object object = ds.getValue(Object.class);
                    full_json = new Gson().toJson(object);



                    try{
                        JSONObject jsonObj = new JSONObject(full_json);
                        review_num = jsonObj.length();
                        fullReviewJson = new ReviewJson(review_num, jsonObj.toString(), jsonObj.names());
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                        System.out.println("전체 제이슨: " + fullReviewJson.getReview_json_string());
                        System.out.println("이름들: " +  fullReviewJson.getReview_json_userID());


                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



    public void readData(DataSnapshot dataSnapshot, String name, int num){




        for(DataSnapshot ds : dataSnapshot.getChildren()){

            if(ds.getKey().equals("review lists"))
            {
                Object object = ds.getValue(Object.class);
                json = new Gson().toJson(object);



                try{
                    JSONObject jsonObj = new JSONObject(json);

                    if(jsonObj.has(name)) {
                        review_num = jsonObj.getJSONObject(name).length();
                        reviewJson.add(num, new ReviewJson(name, review_num, jsonObj.getString(name), jsonObj.getJSONObject(name).names()));
                    }
                    else
                    {
                        reviewJson.add(num,new ReviewJson(null, 0, null, null));
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }


    }

    public static int getReview_num() {
        return review_num;
    }

    public static void setReview_num(int review_num) {
        ReviewFirebaseJson.review_num = review_num;
    }

}
