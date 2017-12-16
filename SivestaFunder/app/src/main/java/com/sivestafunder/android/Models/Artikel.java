package com.sivestafunder.android.Models;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.sivestafunder.android.ApiEndPoint.ArtikelEndPoint;
import com.sivestafunder.android.ApiRespWrapper.ListArtikelResp;
import com.sivestafunder.android.Helpers.AppConst;
import com.sivestafunder.android.Helpers.RetrofitHelper;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ramdan Firdaus on 6/12/2017.
 */

public class Artikel implements Parcelable {
    @SerializedName("id_artikel")
    private String idArtikel;
    private String judul;
    private String konten;
    private String penulis;
    @SerializedName("tgl_posting_text")
    private String tglPosting;
    @SerializedName("image")
    private String imgArtike;

    public String getIdArtikel() {
        return idArtikel;
    }

    public void setIdArtikel(String idArtikel) {
        this.idArtikel = idArtikel;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getTglPosting() {
        return tglPosting;
    }

    public void setTglPosting(String tglPosting) {
        this.tglPosting = tglPosting;
    }

    public String getImgArtike() {
        return imgArtike;
    }

    public void setImgArtike(String imgArtike) {
        this.imgArtike = imgArtike;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idArtikel);
        dest.writeString(this.judul);
        dest.writeString(this.konten);
        dest.writeString(this.penulis);
        dest.writeString(this.tglPosting);
        dest.writeString(this.imgArtike);
    }

    public Artikel() {
    }

    protected Artikel(Parcel in) {
        this.idArtikel = in.readString();
        this.judul = in.readString();
        this.konten = in.readString();
        this.penulis = in.readString();
        this.tglPosting = in.readString();
        this.imgArtike = in.readString();
    }

    public static final Creator<Artikel> CREATOR = new Creator<Artikel>() {
        @Override
        public Artikel createFromParcel(Parcel source) {
            return new Artikel(source);
        }

        @Override
        public Artikel[] newArray(int size) {
            return new Artikel[size];
        }
    };

    private ArtikelModelInterface mCallback;

    public interface ArtikelModelInterface {
        void getArtikelCallback(Bundle args);
    }

    /* rmd */
    public void getArtikelFromApi(ArtikelModelInterface ainf) {
        mCallback = ainf;
        ArtikelEndPoint artikelService = new RetrofitHelper()
                .getArtikelService();
        Observable<ListArtikelResp> listArtikel = artikelService
                .getArtikelService();
        listArtikel
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListArtikelResp>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ListArtikelResp listArtikelResp) {

                        Bundle args = new Bundle();
                        args.putParcelable(AppConst.LIST_OBJ_ARTIKEL, listArtikelResp);
                        args.putString(AppConst.TAG_MSG, AppConst.TAG_SUCCESS);
                        mCallback.getArtikelCallback(args);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        Bundle args = new Bundle();
                        args.putString(AppConst.TAG_MSG, e.getMessage());
                        mCallback.getArtikelCallback(args);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
