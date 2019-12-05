
package com.example.strainforpain.models.DetailsModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Datum2 {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("THC")
    @Expose
    private String tHC;
    @SerializedName("CBD")
    @Expose
    private String cBD;
    @SerializedName("CBN")
    @Expose
    private String cBN;
    @SerializedName("THCV")
    @Expose
    private String tHCV;
    @SerializedName("CBG")
    @Expose
    private String cBG;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("Origins")
    @Expose
    private String origins;
    @SerializedName("Potency")
    @Expose
    private String potency;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Url")
    @Expose
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTHC() {
        return tHC;
    }

    public void setTHC(String tHC) {
        this.tHC = tHC;
    }

    public String getCBD() {
        return cBD;
    }

    public void setCBD(String cBD) {
        this.cBD = cBD;
    }

    public String getCBN() {
        return cBN;
    }

    public void setCBN(String cBN) {
        this.cBN = cBN;
    }

    public String getTHCV() {
        return tHCV;
    }

    public void setTHCV(String tHCV) {
        this.tHCV = tHCV;
    }

    public String getCBG() {
        return cBG;
    }

    public void setCBG(String cBG) {
        this.cBG = cBG;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrigins() {
        return origins;
    }

    public void setOrigins(String origins) {
        this.origins = origins;
    }

    public String getPotency() {
        return potency;
    }

    public void setPotency(String potency) {
        this.potency = potency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
