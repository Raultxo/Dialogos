package com.example.dialogos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends AppCompatActivity implements DialogoCerrar.InterfazDialogoCerrar, DialogoInicio.InterfazDialogoInicioSesion {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        DialogoInicio dialogoInicio = new DialogoInicio();
        dialogoInicio.show(fragmentManager, "tagAlerta");
    }

    @Override
    public void onPossitiveButtonClick() {
        finish();
    }

    @Override
    public void onPossitiveButtonClick2(String nombre, String pass) {
       if(!nombre.equals("usuario1") || !pass.equals("123456")) {
           FragmentManager fragmentManager = getSupportFragmentManager();
           DialogoInicio dialogoInicio = new DialogoInicio();
           dialogoInicio.show(fragmentManager, "DialogoInicio");
        }
    }

    @Override
    public void onNegativeButtonClick2() {
        finish();
    }

    public void salir(View v) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DialogoCerrar dialogoCerrar = new DialogoCerrar();
        dialogoCerrar.show(fragmentManager, "DialogoSalir");
    }

    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DialogoCerrar dialogoCerrar = new DialogoCerrar();
        dialogoCerrar.show(fragmentManager, "DialogoSalir");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}