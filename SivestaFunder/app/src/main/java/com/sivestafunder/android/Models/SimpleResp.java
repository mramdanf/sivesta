package com.sivestafunder.android.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sivesta on 20/12/2017.
 */

public class SimpleResp implements Parcelable {
    String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.msg);
    }

    public SimpleResp() {
    }

    protected SimpleResp(Parcel in) {
        this.msg = in.readString();
    }

    public static final Creator<SimpleResp> CREATOR = new Creator<SimpleResp>() {
        @Override
        public SimpleResp createFromParcel(Parcel source) {
            return new SimpleResp(source);
        }

        @Override
        public SimpleResp[] newArray(int size) {
            return new SimpleResp[size];
        }
    };
}
