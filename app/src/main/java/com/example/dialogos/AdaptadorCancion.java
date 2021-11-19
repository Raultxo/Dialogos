package com.example.dialogos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AdaptadorCancion extends ArrayAdapter<Cancion> {

    private Cancion[] datos;
    public AdaptadorCancion(@NonNull Context context, Cancion[] datos) {
        super(context, R.layout.cancion_layout, datos);
        this.datos = datos;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.cancion_layout, null);

        TextView titlo = (TextView) item.findViewById(R.id.titulo);
        titlo.setText(datos[position].getNombre());

        TextView duracion = (TextView) item.findViewById(R.id.duracion);
        duracion.setText(datos[position].getDuracion());

        return item;
    }

}
