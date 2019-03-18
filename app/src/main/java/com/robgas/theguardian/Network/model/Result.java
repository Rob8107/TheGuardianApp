
package com.robgas.theguardian.Network.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Result implements Serializable {
    @SerializedName("id")
    public String id;

    @SerializedName("sectionName")
    public String sectionName;

    @SerializedName("webTitle")
    public String webTitle;

    @SerializedName("webUrl")
    public String webUrl;

    @SerializedName("fields")
    public Fields fields;
}
