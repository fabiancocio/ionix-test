package cl.cocio.androidtestionix.models;

import android.content.Context;

import androidx.annotation.NonNull;

import cl.cocio.androidtestionix.data.api.request.ApiRequestBody;
import cl.cocio.androidtestionix.data.api.responses.CreateUserResponse;
import cl.cocio.androidtestionix.data.api.responses.PersonDetail;
import cl.cocio.androidtestionix.data.api.responses.SearchByRutResponse;
import cl.cocio.androidtestionix.data.api.retrofit.ApiConnector;
import cl.cocio.androidtestionix.data.api.retrofit.ApiServices;
import cl.cocio.androidtestionix.interfaces.MainInterface;
import cl.cocio.androidtestionix.presenters.MainPresenter;
import cl.cocio.androidtestionix.utilities.DesEncryption;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainModel implements MainInterface.Model {

    private MainPresenter mPresenter;

    public MainModel(MainPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getUserData() {
        // simulate shared preferences
        String name = "Fabián Cocio";
        mPresenter.setUserData(name);
    }

    @Override
    public void searchByRut(Context context, @NonNull String rut) {
        String encryptedRut = DesEncryption.encrypt(context, rut);

        ApiServices apiServices = ApiConnector.getInstance().getIonixRetrofit().create(ApiServices.class);

        Call<SearchByRutResponse> call = apiServices.searchByRut(encryptedRut);
        call.enqueue(new Callback<SearchByRutResponse>() {
            @Override
            public void onResponse(@NonNull Call<SearchByRutResponse> call, @NonNull Response<SearchByRutResponse> response) {
                if (!response.isSuccessful()) {
                    mPresenter.showMessage("Error");
                    return;
                }

                if (response.body() == null) {
                    mPresenter.showMessage("Error");
                    return;
                }

                SearchByRutResponse searchByRutResponse = response.body();

                if (searchByRutResponse.getResult().getPersonData().size() > 2) {
                    mPresenter.setPersonDetail(searchByRutResponse.getResult().getPersonData().get(1).getPersonDetail());
                    return;
                }

                mPresenter.showMessage("No se encontró información con el Rut");
            }

            @Override
            public void onFailure(@NonNull Call<SearchByRutResponse> call, @NonNull Throwable t) {
                mPresenter.showMessage("Error: " + t.getMessage());
            }
        });

    }

    @Override
    public void createUser(PersonDetail personDetail) {

        if (personDetail == null) {
            mPresenter.showMessage("Primero debes realizar una búsqueda por RUT");
            return;
        }

        ApiRequestBody apiRequestBody = new ApiRequestBody();
        ApiServices apiServices = ApiConnector.getInstance().getTypicodeRetrofit().create(ApiServices.class);

        Call<CreateUserResponse> call = apiServices.createUser(apiRequestBody.createUser(personDetail.getEmail(), personDetail.getPhoneNumber()));
        call.enqueue(new Callback<CreateUserResponse>() {
            @Override
            public void onResponse(@NonNull Call<CreateUserResponse> call, @NonNull Response<CreateUserResponse> response) {
                if (!response.isSuccessful()) {
                    mPresenter.showMessage("Error");
                    return;
                }

                if (response.body() == null) {
                    mPresenter.showMessage("Error");
                    return;
                }

                CreateUserResponse createUserResponse = response.body();

                mPresenter.showUserId(createUserResponse.getId());
            }

            @Override
            public void onFailure(@NonNull Call<CreateUserResponse> call, @NonNull Throwable t) {
                mPresenter.showMessage("Error: " + t.getMessage());
            }
        });
    }
}
