package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class Contenido extends AppCompatActivity {
    public ImageView imgBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenido);

        imgBar = (ImageView) findViewById(R.id.imageView);
        Intent intent = getIntent();
       String _token= intent.getExtras().getString("token");

        jsbarcode(_token);
    }
    public void jsbarcode(String mitoken) {
        try{
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(mitoken, BarcodeFormat.PDF_417, 50, 10);
            ImageView imageViewQrCode = (ImageView) findViewById(R.id.imageView);
            imageViewQrCode.setImageBitmap(bitmap);

        } catch (Exception e) {


        }
    }
    }

