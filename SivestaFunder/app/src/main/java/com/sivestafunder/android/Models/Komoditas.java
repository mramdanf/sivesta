package com.sivestafunder.android.Models;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.sivestafunder.android.ApiEndPoint.KomoditasEndPoint;
import com.sivestafunder.android.ApiRespWrapper.ListKomoditasResp;
import com.sivestafunder.android.Helpers.AppConst;
import com.sivestafunder.android.Helpers.RetrofitHelper;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ramdan Firdaus on 3/12/2017.
 */

public class Komoditas implements Parcelable {
    private KomoditasPerenial parenial;
    private KomoditasTahunan tahunan;

    public KomoditasPerenial getParenial() {
        return parenial;
    }

    public void setParenial(KomoditasPerenial parenial) {
        this.parenial = parenial;
    }

    public KomoditasTahunan getTahunan() {
        return tahunan;
    }

    public void setTahunan(KomoditasTahunan tahunan) {
        this.tahunan = tahunan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public int getKomoditasType() {
        return komoditasType;
    }

    public void setKomoditasType(int komoditasType) {
        this.komoditasType = komoditasType;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getIdKomoditas() {
        return idKomoditas;
    }

    public void setIdKomoditas(String idKomoditas) {
        this.idKomoditas = idKomoditas;
    }

    public String getHargaText() {
        return hargaText;
    }

    public void setHargaText(String hargaText) {
        this.hargaText = hargaText;
    }

    public int getMinKontrak() {
        return minKontrak;
    }

    public void setMinKontrak(int minKontrak) {
        this.minKontrak = minKontrak;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public String getPlanted() {
        return planted;
    }

    public void setPlanted(String planted) {
        this.planted = planted;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    private String nama;
    private int harga;
    @SerializedName("stock")
    private int stok;
    private String lokasi;
    @SerializedName("kom_type")
    private int komoditasType;
    private double latitude;
    private double longitude;
    @SerializedName("id_komoditas")
    private String idKomoditas;
    @SerializedName("img_url")
    private String imgUrl;
    @SerializedName("format_rupiah")
    private String hargaText;
    @SerializedName("min_kontrak")
    private int minKontrak;
    private float profit;
    private String planted;
    @SerializedName("deskripsi")
    private String deskripsi;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.parenial, flags);
        dest.writeParcelable(this.tahunan, flags);
        dest.writeString(this.nama);
        dest.writeInt(this.harga);
        dest.writeInt(this.stok);
        dest.writeString(this.lokasi);
        dest.writeInt(this.komoditasType);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
        dest.writeString(this.idKomoditas);
        dest.writeString(this.imgUrl);
        dest.writeString(this.hargaText);
        dest.writeFloat(this.profit);
        dest.writeInt(this.minKontrak);
        dest.writeString(this.planted);
        dest.writeString(this.deskripsi);
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    protected Komoditas(Parcel in) {
        this.parenial = in.readParcelable(KomoditasPerenial.class.getClassLoader());
        this.tahunan = in.readParcelable(KomoditasTahunan.class.getClassLoader());
        this.nama = in.readString();
        this.harga = in.readInt();
        this.stok = in.readInt();
        this.lokasi = in.readString();
        this.komoditasType = in.readInt();
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
        this.idKomoditas = in.readString();
        this.imgUrl = in.readString();
        this.hargaText = in.readString();
        this.profit = in.readFloat();
        this.minKontrak = in.readInt();
        this.planted = in.readString();
        this.deskripsi = in.readString();
    }

    public static final Creator<Komoditas> CREATOR = new Creator<Komoditas>() {
        @Override
        public Komoditas createFromParcel(Parcel source) {
            return new Komoditas(source);
        }

        @Override
        public Komoditas[] newArray(int size) {
            return new Komoditas[size];
        }
    };

    /* Interfacing */
    private transient KomoditasModelInf mCallback;

    public interface KomoditasModelInf {
        void getPopularKomoditasCallback(Bundle args);
    }

    public Komoditas() {
    }

    /* Fungsi spesifik dari objek */
    public void getPopularKomoditasApi(KomoditasModelInf komInf) {
        mCallback = komInf;
        KomoditasEndPoint mKomoditasService = new RetrofitHelper()
                .getKomoditasServiceNoAuth();
        Observable<ListKomoditasResp> listKomoditas = mKomoditasService
                .getPopularKomoditasService();
        listKomoditas
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListKomoditasResp>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ListKomoditasResp listKomoditasResp) {
                        Bundle args = new Bundle();
                        args.putParcelable(AppConst.LIST_OBJ_KOMODITAS, listKomoditasResp);
                        args.putString(AppConst.TAG_MSG, AppConst.TAG_SUCCESS);
                        mCallback.getPopularKomoditasCallback(args);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        Bundle args = new Bundle();
                        args.putString(AppConst.TAG_MSG, e.getMessage());
                        mCallback.getPopularKomoditasCallback(args);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getAllKomoditasApi(KomoditasModelInf komInf) {
        mCallback = komInf;
        KomoditasEndPoint mKomoditasService = new RetrofitHelper()
                .getKomoditasServiceNoAuth();
        Observable<ListKomoditasResp> listKomoditas = mKomoditasService
                .getAllKomoditasService();
        listKomoditas
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListKomoditasResp>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ListKomoditasResp listKomoditasResp) {
                        Bundle args = new Bundle();
                        args.putParcelable(AppConst.LIST_OBJ_KOMODITAS, listKomoditasResp);
                        args.putString(AppConst.TAG_MSG, AppConst.TAG_SUCCESS);
                        mCallback.getPopularKomoditasCallback(args);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        Bundle args = new Bundle();
                        args.putString(AppConst.TAG_MSG, e.getMessage());
                        mCallback.getPopularKomoditasCallback(args);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
