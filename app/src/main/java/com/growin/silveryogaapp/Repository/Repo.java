package com.growin.silveryogaapp.Repository;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.growin.silveryogaapp.DataLoadListener;
import com.growin.silveryogaapp.Model.Contents;

import java.util.ArrayList;

public class Repo {

    static Repo instance;
    private ArrayList<Contents> contentsArrayList = new ArrayList<>();
    private Uri uriImg;

    static Context mContext;
    static DataLoadListener dataLoadListener;
    public static Repo getInstance(Context context){

        mContext = context;
        if(instance ==null){
            instance = new Repo();
        }
        dataLoadListener = (DataLoadListener) mContext;
        return instance;
    }

    public MutableLiveData<ArrayList<Contents>> getContentsListLiveData(){
        loadContentsArrayList();
        MutableLiveData<ArrayList<Contents>> contents = new MutableLiveData<>();
        contents.setValue(contentsArrayList);
        return contents;
    }

    private void loadContentsArrayList() {
        FirebaseDatabase pDatabase = FirebaseDatabase.getInstance();
        DatabaseReference pDatabaseRef = pDatabase.getReference("SilverYoga");
        Query query = pDatabaseRef.child("Contents").orderByChild("group").equalTo("어깨");

        FirebaseStorage pStorage = FirebaseStorage.getInstance("gs://growinyoga-4f680.appspot.com");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ss : snapshot.getChildren()){

                    Contents pItem = new Contents();
                    int nIndex =  Integer.parseInt(ss.getKey());  //Key
                    int nCnt = Integer.parseInt(ss.child("count").getValue().toString()); //Count
                    String strGroup = ss.child("group").getValue().toString(); // ex) 허리, 어깨, 팔, 다리
                    String strName = ss.child("name").getValue().toString(); // 동작이름
                    Uri uriVedio = Uri.parse(ss.child("vedio").getValue().toString()); //요가영상 url
                    String strImgPath = ss.child("img").getValue().toString();

                    //==============================================================================
                    //   Storage에서 이미지 가져오기
                    //==============================================================================
                    StorageReference pStorageRef = pStorage.getReference(strImgPath);

                    pStorageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            pItem.setUriImg(uri);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                    //==============================================================================
                    pItem.setnIndex(nIndex);
                    pItem.setnCnt(nCnt);
                    pItem.setStrGroup(strGroup);

                    pItem.setStrName(strName);
                    pItem.setUriVedio(uriVedio);
                    contentsArrayList.add(pItem);
                }

                dataLoadListener.onContentsLoaded();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }


}
