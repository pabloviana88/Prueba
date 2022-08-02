package com.example.prueba;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Api {
    //http://testandroid.macropay.com.mx/

    Gson gson=new GsonBuilder().setLenient().create();
    public static final Retrofit retrofi= new Retrofit.Builder()
            .baseUrl("http://testandroid.macropay.com.mx")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    @Multipart
    @POST("/")
    Call<ResponseBody> Login(
            @Part("email") RequestBody email,
            @Part("password") RequestBody password
    );

    @FormUrlEncoded
    @POST("/")
    Call<ResponseBody>obtenerDatosUsuario(@Body RequestBody requestBody);


}
