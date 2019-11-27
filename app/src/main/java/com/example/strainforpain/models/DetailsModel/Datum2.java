
package com.example.strainforpain.models.DetailsModel;


import com.google.gson.annotations.SerializedName;


public class Datum2 {

    @SerializedName("CBD")
    private String mCBD;
    @SerializedName("CBG")
    private String mCBG;
    @SerializedName("CBN")
    private String mCBN;
    @SerializedName("Description")
    private String mDescription;
    @SerializedName("image")
    private String mImage;
    @SerializedName("Origins")
    private String mOrigins;
    @SerializedName("Potency")
    private String mPotency;
    @SerializedName("THC")
    private String mTHC;
    @SerializedName("THCV")
    private String mTHCV;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("Type")
    private String mType;

    public String getCBD() {
        return mCBD;
    }

    public void setCBD(String cBD) {
        mCBD = cBD;
    }

    public String getCBG() {
        return mCBG;
    }

    public void setCBG(String cBG) {
        mCBG = cBG;
    }

    public String getCBN() {
        return mCBN;
    }

    public void setCBN(String cBN) {
        mCBN = cBN;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getOrigins() {
        return mOrigins;
    }

    public void setOrigins(String origins) {
        mOrigins = origins;
    }

    public String getPotency() {
        return mPotency;
    }

    public void setPotency(String potency) {
        mPotency = potency;
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

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

}
