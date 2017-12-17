package com.sivestafunder.android.Models;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.sivestafunder.android.Activity.LoginActivity;
import com.sivestafunder.android.Activity.MainActivity;
import com.sivestafunder.android.ApiEndPoint.FunderEndPoint;
import com.sivestafunder.android.Helpers.AppConst;
import com.sivestafunder.android.Helpers.RetrofitHelper;
import com.sivestafunder.android.Helpers.Utility;
import com.sivestafunder.android.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ramdan Firdaus on 6/12/2017.
 */

public class Funder implements Parcelable {
    @SerializedName("nama")
    private String name;
    private String alamat;
    @SerializedName("telepon")
    private String phone;
    private String email;
    private String username;
    private String password;
    private Context mContext;
    @SerializedName("profile_image_url")
    private String profilePic;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.alamat);
        dest.writeString(this.phone);
        dest.writeString(this.email);
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeString(this.profilePic);
    }


    protected Funder(Parcel in) {
        this.name = in.readString();
        this.alamat = in.readString();
        this.phone = in.readString();
        this.email = in.readString();
        this.username = in.readString();
        this.password = in.readString();
        this.profilePic = in.readString();
    }

    public static final Creator<Funder> CREATOR = new Creator<Funder>() {
        @Override
        public Funder createFromParcel(Parcel source) {
            return new Funder(source);
        }

        @Override
        public Funder[] newArray(int size) {
            return new Funder[size];
        }
    };

    /* Interfacing */
    private FunderModelInf mCallback;

    public Funder() {
    }

    public interface FunderModelInf {
        void checkLoginApiCallback(Bundle args);
    }

    /* Fungsi spesifik dari objek */
    public void checkLoginApi(String username, String password, FunderModelInf fInf) {
        mCallback = fInf;
        FunderEndPoint mFunderService = new RetrofitHelper()
                .getFunderService(username, password);
        Observable<Funder> loginFarmer = mFunderService.checkLogin();
        loginFarmer
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Funder>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull Funder funder) {
                        Bundle args = new Bundle();
                        args.putParcelable(AppConst.OBJ_FUNDER, funder);
                        args.putString(AppConst.TAG_MSG, AppConst.TAG_SUCCESS);
                        mCallback.checkLoginApiCallback(args);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        e.printStackTrace();
                        Bundle args = new Bundle();
                        args.putString(AppConst.TAG_MSG, e.getMessage());
                        mCallback.checkLoginApiCallback(args);
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
}
