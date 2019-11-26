
package com.example.strainforpain.Adapters.Model.DiseaseHomeResponse;

import java.util.List;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class DiseaseHomeResponse {

    @SerializedName("data")
    private List<Datum1> mData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Boolean mStatus;

    public List<Datum1> getData() {
        return mData;
    }

    public void setData(List<Datum1> data) {
        mData = data;
    }

    public String getMsg() {
        return mMsg;
    }

    public void setMsg(String msg) {
        mMsg = msg;
    }

    public Boolean getStatus() {
        return mStatus;
    }

    public void setStatus(Boolean status) {
        mStatus = status;
    }

}
