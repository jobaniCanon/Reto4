package com.example.Reto4.Modelo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myretodos.R;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    Context context;
    ArrayList<Producto> listaProductos;

    LayoutInflater inflater;
    public Adaptador(Context context, ArrayList<Producto> listaPersonajes) {
        this.context = context;
        this.listaProductos = listaPersonajes;
    }

    @Override
    public int getCount() {
        return listaProductos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
            convertView = inflater.inflate(R.layout.grid_item, null);
        }

        ImageView imageView = convertView.findViewById(R.id.grid_image);
        TextView textView = convertView.findViewById(R.id.grid_name);

        Producto personaje = listaProductos.get(position);
        byte[] image = personaje.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0,image.length);

        imageView.setImageBitmap(bitmap);
        textView.setText(personaje.getName());


        return convertView;
    }

}
