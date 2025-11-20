package com.example.apiferreteriamovil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.apiferreteriamovil.models.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {

    private DatabaseHelper dbHelper;

    public UsuarioRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void guardarUsuarios(List<Usuario> usuarios) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + DatabaseHelper.TABLE_USUARIOS);

        for (Usuario u : usuarios) {
            ContentValues values = new ContentValues();
            values.put("id", u.id);
            values.put("name", u.nombre);
            values.put("email", u.correo);

            db.insert(DatabaseHelper.TABLE_USUARIOS, null, values);
        }

        db.close();
    }

    public List<Usuario> obtenerUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT id, name, email FROM usuarios_local", null);

        if (cursor.moveToFirst()) {
            do {
                Usuario u = new Usuario();
                u.id = cursor.getInt(0);
                u.nombre = cursor.getString(1);
                u.correo = cursor.getString(2);
                lista.add(u);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lista;
    }
}
