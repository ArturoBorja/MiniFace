package com.example.miniface;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageView img_perfil;
    ImageView img_perfil_resp;
    EditText ext_mensaje;
    List<Mensaje> datos;
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

                datos.add(new Mensaje(ext_mensaje.getText().toString(),true,new Date()));
                mensaje_adapter.notifyDataSetChanged();
                Responder();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,"" + datos.get(i).fecha.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                MostrarMenu(view,i);
                return true;
            }
        });
    }
    void IniciarVariables(){
        img_perfil=findViewById(R.id.img_perfil);
        img_perfil_resp=findViewById(R.id.img_perfil_resp);
        ext_mensaje=findViewById(R.id.ext_mensaje);
        datos=new ArrayList<>();
        listView=findViewById(R.id.listview);
        mensaje_adapter = new MensajeAdapter(this, datos, R.layout.item_mensaje,R.layout.item_respuesta,img_perfil,img_perfil_resp);
        listView.setAdapter(mensaje_adapter);
    }
    void CargarPerfil(){

        Glide.with(MainActivity.this).load("https://www.biography.com/.image/t_share/MTE5NTU2MzE1OTk1Mjc2ODEx/ryan-gosling-212045-1-402.jpg").listener(new RequestListener<Drawable>() {
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
                img_perfil_resp.setImageDrawable(roundedDrawable);
                return true;
            }
        }).into(img_perfil_resp);

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
    void Responder(){
        int dado = (int) (Math.random() * 4) + 1;
        for (int i=0; i<dado;i++){
            datos.add(new Mensaje("hola marcia",false, new Date()));

        }
        mensaje_adapter.notifyDataSetChanged();
    }
    void MostrarMenu( View v, final int i){
        PopupMenu menu = new PopupMenu(this,v);

        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_editar:
                        MostrarDialogoEditar(datos.get(i),i);

                        break;
                    case R.id.menu_eliminar:
                        datos.remove(i);
                        mensaje_adapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });
        menu.inflate(R.menu.menu_mensaje);
        menu.show();
    }
    void MostrarDialogoEditar(Mensaje mensaje, final int posicion){
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("MODIFICAR MENSAJE");

        builder.setMessage(mensaje.mensaje);

        View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_modificar_mensaje,null);
        builder.setView(v);
        final EditText et =v.findViewById(R.id.ext_nuevomensaje);

        builder.setPositiveButton("Cambiar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                datos.set(posicion, (new Mensaje((et.getText().toString()), datos.get(posicion).lado,new Date())));

            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
}
