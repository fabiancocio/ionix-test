package cl.cocio.androidtestionix.data.api.responses;

import com.google.gson.annotations.SerializedName;

public class CreateUserResponse {
    @SerializedName("id")
    private int mId;

    public String getId() {
        return String.valueOf(mId);
    }

    public void setId(int id) {
        mId = id;
    }
}
