package com.example.flavormood;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * InfoDialogFragment - A MODAL (pop-up) view that explains how to use the app.
 *
 * This is shown when the user taps the "ⓘ How It Works" button.
 * It's a DialogFragment, which creates a proper modal — not a simple Alert.
 * The user must tap "Got it!" to dismiss it.
 */
public class InfoDialogFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate the modal layout
        View view = inflater.inflate(R.layout.dialog_info, container, false);

        // Find the close button and dismiss the dialog when tapped
        Button btnClose = view.findViewById(R.id.btn_close_dialog);
        btnClose.setOnClickListener(v -> dismiss());

        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Use the default Dialog but with rounded corners from our theme
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Make the modal a nice width — 90% of screen width
        if (getDialog() != null && getDialog().getWindow() != null) {
            int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
            getDialog().getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
            // Rounded background
            getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
    }
}
