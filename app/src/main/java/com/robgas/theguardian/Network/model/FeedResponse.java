
package com.robgas.theguardian.Network.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FeedResponse implements Serializable {
    @SerializedName("response")
    public Response response;
}
