package com.example.cameraandgalleryapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Button btnClick;
    private ImageView imgPic;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnClick=findViewById(R.id.btnClick);
        imgPic=findViewById(R.id.imgPic);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();
                loadCamera();

            }
        });



    }
    private void checkPermission()
    {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }

    private void loadCamera()
    {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager())!= null){
            startActivityForResult(intent,0);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode ==RESULT_OK){
            Bundle extras =data.getExtras();
            Bitmap imageBitmap =(Bitmap) extras.get("data");
            imgPic.setImageBitmap(imageBitmap);
        }
    }
}
