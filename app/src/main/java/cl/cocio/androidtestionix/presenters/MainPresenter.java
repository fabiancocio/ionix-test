package cl.cocio.androidtestionix.presenters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import cl.cocio.androidtestionix.data.api.responses.PersonDetail;
import cl.cocio.androidtestionix.interfaces.MainInterface;
import cl.cocio.androidtestionix.models.MainModel;
import cl.cocio.androidtestionix.views.MainView;

public class MainPresenter implements MainInterface.Presenter {
    private MainView mView;
    private MainModel mModel;

    public MainPresenter(MainView mainView) {
        mView = mainView;
        mModel = new MainModel(this);
    }

    @Override
    public void setUserData(@NonNull String name) {
        mView.setUserData(name);
    }

    @Override
    public void showMessage(String message) {
        mView.hideLoadingDialog();
        mView.showMessage(message);
    }

    @Override
    public void setPersonDetail(@NonNull PersonDetail personDetail) {
        mView.hideLoadingDialog();
        mView.setPersonDetail(personDetail);
    }

    @Override
    public void showUserId(@NonNull String id) {
        mView.hideLoadingDialog();
        mView.showUserId(id);
    }

    @Override
    public void getUserData() {
        mModel.getUserData();
    }

    @Override
    public void searchByRut(Context context, @NonNull String rut) {
        mView.showLoadingDialog();
        mModel.searchByRut(context, rut);
    }

    @Override
    public void createUser(PersonDetail personDetail) {
        mView.showLoadingDialog();
        mModel.createUser(personDetail);
    }
}
