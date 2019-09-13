package com.example.miniface;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MensajeAdapter extends BaseAdapter {
    Context context;
    List<Mensaje> datos;
    int layout;
    int layout_respuesta;
    ImageView img;
    Drawable img_respuesta;



    public MensajeAdapter(Context context, List<Mensaje> datos, int layout,int layout_respuesta, ImageView img, Drawable img_respuesta) {
        this.context = context;
        this.datos = datos;
        this.layout = layout;
        this.img = img;
        this.img_respuesta = img_respuesta;

    }

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int i) {
        return datos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v;
        if (datos.get(i).lado) {
            v = inflater.inflate(layout, null);
            TextView nombre = v.findViewById(R.id.txt_mensaje);
            nombre.setText(datos.get(i).mensaje);
            ImageView imagen = v.findViewById(R.id.img_mensaje);
            imagen.setImageDrawable(img.getDrawable());

        }else{
            v = inflater.inflate(layout_respuesta, null);
            TextView nombre = v.findViewById(R.id.txt_respuesta);
            nombre.setText(datos.get(i).mensaje);
            ImageView imagen = v.findViewById(R.id.img_respuesta);
            imagen.setImageDrawable(img_respuesta);
        }
        return v;
    }
}
