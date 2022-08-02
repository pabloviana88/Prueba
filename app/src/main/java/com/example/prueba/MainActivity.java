package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.JsonToken;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Response.Listener;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.prueba.Datos;
import com.example.prueba.Api;
import com.example.prueba.Resdatos;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.ObjectStreamClass;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String URL = "http://testandroid.macropay.com.mx/";
    private JSONObject endRequestParams;
    EditText campoUsuario, CampoPass;
    Button entrar;
    private static final String TAG = "TOKEN";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        campoUsuario = (EditText) findViewById(R.id.mail);
        CampoPass = (EditText) findViewById(R.id.password);
        entrar = (Button) findViewById(R.id.entrar);

        endRequestParams = new JSONObject();
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (campoUsuario.getText().toString().isEmpty() && CampoPass.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter both the values", Toast.LENGTH_SHORT).show();
                    return;
                }
                postData();
            }
        });
    }

    public void postData() {
        Api service = Api.retrofi.create(Api.class);
        String user = campoUsuario.getText().toString();

        String password = CampoPass.getText().toString();
        RequestBody _user = RequestBody.create(MultipartBody.FORM, user);
        RequestBody _pass = RequestBody.create(MultipartBody.FORM, password);

        final Call<ResponseBody> call = service.Login(_user, _pass);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                try {
                    Log.d("results", "Conexion exitosa" + response.body().toString());
                    String cont = response.body().string();
                    JSONObject miobjeto = new JSONObject(cont);
                    String token = miobjeto.getString("token");
                    Intent savedList = new Intent(MainActivity.this, Contenido.class);
                    savedList.putExtra("token", token);
                    MainActivity.this.startActivity(savedList);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


    }

}






