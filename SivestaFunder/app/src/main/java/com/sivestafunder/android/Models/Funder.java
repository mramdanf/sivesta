package com.sivestafunder.android.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ramdan Firdaus on 6/12/2017.
 */

public class Funder implements Parcelable {
    private String name;
    private String alamat;
    private String phone;
    private String email;
    private String username;
    private String password;

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
    }

    public Funder() {
    }

    protected Funder(Parcel in) {
        this.name = in.readString();
        this.alamat = in.readString();
        this.phone = in.readString();
        this.email = in.readString();
        this.username = in.readString();
        this.password = in.readString();
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
}
