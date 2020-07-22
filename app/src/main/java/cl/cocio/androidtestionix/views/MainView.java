package cl.cocio.androidtestionix.views;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;

import cl.cocio.androidtestionix.BuildConfig;
import cl.cocio.androidtestionix.R;
import cl.cocio.androidtestionix.data.api.responses.PersonDetail;
import cl.cocio.androidtestionix.databinding.ActivityMainViewBinding;
import cl.cocio.androidtestionix.dialogs.LoadingDialog;
import cl.cocio.androidtestionix.dialogs.LinearTextsDialog;
import cl.cocio.androidtestionix.dialogs.RutDialog;
import cl.cocio.androidtestionix.interfaces.MainInterface;
import cl.cocio.androidtestionix.interfaces.RutDialogListener;
import cl.cocio.androidtestionix.presenters.MainPresenter;

public class MainView extends AppCompatActivity implements MainInterface.View, RutDialogListener {

    ActivityMainViewBinding mViewBinding;
    MainPresenter mPresenter;
    LoadingDialog mLoadingDialog;
    PersonDetail mPersonDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setNavigationBarColor(Color.TRANSPARENT);
        mViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_view);

        mPresenter = new MainPresenter(this);
        mLoadingDialog = new LoadingDialog();


        ViewCompat.setOnApplyWindowInsetsListener(mViewBinding.getRoot(), (v, insets) -> {
            ConstraintLayout.LayoutParams titleLayoutParams = (ConstraintLayout.LayoutParams) mViewBinding.tvTitleWelcome.getLayoutParams();
            titleLayoutParams.topMargin = insets.getSystemWindowInsetTop() + titleLayoutParams.topMargin;
            mViewBinding.tvTitleWelcome.setLayoutParams(titleLayoutParams);

            ConstraintLayout.LayoutParams bottomLayoutParams = (ConstraintLayout.LayoutParams) mViewBinding.viewBottom.getLayoutParams();
            bottomLayoutParams.bottomMargin = insets.getSystemWindowInsetBottom();
            mViewBinding.viewBottom.setLayoutParams(bottomLayoutParams);
            return insets.consumeSystemWindowInsets();
        });

        mViewBinding.llEasyPay.setOnClickListener(v -> showRutDialog());

        mViewBinding.llWallet.setOnClickListener(v -> mPresenter.createUser(mPersonDetail));

        mViewBinding.tvVersionApp.setText(String.format(getString(R.string.version_app), BuildConfig.VERSION_NAME));
        mPresenter.getUserData();
    }

    @Override
    public void setUserData(@NonNull String name) {
        mViewBinding.tvUserName.setText(String.format(getString(R.string.user_name), name));
    }

    @Override
    public void showRutDialog() {
        RutDialog rutDialog = new RutDialog();
        rutDialog.show(getSupportFragmentManager(), "rut");
    }

    @Override
    public void showLoadingDialog() {
        mLoadingDialog.show(getSupportFragmentManager(), "loading");
    }

    @Override
    public void hideLoadingDialog() {
        if(mLoadingDialog != null){
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setPersonDetail(@NonNull PersonDetail personDetail) {
        mPersonDetail = personDetail;
        LinearTextsDialog linearTextsDialog = new LinearTextsDialog(mPersonDetail.getEmail(), mPersonDetail.getPhoneNumber());
        linearTextsDialog.show(getSupportFragmentManager(), "person_detail");
    }

    @Override
    public void showUserId(@NonNull String id) {
        mPersonDetail = null;
        LinearTextsDialog linearTextsDialog = new LinearTextsDialog(id);
        linearTextsDialog.show(getSupportFragmentManager(), "user_id");
    }

    @Override
    public void setRut(@NonNull String rut) {
        mPresenter.searchByRut(getApplicationContext(), rut);
    }
}