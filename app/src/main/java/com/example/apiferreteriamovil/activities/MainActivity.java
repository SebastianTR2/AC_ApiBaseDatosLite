package com.example.apiferreteriamovil.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apiferreteriamovil.R;
import com.example.apiferreteriamovil.UsuarioRepository;
import com.example.apiferreteriamovil.adapters.UsuarioAdapter;
import com.example.apiferreteriamovil.api.ApiClient;
import com.example.apiferreteriamovil.api.ApiService;
import com.example.apiferreteriamovil.models.Usuario;
import com.example.apiferreteriamovil.utils.NetworkUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    RecyclerView rvUsuarios;
    UsuarioAdapter adapter;
    ApiService apiService;
    EditText edtBuscar;
    Button btnAddUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvUsuarios = findViewById(R.id.rvUsuarios);
        rvUsuarios.setLayoutManager(new LinearLayoutManager(this));

        edtBuscar = findViewById(R.id.edtBuscar);


        apiService = ApiClient.getClient().create(ApiService.class);



        cargarUsuarios();
    }

    private void cargarUsuarios() {

        if (NetworkUtils.hayInternet(this)) {

            apiService.getUsuarios().enqueue(new Callback<List<Usuario>>() {
                @Override
                public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                    if (response.isSuccessful()) {
                        List<Usuario> lista = response.body();
                        Log.d("TEST", "Usuarios obtenidos API: " + lista.size());

                        // guardar en local
                        UsuarioRepository repo = new UsuarioRepository(MainActivity.this);
                        repo.guardarUsuarios(lista);

                        adapter = new UsuarioAdapter(lista);
                        rvUsuarios.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(Call<List<Usuario>> call, Throwable t) {
                    t.printStackTrace();
                }
            });

        } else {
            // cargar local
            UsuarioRepository repo = new UsuarioRepository(this);
            List<Usuario> listaLocal = repo.obtenerUsuarios();

            adapter = new UsuarioAdapter(listaLocal);
            rvUsuarios.setAdapter(adapter);
        }
    }


}