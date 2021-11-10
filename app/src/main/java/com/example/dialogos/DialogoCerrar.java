package com.example.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogoCerrar extends DialogFragment {

    private InterfazDialogoCerrar listener;

    @NonNull
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Seguro que quieres salir?").setTitle("Salir")
                .setPositiveButton("SALIR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onPossitiveButtonClick();
                    }
                })
                .setNegativeButton("VOLVER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        return builder.create();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (InterfazDialogoCerrar) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " no implemento OnDialogoConfirmacion");
        }
    }

    public interface InterfazDialogoCerrar {
        void onPossitiveButtonClick();
    }

}
