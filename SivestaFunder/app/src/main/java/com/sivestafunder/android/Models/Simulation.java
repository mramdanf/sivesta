package com.sivestafunder.android.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sivesta on 4/1/2018.
 */

public class Simulation implements Parcelable {
    private int year;
    private float profit;
    private String hargaKomoditas;
    @SerializedName("net_profit")
    private String netProfit;


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public String getHargaKomoditas() {
        return hargaKomoditas;
    }

    public void setHargaKomoditas(String hargaKomoditas) {
        this.hargaKomoditas = hargaKomoditas;
    }

    public String getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(String netProfit) {
        this.netProfit = netProfit;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.year);
        dest.writeFloat(this.profit);
        dest.writeString(this.hargaKomoditas);
        dest.writeString(this.netProfit);
    }

    public Simulation() {
    }

    protected Simulation(Parcel in) {
        this.year = in.readInt();
        this.profit = in.readFloat();
        this.hargaKomoditas = in.readString();
        this.netProfit = in.readString();
    }

    public static final Creator<Simulation> CREATOR = new Creator<Simulation>() {
        @Override
        public Simulation createFromParcel(Parcel source) {
            return new Simulation(source);
        }

        @Override
        public Simulation[] newArray(int size) {
            return new Simulation[size];
        }
    };
}
