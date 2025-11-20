package com.example.apiferreteriamovil.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apiferreteriamovil.R;
import com.example.apiferreteriamovil.api.ApiClient;
import com.example.apiferreteriamovil.api.ApiService;
import com.example.apiferreteriamovil.models.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditUserActivity extends AppCompatActivity {

    EditText edtNombre, edtCorreo;
    Button btnGuardar, btnEliminar;
    ApiService apiService;

    int userId;
    int rolId = 1; // Temporal, porque tu API requiere un rol aunque no lo muestres aquí

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        edtNombre = findViewById(R.id.edtEditarNombre);
        edtCorreo = findViewById(R.id.edtEditarCorreo);
        btnGuardar = findViewById(R.id.btnGuardarCambios);
        btnEliminar = findViewById(R.id.btnEliminarUsuario);

        apiService = ApiClient.getClient().create(ApiService.class);

        userId = getIntent().getIntExtra("id", -1);
        edtNombre.setText(getIntent().getStringExtra("nombre"));
        edtCorreo.setText(getIntent().getStringExtra("correo"));
        btnGuardar.setOnClickListener(v -> guardarCambios());
        btnEliminar.setOnClickListener(v -> eliminarUsuario());
    }

    private void guardarCambios() {
        Usuario u = new Usuario();
        u.id = userId;
        u.nombre = edtNombre.getText().toString().trim();
        u.correo = edtCorreo.getText().toString().trim();
        u.rolId = rolId;

        if (u.nombre.isEmpty() || u.correo.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        apiService.updateUsuario(userId, u).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(EditUserActivity.this, "Usuario actualizado", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(EditUserActivity.this, "Error al actualizar", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void eliminarUsuario() {
        apiService.deleteUsuario(userId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(EditUserActivity.this, "Usuario eliminado", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(EditUserActivity.this, "Error al eliminar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
