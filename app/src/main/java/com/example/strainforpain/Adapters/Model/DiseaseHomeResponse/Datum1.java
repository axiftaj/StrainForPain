
package com.example.strainforpain.Adapters.Model.DiseaseHomeResponse;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Datum1 {

    @SerializedName("CBD")
    private String mCBD;
    @SerializedName("CBN")
    private String mCBN;
    @SerializedName("disease_id")
    private String mDiseaseId;
    @SerializedName("id")
    private String mId;
    @SerializedName("image")
    private String mImage;
    @SerializedName("THC")
    private String mTHC;
    @SerializedName("THCV")
    private String mTHCV;
    @SerializedName("title")
    private String mTitle;

    public String getCBD() {
        return mCBD;
    }

    public void setCBD(String cBD) {
        mCBD = cBD;
    }

    public String getCBN() {
        return mCBN;
    }

    public void setCBN(String cBN) {
        mCBN = cBN;
    }

    public String getDiseaseId() {
        return mDiseaseId;
    }

    public void setDiseaseId(String diseaseId) {
        mDiseaseId = diseaseId;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getTHC() {
        return mTHC;
    }

    public void setTHC(String tHC) {
        mTHC = tHC;
    }

    public String getTHCV() {
        return mTHCV;
    }

    public void setTHCV(String tHCV) {
        mTHCV = tHCV;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}
