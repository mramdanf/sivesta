package com.sivestafunder.android.Models;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.sivestafunder.android.ApiEndPoint.KontrakEndPoint;
import com.sivestafunder.android.ApiRespWrapper.ListNewSeeds;
import com.sivestafunder.android.Helpers.AppConst;
import com.sivestafunder.android.Helpers.RetrofitHelper;

import java.sql.Timestamp;
import java.util.Date;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sivesta on 22/12/2017.
 */

public class Kontrak implements Parcelable {
    private Komoditas komoditas;
    private Funder funder;
    @SerializedName("wkt_mulai")
    private Timestamp waktuMulai;
    @SerializedName("status_kontrak")
    private int statusKontrak;
    @SerializedName("tgl_kadaluarsa")
    private Date tglKadaluarsa;
    @SerializedName("tgl_mulai_kontrak")
    private Date tglMulaiKontrak;
    @SerializedName("biaya_total")
    private int biayaTotal;
    @SerializedName("virtual_account")
    private String virtualAccount;
    private String msg;

    /* ============================ SETTER GETTER ========================*/
    public Komoditas getKomoditas() {
        return komoditas;
    }

    public void setKomoditas(Komoditas komoditas) {
        this.komoditas = komoditas;
    }

    public Funder getFunder() {
        return funder;
    }

    public void setFunder(Funder funder) {
        this.funder = funder;
    }

    public Timestamp getWaktuMulai() {
        return waktuMulai;
    }

    public void setWaktuMulai(Timestamp waktuMulai) {
        this.waktuMulai = waktuMulai;
    }

    public int getStatusKontrak() {
        return statusKontrak;
    }

    public void setStatusKontrak(int statusKontrak) {
        this.statusKontrak = statusKontrak;
    }

    public Date getTglKadaluarsa() {
        return tglKadaluarsa;
    }

    public void setTglKadaluarsa(Date tglKadaluarsa) {
        this.tglKadaluarsa = tglKadaluarsa;
    }

    public Date getTglMulaiKontrak() {
        return tglMulaiKontrak;
    }

    public void setTglMulaiKontrak(Date tglMulaiKontrak) {
        this.tglMulaiKontrak = tglMulaiKontrak;
    }

    public int getBiayaTotal() {
        return biayaTotal;
    }

    public void setBiayaTotal(int biayaTotal) {
        this.biayaTotal = biayaTotal;
    }

    public String getVirtualAccount() {
        return virtualAccount;
    }

    public void setVirtualAccount(String virtualAccount) {
        this.virtualAccount = virtualAccount;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /* ============================ PARSCALABLE ========================*/
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.komoditas, flags);
        dest.writeParcelable(this.funder, flags);
        dest.writeSerializable(this.waktuMulai);
        dest.writeInt(this.statusKontrak);
        dest.writeLong(this.tglKadaluarsa != null ? this.tglKadaluarsa.getTime() : -1);
        dest.writeLong(this.tglMulaiKontrak != null ? this.tglMulaiKontrak.getTime() : -1);
        dest.writeInt(this.biayaTotal);
        dest.writeString(this.virtualAccount);
        dest.writeString(this.msg);
    }

    public Kontrak() {
    }

    protected Kontrak(Parcel in) {
        this.komoditas = in.readParcelable(Komoditas.class.getClassLoader());
        this.funder = in.readParcelable(Funder.class.getClassLoader());
        this.waktuMulai = (Timestamp) in.readSerializable();
        this.statusKontrak = in.readInt();
        long tmpTglKadaluarsa = in.readLong();
        this.tglKadaluarsa = tmpTglKadaluarsa == -1 ? null : new Date(tmpTglKadaluarsa);
        long tmpTglMulaiKontrak = in.readLong();
        this.tglMulaiKontrak = tmpTglMulaiKontrak == -1 ? null : new Date(tmpTglMulaiKontrak);
        this.biayaTotal = in.readInt();
        this.virtualAccount = in.readString();
        this.msg = in.readString();
    }

    public static final Creator<Kontrak> CREATOR = new Creator<Kontrak>() {
        @Override
        public Kontrak createFromParcel(Parcel source) {
            return new Kontrak(source);
        }

        @Override
        public Kontrak[] newArray(int size) {
            return new Kontrak[size];
        }
    };

    /* ============================ INTERFACING ========================*/

    private transient KontrakModelInf mCallback;

    public interface KontrakModelInf {
        void kontrakModelInfCallback(Bundle args);
    }

    public void createKontrakApi(Kontrak kontrak, String email, String password, KontrakModelInf ki) {
        mCallback = ki;
        KontrakEndPoint kontrakService = new RetrofitHelper()
                .getKontrakService(email, password);
        Observable<Kontrak> createKontrak = kontrakService.createKontrakService(kontrak);
        createKontrak
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Kontrak>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Kontrak resKontrak) {
                        Bundle args = new Bundle();
                        args.putParcelable(AppConst.OBJ_KONTRAK, resKontrak);
                        args.putString(AppConst.TAG_MSG, AppConst.TAG_SUCCESS);
                        mCallback.kontrakModelInfCallback(args);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        Bundle args = new Bundle();
                        args.putString(AppConst.TAG_MSG, e.getMessage());
                        mCallback.kontrakModelInfCallback(args);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getKontrakNewSeeds(String idFunder, String email, String password, KontrakModelInf ki) {
        Log.d(this.getClass().getSimpleName(), "id funder: " + idFunder);
        mCallback = ki;
        KontrakEndPoint kontrakService = new RetrofitHelper()
                .getKontrakService(email, password);
        Observable<ListNewSeeds> getListNewSeeds = kontrakService.getKontrakNewSeedsService(idFunder);
        getListNewSeeds
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListNewSeeds>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ListNewSeeds listNewSeeds) {
                        Bundle args = new Bundle();
                        args.putString(AppConst.TAG_MSG, AppConst.TAG_SUCCESS);
                        args.putParcelable(AppConst.LIST_OBJ_KONTRAK, listNewSeeds);
                        mCallback.kontrakModelInfCallback(args);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        Bundle args = new Bundle();
                        args.putString(AppConst.TAG_MSG, e.getMessage());
                        mCallback.kontrakModelInfCallback(args);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
