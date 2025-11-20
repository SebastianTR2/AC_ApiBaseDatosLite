package com.example.apiferreteriamovil.api;

import com.example.apiferreteriamovil.models.Usuario;
import com.example.apiferreteriamovil.models.Rol;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    @GET("Usuarios")
    Call<List<Usuario>> getUsuarios();

    @POST("Usuarios")
    Call<Usuario> createUsuario(@Body Usuario usuario);

    @GET("Roles")
    Call<List<Rol>> getRoles();

    @PUT("Usuarios/{id}")
    Call<Void> updateUsuario(@Path("id") int id, @Body Usuario usuario);

    @DELETE("Usuarios/{id}")
    Call<Void> deleteUsuario(@Path("id") int id);
}


