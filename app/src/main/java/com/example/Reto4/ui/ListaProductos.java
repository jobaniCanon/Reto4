package com.example.Reto4.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Reto4.Datos.DBProductos;
import com.example.Reto4.Modelo.Adaptador;
import com.example.Reto4.Modelo.Producto;
import com.example.myretodos.R;

import java.util.ArrayList;

public class ListaProductos extends AppCompatActivity {
    private DBProductos dbProductos;
    private ArrayList<Producto> listaProductos;
    private GridView gridView;


    public ArrayList<Producto> llenarLista(Cursor cursor){
        ArrayList<Producto> list = new ArrayList<>();
        if(cursor.getCount() == 0){
            return list;
        }else{
            StringBuffer buffer = new StringBuffer();
            while (cursor.moveToNext()){
                Producto producto = new Producto(
                        cursor.getString(1),
                        cursor.getBlob(2)
                );
                list.add(producto);
            }
            return list;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);
        dbProductos = new DBProductos(getApplicationContext());
        gridView = (GridView) findViewById(R.id.gridView);

        Cursor cursor = dbProductos.getProductos();
        listaProductos = llenarLista(cursor);
        Adaptador adaptador = new Adaptador(getApplicationContext(),listaProductos);
        gridView.setAdapter(adaptador);

    }
}
