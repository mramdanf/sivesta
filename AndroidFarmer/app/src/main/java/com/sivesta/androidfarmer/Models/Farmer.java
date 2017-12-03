package com.sivesta.androidfarmer.Models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Farmer implements Parcelable {
    @SerializedName("id_petani")
    private String idPetani;
    private String nama;
    private String kontak;
    private String username;
    private String password;
    private String alamat;

    public String getIdPetani() {
        return idPetani;
    }

    public void setIdPetani(String idPetani) {
        this.idPetani = idPetani;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKontak() {
        return kontak;
    }

    public void setKontak(String kontak) {
        this.kontak = kontak;
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

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idPetani);
        dest.writeString(this.nama);
        dest.writeString(this.kontak);
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeString(this.alamat);
    }

    public Farmer() {
    }

    protected Farmer(Parcel in) {
        this.idPetani = in.readString();
        this.nama = in.readString();
        this.kontak = in.readString();
        this.username = in.readString();
        this.password = in.readString();
        this.alamat = in.readString();
    }

    public static final Creator<Farmer> CREATOR = new Creator<Farmer>() {
        @Override
        public Farmer createFromParcel(Parcel source) {
            return new Farmer(source);
        }

        @Override
        public Farmer[] newArray(int size) {
            return new Farmer[size];
        }
    };
}
