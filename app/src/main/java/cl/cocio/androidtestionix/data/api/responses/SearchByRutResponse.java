package cl.cocio.androidtestionix.data.api.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SearchByRutResponse {
    @SerializedName("responseCode")
    private int mResponseCode;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("result")
    private Result mResult;

    public int getResponseCode() {
        return mResponseCode;
    }

    public void setResponseCode(int responseCode) {
        mResponseCode = responseCode;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Result getResult() {
        return mResult;
    }

    public void setResult(Result result) {
        mResult = result;
    }
}

