package com.sivesta.androidfarmer.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramdan Firdaus on 3/12/2017.
 */

public class KomoditasPerenial implements Parcelable {
    @SerializedName("jumlah_pohon")
    int jmlPohon;

    public int getJmlPohon() {
        return jmlPohon;
    }

    public void setJmlPohon(int jmlPohon) {
        this.jmlPohon = jmlPohon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.jmlPohon);
    }

    public KomoditasPerenial() {
    }

    protected KomoditasPerenial(Parcel in) {
        this.jmlPohon = in.readInt();
    }

    public static final Creator<KomoditasPerenial> CREATOR = new Creator<KomoditasPerenial>() {
        @Override
        public KomoditasPerenial createFromParcel(Parcel source) {
            return new KomoditasPerenial(source);
        }

        @Override
        public KomoditasPerenial[] newArray(int size) {
            return new KomoditasPerenial[size];
        }
    };
}
