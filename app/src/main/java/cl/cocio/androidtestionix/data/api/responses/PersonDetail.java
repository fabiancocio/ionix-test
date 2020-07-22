package cl.cocio.androidtestionix.data.api.responses;

import com.google.gson.annotations.SerializedName;

public class PersonDetail {
    @SerializedName("email")
    private String mEmail;
    @SerializedName("phone_number")
    private String mPhoneNumber;

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }
}
