package cl.cocio.androidtestionix.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.Objects;

import cl.cocio.androidtestionix.R;
import cl.cocio.androidtestionix.interfaces.RutDialogListener;
import cl.cocio.androidtestionix.utilities.RutValidator;

public class RutDialog extends AppCompatDialogFragment {

    private EditText mEdtRut;
    private RutDialogListener mRutDialogListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_with_edit_text, null);

        builder.setView(view);
        builder.setTitle("Ingresa un RUT");
        builder.setPositiveButton("Enviar", (dialog, which) -> {
            String rut = mEdtRut.getText().toString().trim();
            if (RutValidator.validate(rut)){
                mRutDialogListener.setRut(rut);
            } else {
                mEdtRut.setError("Debes ingresar un RUT válido");
            }
        });

        builder.setNegativeButton("Volver", (dialog, which) -> {
            dismiss();
        });

        mEdtRut = view.findViewById(R.id.edt_rut);
        mEdtRut.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() > 1) {
                    mEdtRut.removeTextChangedListener(this);
                    mEdtRut.setText(RutValidator.format(s.toString().trim()));
                    mEdtRut.setSelection(mEdtRut.getText().toString().trim().length());
                    mEdtRut.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                AlertDialog dialog = (AlertDialog) getDialog();
                if (dialog != null){
                    if (RutValidator.validate(s.toString().trim())){
                        mEdtRut.setError(null);
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                    } else {
                        mEdtRut.setError("Debes ingresar un RUT válido");
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                    }
                }
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mRutDialogListener = (RutDialogListener) context;
        } catch (ClassCastException ex) {
            throw new ClassCastException(context.toString() + " must implement RutDialogListener");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        AlertDialog dialog = (AlertDialog) getDialog();
        if (dialog != null){
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
        }
    }
}
