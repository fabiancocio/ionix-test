package cl.cocio.androidtestionix.data.api.request;

import com.google.gson.JsonObject;

public class ApiRequestBody {

    public JsonObject createUser(String email, String phoneNumber){
        JsonObject user = new JsonObject();
        user.addProperty("email", email);
        user.addProperty("phone_number", phoneNumber);

        return user;
    }
}
