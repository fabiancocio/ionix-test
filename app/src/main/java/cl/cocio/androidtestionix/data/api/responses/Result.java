package cl.cocio.androidtestionix.data.api.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Result {
    @SerializedName("items")
    private ArrayList<PersonData> mPersonData;

    public ArrayList<PersonData> getPersonData() {
        return mPersonData;
    }

    public void setPersonData(ArrayList<PersonData> personData) {
        mPersonData = personData;
    }
}
