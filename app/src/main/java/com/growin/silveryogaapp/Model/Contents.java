package com.growin.silveryogaapp.Model;

import android.net.Uri;

public class Contents {
    private int nIndex;
    private int nCnt;
    private String strGroup;
    private Uri uriImg;
    private String strName;
    private Uri uriVedio;

    public Contents()
    {
        //
    }

    public int getnIndex() {
        return nIndex;
    }

    public int getnCnt() {
        return nCnt;
    }

    public void setnCnt(int nCnt) {
        this.nCnt = nCnt;
    }

    public String getStrGroup() {
        return strGroup;
    }

    public void setStrGroup(String strGroup) {
        this.strGroup = strGroup;
    }

    public Uri getUriImg() {
        return uriImg;
    }

    public void setUriImg(Uri uriImg) {
        this.uriImg = uriImg;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public Uri getUriVedio() {
        return uriVedio;
    }

    public void setnIndex(int nIndex) {
        this.nIndex = nIndex;
    }

    public void setUriVedio(Uri uriVedio) {
        this.uriVedio = uriVedio;
    }

    @Override
    public String toString() {
        return "ListViewItem{" +
                "nIndex=" + nIndex +
                ", nCnt=" + nCnt +
                ", strGroup='" + strGroup + '\'' +
                ", uriImg=" + uriImg +
                ", strName='" + strName + '\'' +
                ", uriVedio=" + uriVedio +
                '}';
    }
}
