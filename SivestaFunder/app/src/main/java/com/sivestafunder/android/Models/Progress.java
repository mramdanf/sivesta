package com.sivestafunder.android.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sivesta on 4/1/2018.
 */

public class Progress implements Parcelable {
    @SerializedName("keterangan")
    private String textProgress;
    @SerializedName("img_url")
    private String imgUrl;
    @SerializedName("striped_progress_text")
    private String stripedProgressText;
    @SerializedName("posted_at")
    private String postedAt;

    public String getTextProgress() {
        return textProgress;
    }

    public void setTextProgress(String textProgress) {
        this.textProgress = textProgress;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getStripedProgressText() {
        return stripedProgressText;
    }

    public void setStripedProgressText(String stripedProgressText) {
        this.stripedProgressText = stripedProgressText;
    }

    public String getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(String postedAt) {
        this.postedAt = postedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.textProgress);
        dest.writeString(this.imgUrl);
        dest.writeString(this.postedAt);
    }

    public Progress() {
    }

    protected Progress(Parcel in) {
        this.textProgress = in.readString();
        this.imgUrl = in.readString();
        this.postedAt = in.readString();
    }

    public static final Creator<Progress> CREATOR = new Creator<Progress>() {
        @Override
        public Progress createFromParcel(Parcel source) {
            return new Progress(source);
        }

        @Override
        public Progress[] newArray(int size) {
            return new Progress[size];
        }
    };
}
