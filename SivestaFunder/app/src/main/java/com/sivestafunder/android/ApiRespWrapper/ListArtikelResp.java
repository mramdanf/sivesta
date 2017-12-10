package com.sivestafunder.android.ApiRespWrapper;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.sivestafunder.android.Models.Artikel;

import java.util.List;

/**
 * Created by Ramdan Firdaus on 6/12/2017.
 */

public class ListArtikelResp implements Parcelable {

    @SerializedName("articles")
    private List<Artikel> artikelList;

    public List<Artikel> getArtikelList() {
        return artikelList;
    }

    public void setArtikelList(List<Artikel> artikelList) {
        this.artikelList = artikelList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.artikelList);
    }

    public ListArtikelResp() {
    }

    protected ListArtikelResp(Parcel in) {
        this.artikelList = in.createTypedArrayList(Artikel.CREATOR);
    }

    public static final Creator<ListArtikelResp> CREATOR = new Creator<ListArtikelResp>() {
        @Override
        public ListArtikelResp createFromParcel(Parcel source) {
            return new ListArtikelResp(source);
        }

        @Override
        public ListArtikelResp[] newArray(int size) {
            return new ListArtikelResp[size];
        }
    };
}
