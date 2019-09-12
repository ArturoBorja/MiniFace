package com.example.miniface;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageView img_perfil;
    EditText ext_mensaje;
    List<String> datos;
    MensajeAdapter mensaje_adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IniciarVariables();
        CargarPerfil();
        img_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datos.add(ext_mensaje.getText().toString());
                mensaje_adapter.notifyDataSetChanged();
            }
        });
    }
    void IniciarVariables(){
        img_perfil=findViewById(R.id.img_perfil);
        ext_mensaje=findViewById(R.id.ext_mensaje);
        datos=new ArrayList<>();
        listView=findViewById(R.id.listview);
        mensaje_adapter = new MensajeAdapter(this, datos, R.layout.item_mensaje,img_perfil);
        listView.setAdapter(mensaje_adapter);
    }
    void CargarPerfil(){
        Glide.with(MainActivity.this).load("https://i.pinimg.com/280x280_RS/0e/f4/3c/0ef43cf48f536cccdbdccf4d8d07c59e.jpg").listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                Drawable originalDrawable = resource;
                Bitmap originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();
                RoundedBitmapDrawable roundedDrawable = RoundedBitmapDrawableFactory.create(getResources(), originalBitmap);
                roundedDrawable.setCornerRadius(originalBitmap.getHeight());
                img_perfil.setImageDrawable(roundedDrawable);
                return true;
            }
        }).into(img_perfil);

    }
}
