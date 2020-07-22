package cl.cocio.androidtestionix.interfaces;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import cl.cocio.androidtestionix.data.api.responses.PersonDetail;

public interface MainInterface {

    interface View {
        void setUserData(@NonNull String name);

        void showRutDialog();

        void showLoadingDialog();

        void hideLoadingDialog();

        void showMessage(String message);

        void setPersonDetail(@NonNull PersonDetail personDetail);

        void showUserId(@NonNull String id);
    }

    interface Presenter {
        /* View */
        void setUserData(@NonNull String name);

        void showMessage(String message);

        void setPersonDetail(@NonNull PersonDetail personDetail);

        void showUserId(@NonNull String id);

        /* Model */
        void getUserData();

        void searchByRut(Context context, @NonNull String rut);

        void createUser(PersonDetail personDetail);
    }

    interface Model {
        void getUserData();

        void searchByRut(Context context, @NonNull String rut);

        void createUser(PersonDetail personDetail);
    }
}
