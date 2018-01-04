package com.sivestafunder.android.ApiRespWrapper;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.sivestafunder.android.Models.Simulation;

import java.util.List;

/**
 * Created by Sivesta on 4/1/2018.
 */

public class ListSimulationResp implements Parcelable {
    private String msg;
    private boolean status;
    @SerializedName("list_simulation")
    private List<Simulation> simulationList;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Simulation> getSimulationList() {
        return simulationList;
    }

    public void setSimulationList(List<Simulation> simulationList) {
        this.simulationList = simulationList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.msg);
        dest.writeByte(this.status ? (byte) 1 : (byte) 0);
        dest.writeTypedList(this.simulationList);
    }

    public ListSimulationResp() {
    }

    protected ListSimulationResp(Parcel in) {
        this.msg = in.readString();
        this.status = in.readByte() != 0;
        this.simulationList = in.createTypedArrayList(Simulation.CREATOR);
    }

    public static final Creator<ListSimulationResp> CREATOR = new Creator<ListSimulationResp>() {
        @Override
        public ListSimulationResp createFromParcel(Parcel source) {
            return new ListSimulationResp(source);
        }

        @Override
        public ListSimulationResp[] newArray(int size) {
            return new ListSimulationResp[size];
        }
    };
}
