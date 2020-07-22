package cl.cocio.androidtestionix.data.api.retrofit;

import com.google.gson.JsonObject;

import cl.cocio.androidtestionix.data.api.responses.CreateUserResponse;
import cl.cocio.androidtestionix.data.api.responses.SearchByRutResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServices {

    @GET("test-tecnico/search")
    Call<SearchByRutResponse> searchByRut(@Query(value = "rut", encoded = true) String rut);

    @POST("users")
    Call<CreateUserResponse> createUser(@Body JsonObject jsonObject);
}
