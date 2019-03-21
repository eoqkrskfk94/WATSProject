package com.example.mjkim.watsproject.FirstSreenFragments;


import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mjkim.watsproject.ChangeInfoScreenActivity;
import com.example.mjkim.watsproject.CreateReviewScreenActivity;
import com.example.mjkim.watsproject.LoginScreenActivity;
import com.example.mjkim.watsproject.MainScreenActivity;
import com.example.mjkim.watsproject.MyReviewScreenActivity;
import com.example.mjkim.watsproject.R;
import com.example.mjkim.watsproject.Review.ReviewList;
import com.example.mjkim.watsproject.User.UserInformation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class MypageFragment extends Fragment {
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    public final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    public final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 2;
    Dialog myDialog;
    ImageView profile;
    private FirebaseStorage storage;
    private static final int pick_from_album = 1; // 갤러리 불러올때 요청 상수.
    public String imagePath1="";
    StorageReference storageRef;
    public UserInformation userInformation=new UserInformation();

    public MypageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        auth= FirebaseAuth.getInstance();
        final String userEmail = new String(auth.getCurrentUser().getEmail()); //Useremail이 현재 사용자 이메일이다.
        mDatabase = database.getReference();
        final DatabaseReference myreview = database.getReference();
        storage= FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        View v = inflater.inflate(R.layout.fragment_mypage, container,false);

        Button logout=(Button) v.findViewById(R.id.log_out_button);
        Button informationButton=(Button)v.findViewById(R.id.see_information_button);
        Button myreviewButton=(Button)v.findViewById(R.id.see_review_button);
        Button changeButton=(Button)v.findViewById(R.id.change_info_button);
        profile=(ImageView)v.findViewById(R.id.profile_imageview);


        // 갤러리 사용 권한 체크
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_CONTACTS)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

//        //프로필을 클릭했을때, 우선 막아놓음 지우지마셈 준영이가 구현할 예정
//        profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//
//                myDialog = new Dialog(getActivity()); //팝업 변수 선언
//                myDialog.setContentView(R.layout.profile_popup);
//                myDialog.setCancelable(false);
//
//                Button galleryButton = (Button) myDialog.findViewById(R.id.gallery_button);
//                Button cancelButton = (Button) myDialog.findViewById(R.id.cancel_button);
//                //갤러리 버튼을 눌렀을때
//                galleryButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getActivity(),"준비중입니다.",Toast.LENGTH_LONG).show();
//                        // doTakeAlbumAction();
//                        myDialog.dismiss();//앨범에서 사진 가져오기 메소드
//                    }
//                });
//
//
//                //닫기 버튼을 눌렀을때
//                cancelButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        // 취소버튼을 누르면 사진삭제
//                        myDialog.dismiss();
//                    }
//                });
//                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
//                myDialog.show(); //팝업창.
//
//            }
//
//        });

        final TextView textId=(TextView)v.findViewById(R.id.textView_id);

        //닉네임을 보여줌.
        mDatabase.child("user lists").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                // 데이타베이스에 저장되어있는 이름값(ex: 남준영)의 userEmail값이 사용자와 같을때 아래구문 실행.
                mDatabase.child("user lists").child(dataSnapshot.getKey()).orderByChild("userEmail").equalTo(userEmail).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        UserInformation userInformation = dataSnapshot.getValue(UserInformation.class);
                        textId.setText(userInformation.getUserNickname()); // 그 유저에 해당하는 닉네임값을 보여줌.
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //로그아웃 눌렀을때
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                auth.signOut();
                Intent intent =new Intent(getActivity(), LoginScreenActivity.class);
                startActivity(intent);
            }
        });

        //회원정보 수정 버튼 눌럿을때
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 사용자 정보 이용해서 비밀번호 통과시키기
                auth = FirebaseAuth.getInstance();
                auth.setLanguageCode("ko");
                EditText checkPasswordEditText = new EditText(getContext());


                String userEmail = auth.getCurrentUser().getEmail();

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("회원정보 수정");
                builder.setMessage("비밀번호를 입력하세요.");
                // 이거 안하면 튕김. 뷰가 재생성되기전에 지워지도록.
                if (checkPasswordEditText.getParent() != null)
                    ((ViewGroup) checkPasswordEditText.getParent()).removeView(checkPasswordEditText);

                checkPasswordEditText.setSingleLine();
                FrameLayout container = new FrameLayout(getContext());
                FrameLayout.LayoutParams params = new  FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.leftMargin = getResources().getDimensionPixelSize(R.dimen.dialog_margin);
                params.rightMargin = getResources().getDimensionPixelSize(R.dimen.dialog_margin);
                checkPasswordEditText.setLayoutParams(params);
                // 비밀번호처럼 * 로 나오게 하기
                checkPasswordEditText.setInputType( InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
                checkPasswordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                container.addView(checkPasswordEditText);
                builder.setView(container);

                builder.setCancelable(false);
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String password = checkPasswordEditText.getText().toString();
                        // 널 방지
                        if(password.isEmpty()) {
                            password = ".";
                        }
                        auth.signInWithEmailAndPassword(userEmail, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(getActivity(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Intent intent=new Intent(getActivity(),ChangeInfoScreenActivity.class);
                                    startActivity(intent);
                                }
                            }
                        });
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

        //내가 작성한 후기 버튼 눌렀을때
        myreviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("프래그맨트 : " + getFragmentManager().getFragments().toString());

                Intent intent=new Intent(getActivity(),MyReviewScreenActivity.class);
                startActivity(intent);
            }
        });


        // 개발 정보를 눌렀을때
        informationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("1.01버전");
                builder.setMessage("개발자 : 김명진 남준영 정희석\n디자인 : 김진이 신영지 이현진 한준모 ");
                builder.setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();
            }
        });

        return v;


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
        CursorLoader cursorLoader=new CursorLoader(getActivity(),uri,proj,null,null,null);
        Cursor cursor=cursorLoader.loadInBackground();
        int index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(index);
    }

    //카메라 권한받는 메소드
    @Override
    public void onRequestPermissionsResult(int requestCode,@NonNull String[] permissions,@NonNull int[] grantResults){
        if(requestCode==0){
            if(grantResults[0]==0){ Toast.makeText(getActivity(),"카메라 권한 승인", Toast.LENGTH_SHORT).show(); }
            else{ Toast.makeText(getActivity(),"권한 승인이 거절되었습니다. 카메라를 이용하려면 권한을 승인해야 합니다.",Toast.LENGTH_SHORT).show(); }
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
                    Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();
                }

            }

            return;
        }

        // other 'case' lines to check for other
        // permissions this app might request
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
        System.out.println("들어왔어??");
            if (requestCode == pick_from_album) {
                    if (data != null) {
                        imagePath1 = getPath(data.getData());
                        Uri file = Uri.fromFile(new File(imagePath1));
                        StorageReference riverRef=storageRef.child("images/"+file.getLastPathSegment());
                        UploadTask uploadTask = riverRef.putFile(file);
                        final Uri finalFile = file;
                        System.out.println("들어왔어");

                        uploadTask.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                System.out.println("실패1");
                            }
                        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                System.out.println("업로드 성공");
                                userInformation.setProfileUri(finalFile.getLastPathSegment());
                                Intent intent1 =new Intent(getActivity(), MainScreenActivity.class);
                                startActivity(intent1);
//                                reviewList.setImageUrl1(finalFile.getLastPathSegment());
//                                reviewData.saveData(nameAndAdress, reviewList);
                            }
                        });

                      }
             }
        }
    }
}
