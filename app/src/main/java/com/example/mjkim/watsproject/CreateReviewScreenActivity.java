package com.example.mjkim.watsproject;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mjkim.watsproject.Review.ReviewData;
import com.example.mjkim.watsproject.Review.ReviewList;
import com.example.mjkim.watsproject.User.UserInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateReviewScreenActivity extends AppCompatActivity {

    public ReviewList reviewList = new ReviewList();
    Dialog myDialog;
    CheckBox chk[] = new CheckBox[6]; //태그 체크박스
    String userName = ""; //유저 이름
    String userNickName = ""; // 유저 별명
    String key = ""; //키값
    EditText reviewText; //후기 적성부분
    Boolean tag1,tag2,tag3,tag4,tag5,tag6; //태그
    public FirebaseStorage storage;
    public StorageReference storageRef;
    ReviewData reviewData = new ReviewData();
    public final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    public final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 2;
    ImageView picture1,picture2,picture3,picture4,picture5,picture6,picture7,picture8,picture9; //사진 버
    private Uri photoUri;
    private int id_view;
    private static final int pick_from_camera = 0;
    private static final int pick_from_album = 1; // 갤러리 불러올때 요청 상수.
    private static final int REQUEST_IMAGE_CAPTURE = 672;
    String imagePath1="";
    String imagePath2="";
    String imagePath3="";
    String imagePath4="";
    String imagePath5="";
    String imagePath6="";
    String imagePath7="";
    String imagePath8="";
    String imagePath9="";

    String nameAndAdress = ""; //이름이랑 주소 같이 나오는 스트
    private double location_x, location_y;
    //리뷰가 작성된 날짜
    static SimpleDateFormat simpleDateFormat;
    static Date currentTime;
    static String mTime;

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    int pic1=0;
    int pic2=0;
    int pic3=0;
    int pic4=0;
    int pic5=0;
    int pic6=0;
    int pic7=0;
    int pic8=0;
    int pic9=0;
    int exifOrientation;
    int exifDegree;
    static String locationName;
    //파이어베이스 유저 관련 변수 선언
    private DatabaseReference mDatabase;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},0);
        }
        setContentView(R.layout.activity_create_review_screen);
        myDialog = new Dialog(this); //등록하기 팝업 변수 선언
        storage=FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        //파이어베이스 유저 관련 변수 선언
        auth= FirebaseAuth.getInstance();
        mDatabase = database.getReference();
        final String userEmail = new String(auth.getCurrentUser().getEmail()); //Useremail이 현재 사용자 이메일이다.
        final DatabaseReference myreview = database.getReference();
        final TextView textId=(TextView)findViewById(R.id.id_name);
        final TextView textDate = (TextView) findViewById(R.id.vi_date);

        location_x = getIntent().getExtras().getDouble("MAPX");
        location_y = getIntent().getExtras().getDouble("MAPY");
        System.out.println("newmap9 : " + locationName + "  "  + "  " + location_x + "  " + location_y);


        //리뷰가 작성된 날짜
        simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.KOREA);
        currentTime = new Date();
        mTime = simpleDateFormat.format(currentTime);
        textDate.setText(mTime);

        //돌아가기 버튼 선언, 돌아가기 버튼 눌렀을때 전 화면을 돌아간다
        Button backButton = (Button)findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myDialog.setContentView(R.layout.review_stop_popup);
                myDialog.setCancelable(false);

                Button yesButton = (Button) myDialog.findViewById(R.id.yes_button);
                Button noButton = (Button) myDialog.findViewById(R.id.no_button);

                //네 버튼을 눌렀을때
                yesButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                        myDialog.dismiss();
                    }
                });

                //아니요 버튼을 눌렀을때
                noButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myDialog.dismiss();
                    }
                });

                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                myDialog.show();


            }
        });


        //사진 변수선
        picture1 = (ImageView)findViewById(R.id.Imagebutton1);
        picture2 = (ImageView)findViewById(R.id.Imagebutton2);
        picture3 = (ImageView)findViewById(R.id.Imagebutton3);
        picture4 = (ImageView)findViewById(R.id.Imagebutton4);
        picture5 = (ImageView)findViewById(R.id.Imagebutton5);
        picture6 = (ImageView)findViewById(R.id.Imagebutton6);
        picture7 = (ImageView)findViewById(R.id.Imagebutton7);
        picture8 = (ImageView)findViewById(R.id.Imagebutton8);
        picture9 = (ImageView)findViewById(R.id.Imagebutton9);

        //체크박스 변수 선언
        Integer[] numChkIDs = { R.id.checkBox1, R.id.checkBox2, R.id.checkBox3,
                R.id.checkBox4, R.id.checkBox5, R.id.checkBox6};

        for(int i = 0; i < 6; i++){ chk[i] = (CheckBox)findViewById(numChkIDs[i]); }
        //갤러리 사용 권한 체크
        if (ContextCompat.checkSelfPermission(CreateReviewScreenActivity.this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(CreateReviewScreenActivity.this,
                    Manifest.permission.READ_CONTACTS)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(CreateReviewScreenActivity.this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

        if (ContextCompat.checkSelfPermission(CreateReviewScreenActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(CreateReviewScreenActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(CreateReviewScreenActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }


        //유저 별명 불러오기
        mDatabase.child("user lists").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                // 데이타베이스에 저장되어있는 이름값(ex: 남준영)의 userEmail값이 사용자와 같을때 아래구문 실행.

                myreview.child("user lists").child(dataSnapshot.getKey()).orderByChild("userEmail").equalTo(userEmail).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        UserInformation userInformation = dataSnapshot.getValue(UserInformation.class);
                        textId.setText(userInformation.getUserNickname()); // 그 유저에 해당하는 닉네임값을 보여줌.
                        userNickName = userInformation.getUserNickname();
                        userName = userInformation.getUserName();
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }
                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) { }
                });
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });

        reviewText = (EditText) findViewById(R.id.review_text); //후기 작성 부분
    }



    //사진등록 밑에있는 사진중 하나를 클릭했을때.
    public void onClick(final View view) {

        id_view = view.getId();
        if(view.getId() == R.id.Imagebutton1 || view.getId() == R.id.Imagebutton2 || view.getId() == R.id.Imagebutton3
                || view.getId() == R.id.Imagebutton4 || view.getId() == R.id.Imagebutton5 || view.getId() == R.id.Imagebutton6
                || view.getId() == R.id.Imagebutton7 || view.getId() == R.id.Imagebutton8 || view.getId() == R.id.Imagebutton9 ) {


            myDialog = new Dialog(this); //팝업 변수 선언
            myDialog.setContentView(R.layout.image_popup);
            myDialog.setCancelable(false);

            Button cameraButton = (Button) myDialog.findViewById(R.id.camera_button);
            Button galleryButton = (Button) myDialog.findViewById(R.id.gallery_button);
            Button cancelButton = (Button) myDialog.findViewById(R.id.cancel_button);
            Button deleteButton = (Button) myDialog.findViewById(R.id.delete_image_button);

            //카메라 버튼을 눌렀을때
            cameraButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    doTakePhotoAction();
                    myDialog.dismiss();//카메라에서 사진 가져오기 메소드
                }
            });

            //갤러리 버튼을 눌렀을때
            galleryButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    doTakeAlbumAction();
                    myDialog.dismiss();//앨범에서 사진 가져오기 메소드
                }
            });

            //닫기 버튼을 눌렀을때
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 취소버튼을 누르면 사진삭제
                    myDialog.dismiss();
                }
            });

            //삭제 버튼을 눌렀을때
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 취소버튼을 누르면 사진삭제

                    if(id_view==R.id.Imagebutton1) picture1.setImageResource(R.drawable.sample_pic);
                    if(id_view==R.id.Imagebutton2) picture2.setImageResource(R.drawable.sample_pic);
                    if(id_view==R.id.Imagebutton3) picture3.setImageResource(R.drawable.sample_pic);
                    if(id_view==R.id.Imagebutton4) picture4.setImageResource(R.drawable.sample_pic);
                    if(id_view==R.id.Imagebutton5) picture5.setImageResource(R.drawable.sample_pic);
                    if(id_view==R.id.Imagebutton6) picture6.setImageResource(R.drawable.sample_pic);
                    if(id_view==R.id.Imagebutton7) picture7.setImageResource(R.drawable.sample_pic);
                    if(id_view==R.id.Imagebutton8) picture8.setImageResource(R.drawable.sample_pic);
                    if(id_view==R.id.Imagebutton9) picture9.setImageResource(R.drawable.sample_pic);
                    myDialog.dismiss();
                }
            });


            myDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
            myDialog.show(); //팝업창.


        }
    }
    public void doTakePhotoAction(){  //카메라에서 사진 가져오기.
        int permissioncheck= ContextCompat.checkSelfPermission(CreateReviewScreenActivity.this,Manifest.permission.CAMERA);
        if(permissioncheck== PackageManager.PERMISSION_DENIED){
            //권한없음
            ActivityCompat.requestPermissions(CreateReviewScreenActivity.this,new String[]{Manifest.permission.CAMERA},0);
            Toast.makeText(this,"권한없음",Toast.LENGTH_SHORT).show();
        }
        else{ // 권한있을 경우.
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    // Error occurred while creating the File
                }

                if (photoFile != null) {
                    photoUri = FileProvider.getUriForFile(CreateReviewScreenActivity.this, getPackageName(), photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        }
    }
    //카메라 권한받는 메소드
    @Override
    public void onRequestPermissionsResult(int requestCode,@NonNull String[] permissions,@NonNull int[] grantResults){
        if(requestCode==0){
            if(grantResults[0]==0){ Toast.makeText(this,"카메라 권한 승인",Toast.LENGTH_SHORT).show(); }
            else{ Toast.makeText(this,"권한 승인이 거절되었습니다. 카메라를 이용하려면 권한을 승인해야 합니다.",Toast.LENGTH_SHORT).show(); }
        }

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
            }
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                if(grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                }
                else{
                    Toast.makeText(CreateReviewScreenActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }

            }

            return;
        }

        // other 'case' lines to check for other
        // permissions this app might request
    }
    private int exifOrientationToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }
    private Bitmap rotate(Bitmap bitmap, float degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);

        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, null);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
    //앨범에서 이미지 가져오기
    public void doTakeAlbumAction(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, pick_from_album );
    }

    public String getPath(Uri uri){
        String [] proj={MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader=new CursorLoader(this,uri,proj,null,null,null);
        Cursor cursor=cursorLoader.loadInBackground();
        int index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(index);
    }
    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK){

            System.out.println("들어왔어??11");
            //카메라 찍기에서 일어나는 메소드
            if(requestCode == REQUEST_IMAGE_CAPTURE){
                //첫번째 버튼일 경우
                if(id_view == R.id.Imagebutton1){
                    BitmapFactory.Options options=new BitmapFactory.Options();
                    options.inSampleSize=4;
                    Bitmap bitmap = BitmapFactory.decodeFile(imagePath1,options);
                    Bitmap resize = Bitmap.createScaledBitmap(bitmap,500,500,true);



                    ExifInterface exif = null;
                    try { exif = new ExifInterface(imagePath1); } catch (IOException e) { e.printStackTrace(); }
                    if (exif != null) { exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                        exifDegree = exifOrientationToDegrees(exifOrientation); } else { exifDegree = 0; }
                    //picture1.setMaxWidth(45);
                    //picture1.setMaxHeight(45);
                    picture1.setAdjustViewBounds(true);
                    picture1.setImageBitmap(rotate(resize, exifDegree));

                    pic1=2; //카메라 찍기면 2 앨범에서 불러오면 1
                }
                if(id_view == R.id.Imagebutton2){
                    Bitmap bitmap = BitmapFactory.decodeFile(imagePath2);
                    ExifInterface exif = null;
                    try { exif = new ExifInterface(imagePath2); } catch (IOException e) { e.printStackTrace(); }
                    if (exif != null) {
                        exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                        exifDegree = exifOrientationToDegrees(exifOrientation);
                    } else { exifDegree = 0; }
                    picture2.setAdjustViewBounds(true);

                    picture2.setImageBitmap(rotate(bitmap, exifDegree));
                    pic2=2;
                }
                if(id_view == R.id.Imagebutton3){
                    Bitmap bitmap = BitmapFactory.decodeFile(imagePath3);
                    ExifInterface exif = null;
                    try { exif = new ExifInterface(imagePath3); } catch (IOException e) { e.printStackTrace(); }
                    if (exif != null) {
                        exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                        exifDegree = exifOrientationToDegrees(exifOrientation);
                    } else {
                        exifDegree = 0;
                    }
                    picture3.setAdjustViewBounds(true);
                    picture3.setImageBitmap(rotate(bitmap, exifDegree));
                    pic3=2;
                }
                if(id_view == R.id.Imagebutton4){
                    Bitmap bitmap = BitmapFactory.decodeFile(imagePath4);
                    ExifInterface exif = null;
                    try { exif = new ExifInterface(imagePath4); } catch (IOException e) { e.printStackTrace(); }
                    if (exif != null) {
                        exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                        exifDegree = exifOrientationToDegrees(exifOrientation);
                    } else { exifDegree = 0; }
                    picture4.setAdjustViewBounds(true);
                    picture4.setImageBitmap(rotate(bitmap, exifDegree));
                    pic4=2;
                }
                if(id_view == R.id.Imagebutton5){
                    Bitmap bitmap = BitmapFactory.decodeFile(imagePath5);
                    ExifInterface exif = null;
                    try { exif = new ExifInterface(imagePath5); } catch (IOException e) { e.printStackTrace(); }
                    if (exif != null) {
                        exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                        exifDegree = exifOrientationToDegrees(exifOrientation);
                    } else { exifDegree = 0; }
                    picture5.setAdjustViewBounds(true);
                    picture5.setImageBitmap(rotate(bitmap, exifDegree));
                    pic5=2;
                }
                if(id_view == R.id.Imagebutton6){
                    Bitmap bitmap = BitmapFactory.decodeFile(imagePath6);
                    ExifInterface exif = null;
                    try { exif = new ExifInterface(imagePath6); } catch (IOException e) { e.printStackTrace(); }
                    if (exif != null) {
                        exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                        exifDegree = exifOrientationToDegrees(exifOrientation); } else { exifDegree = 0; }
                    picture6.setAdjustViewBounds(true);
                    picture6.setImageBitmap(rotate(bitmap, exifDegree));
                    pic6=2;
                }
                if(id_view == R.id.Imagebutton7){
                    Bitmap bitmap = BitmapFactory.decodeFile(imagePath7);
                    ExifInterface exif = null;
                    try { exif = new ExifInterface(imagePath7); } catch (IOException e) { e.printStackTrace(); }
                    if (exif != null) {
                        exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                        exifDegree = exifOrientationToDegrees(exifOrientation); } else { exifDegree = 0; }
                    picture7.setAdjustViewBounds(true);
                    picture7.setImageBitmap(rotate(bitmap, exifDegree));
                    pic7=2;
                }
                if(id_view == R.id.Imagebutton8){
                    Bitmap bitmap = BitmapFactory.decodeFile(imagePath8);
                    ExifInterface exif = null;
                    try { exif = new ExifInterface(imagePath8); } catch (IOException e) { e.printStackTrace(); }
                    if (exif != null) {
                        exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                        exifDegree = exifOrientationToDegrees(exifOrientation); } else { exifDegree = 0; }
                    picture8.setAdjustViewBounds(true);
                    picture8.setImageBitmap(rotate(bitmap, exifDegree));
                    pic8=2;
                }
                if(id_view == R.id.Imagebutton9){
                    Bitmap bitmap = BitmapFactory.decodeFile(imagePath9);
                    ExifInterface exif = null;
                    try { exif = new ExifInterface(imagePath9); } catch (IOException e) { e.printStackTrace(); }
                    if (exif != null) {
                        exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                        exifDegree = exifOrientationToDegrees(exifOrientation); } else { exifDegree = 0; }
                    picture9.setAdjustViewBounds(true);
                    picture9.setImageBitmap(rotate(bitmap, exifDegree));
                    pic9=2;
                }
            }
        }
        if(requestCode==pick_from_album) {
            if (id_view == R.id.Imagebutton1) {
                if(data!=null) {

                    System.out.println("들어왔어??22");
                    imagePath1 = getPath(data.getData());
                    File f1 = new File(imagePath1);
                    picture1.setAdjustViewBounds(true);
                    picture1.setImageURI(Uri.fromFile(f1));
                    pic1 = 1;
                }
            }
            if (id_view == R.id.Imagebutton2) {
                if(data!=null) {
                    imagePath2 = getPath(data.getData());
                    File f2 = new File(imagePath2);
                    picture2.setAdjustViewBounds(true);
                    picture2.setImageURI(Uri.fromFile(f2));
                    pic2 = 1;
                }
            }
            if (id_view == R.id.Imagebutton3) {
                if(data!=null) {
                    imagePath3 = getPath(data.getData());
                    File f3 = new File(imagePath3);
                    picture3.setAdjustViewBounds(true);
                    picture3.setImageURI(Uri.fromFile(f3));
                    pic3 = 1;
                }
            }
            if (id_view == R.id.Imagebutton4) {
                if(data!=null) {
                    imagePath4 = getPath(data.getData());
                    File f4 = new File(imagePath4);
                    picture4.setAdjustViewBounds(true);
                    picture4.setImageURI(Uri.fromFile(f4));
                    pic4 = 1;
                }
            }
            if (id_view == R.id.Imagebutton5) {
                if(data!=null) {
                    imagePath5 = getPath(data.getData());
                    File f5 = new File(imagePath5);
                    picture5.setAdjustViewBounds(true);
                    picture5.setImageURI(Uri.fromFile(f5));
                    pic5 = 1;
                }
            }
            if (id_view == R.id.Imagebutton6) {
                if(data!=null) {
                    imagePath6 = getPath(data.getData());
                    File f6 = new File(imagePath6);
                    picture6.setAdjustViewBounds(true);
                    picture6.setImageURI(Uri.fromFile(f6));
                    pic6 = 1;
                }
            }
            if (id_view == R.id.Imagebutton7) {
                if(data!=null) {
                    imagePath7 = getPath(data.getData());
                    File f7 = new File(imagePath7);
                    picture7.setAdjustViewBounds(true);
                    picture7.setImageURI(Uri.fromFile(f7));
                    pic7 = 1;
                }
            }
            if (id_view == R.id.Imagebutton8) {
                if(data!=null) {
                    imagePath8 = getPath(data.getData());
                    File f8 = new File(imagePath8);
                    picture8.setAdjustViewBounds(true);
                    picture8.setImageURI(Uri.fromFile(f8));
                    pic8 = 1;
                }
            }
            if (id_view == R.id.Imagebutton9) {
                if(data!=null) {
                    imagePath9 = getPath(data.getData());
                    File f9 = new File(imagePath9);
                    picture9.setAdjustViewBounds(true);
                    picture9.setImageURI(Uri.fromFile(f9));
                    pic9 = 1;
                }
            }
        }
    }
    //이미지가 저장될 파일을 만드는 함수
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,      /* prefix */
                ".jpg",         /* suffix */
                storageDir          /* directory */
        );
//        BitmapFactory.Options options=new BitmapFactory.Options();
//        options.inSampleSize=4;
//        Bitmap bitmap = BitmapFactory.decodeFile(String.valueOf(image),options);
//        Bitmap resize = Bitmap.createScaledBitmap(bitmap,300,400,true);

        if(id_view==R.id.Imagebutton1){ imagePath1 = image.getAbsolutePath(); }
        if(id_view==R.id.Imagebutton2){ imagePath2 = image.getAbsolutePath(); }
        if(id_view==R.id.Imagebutton3){ imagePath3 = image.getAbsolutePath(); }
        if(id_view==R.id.Imagebutton4){ imagePath4 = image.getAbsolutePath(); }
        if(id_view==R.id.Imagebutton5){ imagePath5 = image.getAbsolutePath(); }
        if(id_view==R.id.Imagebutton6){ imagePath6 = image.getAbsolutePath(); }
        if(id_view==R.id.Imagebutton7){ imagePath7 = image.getAbsolutePath(); }
        if(id_view==R.id.Imagebutton8){ imagePath8 = image.getAbsolutePath(); }
        if(id_view==R.id.Imagebutton9){ imagePath9 = image.getAbsolutePath(); }
        return image;
    }
    private Uri getImageUri(Context context, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    // 사진 링크를 저장하는 함수.
    private void upload(){
        Uri file1 = Uri.fromFile(new File(imagePath1));
        Uri file2 = Uri.fromFile(new File(imagePath2));
        Uri file3 = Uri.fromFile(new File(imagePath3));
        Uri file4 = Uri.fromFile(new File(imagePath4));
        Uri file5 = Uri.fromFile(new File(imagePath5));
        Uri file6 = Uri.fromFile(new File(imagePath6));
        Uri file7 = Uri.fromFile(new File(imagePath7));
        Uri file8 = Uri.fromFile(new File(imagePath8));
        Uri file9 = Uri.fromFile(new File(imagePath9));


        // 사진으로 찍은거면 file을 바꾸기.
        if(pic1==2){ file1 = Uri.fromFile(new File(imagePath1)); }
        if(pic2==2){ file2 = Uri.fromFile(new File(imagePath2)); }
        if(pic3==2){ file3 = Uri.fromFile(new File(imagePath3)); }
        if(pic4==2){ file4 = Uri.fromFile(new File(imagePath4)); }
        if(pic5==2){ file5 = Uri.fromFile(new File(imagePath5)); }
        if(pic6==2){ file6 = Uri.fromFile(new File(imagePath6)); }
        if(pic7==2){ file7 = Uri.fromFile(new File(imagePath7)); }
        if(pic8==2){ file8 = Uri.fromFile(new File(imagePath8)); }
        if(pic9==2){ file9 = Uri.fromFile(new File(imagePath9)); }

        if(!imagePath1.equals(""))
            reviewList.setImageUrl1(file1.getLastPathSegment());
        if(!imagePath2.equals(""))
            reviewList.setImageUrl2(file2.getLastPathSegment());
        if(!imagePath3.equals(""))
            reviewList.setImageUrl3(file3.getLastPathSegment());
        if(!imagePath4.equals(""))
            reviewList.setImageUrl4(file4.getLastPathSegment());
        if(!imagePath5.equals(""))
            reviewList.setImageUrl5(file5.getLastPathSegment());
        if(!imagePath6.equals(""))
            reviewList.setImageUrl6(file6.getLastPathSegment());
        if(!imagePath7.equals(""))
            reviewList.setImageUrl7(file7.getLastPathSegment());
        if(!imagePath8.equals(""))
            reviewList.setImageUrl8(file8.getLastPathSegment());
        if(!imagePath9.equals(""))
            reviewList.setImageUrl9(file9.getLastPathSegment());

        reviewData.saveData(nameAndAdress,reviewList);

        StorageReference riverRef1=storageRef.child("images/"+file1.getLastPathSegment());
        UploadTask uploadTask = riverRef1.putFile(file1);
        uploadTask.addOnFailureListener(e -> System.out.println("첫번째 실패")).
                addOnSuccessListener(taskSnapshot -> System.out.println("제발제발되게해주세요하나님감사합니다아버지사랑합니다"));

        StorageReference riverRef2=storageRef.child("images/"+file2.getLastPathSegment());
        UploadTask uploadTask2 = riverRef2.putFile(file2);
        uploadTask2.addOnFailureListener(e -> System.out.println("두번째 실패")).
                addOnSuccessListener(taskSnapshot -> System.out.println("제발제발되게해주세요하나님감사합니다아버지사랑합니다"));

        StorageReference riverRef3=storageRef.child("images/"+file3.getLastPathSegment());
        UploadTask uploadTask3 = riverRef3.putFile(file3);
        uploadTask3.addOnFailureListener(e -> System.out.println("세번째 실패")).
                addOnSuccessListener(taskSnapshot -> System.out.println("제발제발되게해주세요하나님감사합니다아버지사랑합니다"));

        StorageReference riverRef4=storageRef.child("images/"+file4.getLastPathSegment());
        UploadTask uploadTask4 = riverRef4.putFile(file4);
        uploadTask4.addOnFailureListener(e -> System.out.println("네번째 실패")).
                addOnSuccessListener(taskSnapshot -> System.out.println("제발제발되게해주세요하나님감사합니다아버지사랑합니다"));

        StorageReference riverRef5=storageRef.child("images/"+file5.getLastPathSegment());
        UploadTask uploadTask5 = riverRef5.putFile(file5);
        uploadTask5.addOnFailureListener(e -> System.out.println("다섯번째 실패")).
                addOnSuccessListener(taskSnapshot -> System.out.println("제발제발되게해주세요하나님감사합니다아버지사랑합니다"));

        StorageReference riverRef6=storageRef.child("images/"+file6.getLastPathSegment());
        UploadTask uploadTask6 = riverRef6.putFile(file6);
        uploadTask6.addOnFailureListener(e -> System.out.println("여섯번째 실패")).
                addOnSuccessListener(taskSnapshot -> System.out.println("제발제발되게해주세요하나님감사합니다아버지사랑합니다"));

        StorageReference riverRef7=storageRef.child("images/"+file7.getLastPathSegment());
        UploadTask uploadTask7 = riverRef7.putFile(file7);
        uploadTask7.addOnFailureListener(e -> System.out.println("일곱번째 실패")).
                addOnSuccessListener(taskSnapshot -> System.out.println("제발제발되게해주세요하나님감사합니다아버지사랑합니다"));

        StorageReference riverRef8=storageRef.child("images/"+file8.getLastPathSegment());
        UploadTask uploadTask8 = riverRef8.putFile(file8);
        uploadTask8.addOnFailureListener(e -> System.out.println("여덟번째 실패")).
                addOnSuccessListener(taskSnapshot -> System.out.println("제발제발되게해주세요하나님감사합니다아버지사랑합니다"));

        StorageReference riverRef9=storageRef.child("images/"+file9.getLastPathSegment());
        UploadTask uploadTask9 = riverRef9.putFile(file9);
        uploadTask9.addOnFailureListener(e -> System.out.println("아홉번째 실패")).
                addOnSuccessListener(taskSnapshot -> System.out.println("제발제발되게해주세요하나님감사합니다아버지사랑합니다"));

    }

    //등록하기 버튼 눌렀을때
    public void RegisterButton (View view){

        //리뷰 저장 부분


        Intent reviewIntent = getIntent();

        locationName = reviewIntent.getExtras().getString("NAME");
 //       int index = location_name.indexOf(" , ");
  //      location_name = location_name.substring(0, index);
        String locationCategory = reviewIntent.getExtras().getString("CATEGORY");
        String shortCategory = locationCategory.substring(locationCategory.lastIndexOf(">")+1);
        String locationAddress = reviewIntent.getExtras().getString("ADDRESS");
        String locationNumber = reviewIntent.getExtras().getString("TELEPHONE");
        System.out.println("newmap10 : " + locationName + "  " + locationAddress + "  " + location_x + "  " + location_y);

        //리뷰 문장 받아오기
        String reviewDescription = reviewText.getText().toString();

        if(chk[0].isChecked() == true) tag1 = true; else tag1 = false;
        if(chk[1].isChecked() == true) tag2 = true; else tag2 = false;
        if(chk[2].isChecked() == true) tag3 = true; else tag3 = false;
        if(chk[3].isChecked() == true) tag4 = true; else tag4 = false;
        if(chk[4].isChecked() == true) tag5 = true; else tag5 = false;
        if(chk[5].isChecked() == true) tag6 = true; else tag6 = false;


        reviewList = new ReviewList(locationName, locationAddress, locationNumber, locationCategory, shortCategory, reviewDescription, location_x, location_y, tag1, tag2, tag3, tag4, tag5, tag6, mTime, userName, userNickName, key
                ,imagePath1,imagePath2,imagePath3,imagePath4,imagePath5,imagePath6,imagePath7,imagePath8,imagePath9);

        nameAndAdress = locationName + " , " + locationAddress;

        //파이어베이스에는 특수문자가 들어가면 안되서 바꿔준다.
        if(nameAndAdress.contains("."))
         nameAndAdress = nameAndAdress.replace(".", "");
        if(nameAndAdress.contains("#"))
            nameAndAdress = nameAndAdress.replace("#", "");
        if(nameAndAdress.contains("_"))
            nameAndAdress = nameAndAdress.replace("_", " ");

        if(pic1 !=0 || pic2 !=0 || pic3 !=0 || pic4 !=0 || pic5 !=0 || pic6 !=0 || pic7 !=0 || pic8!=0 || pic9!=0 ) {  // 사진이 하나라도 있으면.
            upload();
        }
        else{
            reviewData.saveData(nameAndAdress, reviewList);  //사진 없을때
        }

        myDialog.setContentView(R.layout.review_popup);
        myDialog.setCancelable(false);

        Button closeButton = (Button) myDialog.findViewById(R.id.ok_button);

        //닫기 버튼을 눌렀을때
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CreateReviewScreenActivity.this,MainScreenActivity.class);
                startActivity(intent);
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
        myDialog.show();
    }

    @Override
    public void onBackPressed() {

        myDialog.setContentView(R.layout.review_stop_popup);
        myDialog.setCancelable(false);

        Button yesButton = (Button) myDialog.findViewById(R.id.yes_button);
        Button noButton = (Button) myDialog.findViewById(R.id.no_button);

        //네 버튼을 눌렀을때
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                myDialog.dismiss();
            }
        });

        //아니요 버튼을 눌렀을때
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
        myDialog.show();
    }
}