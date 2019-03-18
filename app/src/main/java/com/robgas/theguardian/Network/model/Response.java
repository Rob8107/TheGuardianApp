package com.robgas.theguardian.Network.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Response implements Serializable {

    @SerializedName("startIndex")
    public int startIndex;

    @SerializedName("pageSize")
    public int pageSize;

    @SerializedName("currentPage")
    public int currentPage;

    @SerializedName("pages")
    public int pages;

    @SerializedName("results")
    public List<Result> results = new ArrayList<Result>();

}
