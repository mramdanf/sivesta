package com.sivestafunder.android.Models;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.sivestafunder.android.ApiEndPoint.FunderEndPoint;
import com.sivestafunder.android.Helpers.AppConst;
import com.sivestafunder.android.Helpers.RetrofitHelper;
import com.sivestafunder.android.Helpers.Utility;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

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
    @SerializedName("profile_image_url")
    private String profilePic;
    @SerializedName("id_funders")
    private String idFunder;
    private int planted;
    @SerializedName("harvest_soon")
    private int harvestSoon;
    @SerializedName("participated")
    private int participated;

    /* ============= SETTER GETTRE =================== */
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

    public String getIdFunder() {
        return idFunder;
    }

    public void setIdFunder(String idFunder) {
        this.idFunder = idFunder;
    }

    public int getPlanted() {
        return planted;
    }

    public void setPlanted(int planted) {
        this.planted = planted;
    }

    public int getHarvestSoon() {
        return harvestSoon;
    }

    public void setHarvestSoon(int harvestSoon) {
        this.harvestSoon = harvestSoon;
    }

    public int getParticipated() {
        return participated;
    }

    public void setParticipated(int participated) {
        this.participated = participated;
    }

    /* ====================== PARSCALABLE =========================== */
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
        dest.writeString(this.idFunder);
        dest.writeInt(this.planted);
        dest.writeInt(this.harvestSoon);
        dest.writeInt(this.participated);
    }

    protected Funder(Parcel in) {
        this.name = in.readString();
        this.alamat = in.readString();
        this.phone = in.readString();
        this.email = in.readString();
        this.username = in.readString();
        this.password = in.readString();
        this.profilePic = in.readString();
        this.idFunder = in.readString();
        this.planted = in.readInt();
        this.harvestSoon = in.readInt();
        this.participated = in.readInt();
    }

    public Funder(Context context) {
        this.mContext = context;
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


    /* ===================== Interfacing ============================ */
    private transient Context mContext;

    private transient FunderModelInf mCallback;

    public interface FunderModelInf {
        void funderModelApiCallback(Bundle args);
    }

    /* Fungsi spesifik dari objek */
    public void checkLoginApi(String email, String password, FunderModelInf fInf) {
        mCallback = fInf;
        FunderEndPoint mFunderService = new RetrofitHelper()
                .getFunderService(email, password);
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

                        // Simpan data funder di prf
                        Utility.setFundersPrefs(mContext, funder);

                        mCallback.funderModelApiCallback(args);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        e.printStackTrace();
                        Bundle args = new Bundle();
                        args.putString(AppConst.TAG_MSG, e.getMessage());
                        mCallback.funderModelApiCallback(args);
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    public void logout() {
        Utility.removeFunderPrefs(mContext);
    }

    public void createAccountApi(final Funder funder, FunderModelInf fInf) {
        mCallback = fInf;
        FunderEndPoint mFunderService = new RetrofitHelper()
                .getFunderServiceNoAuth();
        Observable<SimpleResp> submitAccout = mFunderService.submitCreateAccount(
                funder
        );
        submitAccout
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SimpleResp>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SimpleResp simpleResp) {
                        Bundle args = new Bundle();
                        args.putString(AppConst.TAG_MSG, AppConst.TAG_SUCCESS);
                        args.putParcelable(AppConst.OBJ_FUNDER, funder);
                        mCallback.funderModelApiCallback(args);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        Bundle args = new Bundle();
                        args.putString(AppConst.TAG_MSG, e.getMessage());
                        mCallback.funderModelApiCallback(args);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void updateProfileApi(Funder funder, String email, String password, FunderModelInf fInf) {
        mCallback = fInf;
        FunderEndPoint mFunderService = new RetrofitHelper()
                .getFunderService(email, password);

        Observable<SimpleResp> submitUpdate = mFunderService.updateProfileFunder(
                RequestBody.create( okhttp3.MultipartBody.FORM, funder.getName()),
                RequestBody.create( okhttp3.MultipartBody.FORM, funder.getEmail()),
                RequestBody.create( okhttp3.MultipartBody.FORM, funder.getPhone()),
                RequestBody.create( okhttp3.MultipartBody.FORM, funder.getAlamat()),
                RequestBody.create( okhttp3.MultipartBody.FORM, funder.getIdFunder())
        );

        submitUpdate
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SimpleResp>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SimpleResp simpleResp) {
                        Bundle args = new Bundle();
                        args.putString(AppConst.TAG_MSG, AppConst.TAG_SUCCESS);
                        mCallback.funderModelApiCallback(args);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        Bundle args = new Bundle();
                        args.putString(AppConst.TAG_MSG, e.getMessage());
                        mCallback.funderModelApiCallback(args);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
