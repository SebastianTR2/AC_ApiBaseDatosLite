package com.example.apiferreteriamovil.api;

import com.example.apiferreteriamovil.models.Usuario;
import com.example.apiferreteriamovil.models.Rol;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("users")
    Call<List<Usuario>> getUsuarios();
}

