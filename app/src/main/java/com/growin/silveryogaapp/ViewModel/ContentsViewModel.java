package com.growin.silveryogaapp.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.growin.silveryogaapp.Model.Contents;
import com.growin.silveryogaapp.Repository.Repo;

import java.util.ArrayList;

public class ContentsViewModel extends ViewModel {

    MutableLiveData<ArrayList<Contents>> contentsListLiveData;


    public void init(Context context){
        if(contentsListLiveData!=null){
            return;
        }

        contentsListLiveData = Repo.getInstance(context).getContentsListLiveData();
    }

    public LiveData<ArrayList<Contents>> getContentsListLiveData(){
        return contentsListLiveData;
    }




}
