package com.example.Reto4.Datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DBProductos extends SQLiteOpenHelper {

    private SQLiteDatabase sqLiteDatabase;

    public DBProductos(Context context) {
        super(context, "reto3.db", null, 1);
        sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE PRODUCTOS(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME VARCHAR," +
                "IMAGE BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS PRODUCTOS");
    }

    // Funciones personalizadas
    public void insertProductos(String name, byte[] image){
        String sql = "INSERT INTO PRODUCTOS VALUES(null, ?, ?)";
        SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindBlob(2, image);

        statement.executeInsert();
    }

    public Cursor getProductos(){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PRODUCTOS", null);
        return cursor;
    }

    public Cursor getProductosById(String id){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PRODUCTOS WHERE ID = "+id, null);
        return cursor;
    }

    public void deleteProductos(String id){
        String[] args = new String[]{id};
        sqLiteDatabase.delete("PRODUCTOS","ID=?",args);
    }

    public void updateProductos(String id, String name, byte[] image){
        String sql = "UPDATE PRODUCTOS " +
                "SET NAME = ?," +
                "IMAGE = ?";
        SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindBlob(2, image);

        statement.executeUpdateDelete();
    }
}
