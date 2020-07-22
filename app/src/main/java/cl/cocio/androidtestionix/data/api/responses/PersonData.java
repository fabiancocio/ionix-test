package cl.cocio.androidtestionix.data.api.responses;

import com.google.gson.annotations.SerializedName;

public class PersonData {
    @SerializedName("name")
    private String name;
    @SerializedName("detail")
    private PersonDetail mPersonDetail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PersonDetail getPersonDetail() {
        return mPersonDetail;
    }

    public void setPersonDetail(PersonDetail personDetail) {
        mPersonDetail = personDetail;
    }
}
