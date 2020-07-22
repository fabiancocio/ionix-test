package cl.cocio.androidtestionix.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import cl.cocio.androidtestionix.R;

public class LinearTextsDialog extends AppCompatDialogFragment {

    private ArrayList<String> mTextList;

    public LinearTextsDialog(String... texts) {
        mTextList = new ArrayList<>();
        mTextList.addAll(Arrays.asList(texts));
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_with_text_views, null);

        LinearLayout llRoot = (LinearLayout) view.getRootView();

        for (String text : mTextList){
            TextView textView = new TextView(getContext());
            textView.setPadding(0, 8, 0, 8);
            textView.setTextSize(16);
            textView.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.black));
            textView.setText(text);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            llRoot.addView(textView);
        }

        builder.setView(view);
        builder.setPositiveButton("Aceptar", (dialog, which) -> dialog.dismiss());

        return builder.create();
    }

}
