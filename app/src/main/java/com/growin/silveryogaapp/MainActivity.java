package com.growin.silveryogaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.growin.silveryogaapp.R;
import com.growin.silveryogaapp.Model.Contents;
import com.growin.silveryogaapp.ContentsListViewAdapter;
import com.growin.silveryogaapp.ViewModel.ContentsViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DataLoadListener {


    private RecyclerView pRecyclerView;
    private ContentsListViewAdapter pContentsListViewAdapter;
    private ContentsViewModel pContentsViewModel;
    private ViewModelProvider.AndroidViewModelFactory pViewModalFactory;

    private FirebaseDatabase pDatabase;
    private DatabaseReference pDatabaseRef;

    private FirebaseStorage pStorage;
    private StorageReference pStorageRef;


    private ListView pListView;
    //private ArrayAdapter<String> pArrayAdapter;

    private ArrayList<Contents> pItemList;
    private Uri imgUri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pRecyclerView = findViewById(R.id.rcclContentsListView);
        pRecyclerView.setHasFixedSize(true);
        pRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        if(pViewModalFactory==null){
            pViewModalFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
        }

        pContentsViewModel = new ViewModelProvider(this,pViewModalFactory).get(ContentsViewModel.class);
        pContentsViewModel.init(MainActivity.this);

        pContentsListViewAdapter = new ContentsListViewAdapter(pContentsViewModel.getContentsListLiveData().getValue());

        pRecyclerView.setAdapter(pContentsListViewAdapter);


        //InitState();
        //InitDatabase();

    }

    @Override
    public void onContentsLoaded(){
        pContentsViewModel.getContentsListLiveData().observe(this, new Observer<ArrayList<Contents>>() {
            @Override
            public void onChanged(ArrayList<Contents> contents) {
                pContentsListViewAdapter.notifyDataSetChanged();
            }
        });
    }


    private void InitState() {

        //array adapter 정의
        //pArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<String>());


        //listview와 adapter 연결
        //pListView.setAdapter(pArrayAdapter);
        //pListView.setAdapter(pListViewAdapter);
    }


    /*
    private void InitDatabase(){
        pDatabase = FirebaseDatabase.getInstance();
        pDatabaseRef = pDatabase.getReference("SilverYoga");
        Query dataQuery = pDatabaseRef.child("Contents").orderByChild("group").equalTo("허리");

        pStorage = FirebaseStorage.getInstance("gs://growinyoga-4f680.appspot.com");



        dataQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                pItemList = new ArrayList<Contents>();

                for (DataSnapshot ss : snapshot.getChildren()) {

                    String strPoseName = ss.child("name").getValue().toString();
                    String strImgPath = ss.child("img").getValue().toString();
                    int nIndex =  Integer.parseInt(ss.getKey());

                    pStorageRef = pStorage.getReference(strImgPath);

                    pStorageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Contents pItem = new Contents();
                            //pItem.setnIndex();
                            pItem.setStrName(strPoseName);
                            pItem.setUriImg(uri);
                            pItemList.add(pItem);
                            pContentsListViewAdapter = new ContentsListViewAdapter(pItemList);
                            pListView.setAdapter(pContentsListViewAdapter);
                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //
                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }*/



}