package com.sivestafunder.android.ApiRespWrapper;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.sivestafunder.android.Models.Progress;

import java.util.List;

/**
 * Created by Sivesta on 4/1/2018.
 */

public class ListProgressResp implements Parcelable {
    private boolean status;
    private String msg;

    @SerializedName("list_progress")
    private List<Progress> progressList;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Progress> getProgressList() {
        return progressList;
    }

    public void setProgressList(List<Progress> progressList) {
        this.progressList = progressList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.status ? (byte) 1 : (byte) 0);
        dest.writeString(this.msg);
        dest.writeTypedList(this.progressList);
    }

    public ListProgressResp() {
    }

    protected ListProgressResp(Parcel in) {
        this.status = in.readByte() != 0;
        this.msg = in.readString();
        this.progressList = in.createTypedArrayList(Progress.CREATOR);
    }

    public static final Creator<ListProgressResp> CREATOR = new Creator<ListProgressResp>() {
        @Override
        public ListProgressResp createFromParcel(Parcel source) {
            return new ListProgressResp(source);
        }

        @Override
        public ListProgressResp[] newArray(int size) {
            return new ListProgressResp[size];
        }
    };
}
