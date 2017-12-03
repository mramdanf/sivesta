package com.sivesta.androidfarmer.ApiRespWrapper;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.sivesta.androidfarmer.Models.Komoditas;

import java.util.List;

/**
 * Created by Ramdan Firdaus on 3/12/2017.
 */

public class ListKomoditasResp implements Parcelable {
    @SerializedName("komoditas")
    private List<Komoditas> komoditasList;

    public List<Komoditas> getKomoditasList() {
        return komoditasList;
    }

    public void setKomoditasList(List<Komoditas> komoditasList) {
        this.komoditasList = komoditasList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.komoditasList);
    }

    public ListKomoditasResp() {
    }

    protected ListKomoditasResp(Parcel in) {
        this.komoditasList = in.createTypedArrayList(Komoditas.CREATOR);
    }

    public static final Creator<ListKomoditasResp> CREATOR = new Creator<ListKomoditasResp>() {
        @Override
        public ListKomoditasResp createFromParcel(Parcel source) {
            return new ListKomoditasResp(source);
        }

        @Override
        public ListKomoditasResp[] newArray(int size) {
            return new ListKomoditasResp[size];
        }
    };
}
