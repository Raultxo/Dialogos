package com.example.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogoInicio extends DialogFragment {

    private InterfazDialogoInicioSesion listener;
    private EditText txtNombre, txtPass;

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        setCancelable(false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = onCreateView(inflater, null,null);
        builder.setView(inflater.inflate(R.layout.dialogo_inicio, null))
                .setPositiveButton("Iniciar sesion", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onPossitiveButtonClick2(txtNombre.getText().toString(), txtPass.getText().toString());
                    }
                }).setNegativeButton("SALIR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onNegativeButtonClick2();
                    }
        });
        return builder.create();
    }

    public void onStart() {
        super.onStart();
        txtNombre = (EditText) getDialog().findViewById(R.id.txtNombre);
        txtPass = (EditText) getDialog().findViewById(R.id.txtPass);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener = (InterfazDialogoInicioSesion) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " no Implemento InterfazDialogoInicioSesion");
        }
    }

    public interface InterfazDialogoInicioSesion {
        void onPossitiveButtonClick2(String nombre, String pass);
        void onNegativeButtonClick2();
    }

}
