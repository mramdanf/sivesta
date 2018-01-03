package com.sivestafunder.android.ApiRespWrapper;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.sivestafunder.android.Models.Kontrak;

import java.util.List;

/**
 * Created by Sivesta on 1/1/2018.
 */

public class ListNewSeeds implements Parcelable {

    private boolean status;
    @SerializedName("list_kontrak")
    private List<Kontrak> kontrakList;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Kontrak> getKontrakList() {
        return kontrakList;
    }

    public void setKontrakList(List<Kontrak> kontrakList) {
        this.kontrakList = kontrakList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.status ? (byte) 1 : (byte) 0);
        dest.writeTypedList(this.kontrakList);
    }

    public ListNewSeeds() {
    }

    protected ListNewSeeds(Parcel in) {
        this.status = in.readByte() != 0;
        this.kontrakList = in.createTypedArrayList(Kontrak.CREATOR);
    }

    public static final Creator<ListNewSeeds> CREATOR = new Creator<ListNewSeeds>() {
        @Override
        public ListNewSeeds createFromParcel(Parcel source) {
            return new ListNewSeeds(source);
        }

        @Override
        public ListNewSeeds[] newArray(int size) {
            return new ListNewSeeds[size];
        }
    };
}
