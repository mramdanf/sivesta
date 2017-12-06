package com.sivestafunder.android.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ramdan Firdaus on 3/12/2017.
 */

public class KomoditasTahunan implements Parcelable {
    private int panjang;
    private int lebar;

    public int getPanjang() {
        return panjang;
    }

    public void setPanjang(int panjang) {
        this.panjang = panjang;
    }

    public int getLebar() {
        return lebar;
    }

    public void setLebar(int lebar) {
        this.lebar = lebar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.panjang);
        dest.writeInt(this.lebar);
    }

    public KomoditasTahunan() {
    }

    protected KomoditasTahunan(Parcel in) {
        this.panjang = in.readInt();
        this.lebar = in.readInt();
    }

    public static final Creator<KomoditasTahunan> CREATOR = new Creator<KomoditasTahunan>() {
        @Override
        public KomoditasTahunan createFromParcel(Parcel source) {
            return new KomoditasTahunan(source);
        }

        @Override
        public KomoditasTahunan[] newArray(int size) {
            return new KomoditasTahunan[size];
        }
    };
}
