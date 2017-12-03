package com.sivesta.androidfarmer.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ramdan Firdaus on 3/12/2017.
 */

public class Komoditas implements Parcelable {
    private KomoditasPerenial parenial;
    private String nama;
    private int harga;
    private int stok;
    private String lokasi;
    private int komoditasType;
    private double latitude;
    private double longitude;

    public KomoditasPerenial getParenial() {
        return parenial;
    }

    public void setParenial(KomoditasPerenial parenial) {
        this.parenial = parenial;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.parenial, flags);
        dest.writeString(this.nama);
        dest.writeInt(this.harga);
        dest.writeInt(this.stok);
        dest.writeString(this.lokasi);
        dest.writeInt(this.komoditasType);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
    }

    public Komoditas() {
    }

    protected Komoditas(Parcel in) {
        this.parenial = in.readParcelable(KomoditasPerenial.class.getClassLoader());
        this.nama = in.readString();
        this.harga = in.readInt();
        this.stok = in.readInt();
        this.lokasi = in.readString();
        this.komoditasType = in.readInt();
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
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
}
