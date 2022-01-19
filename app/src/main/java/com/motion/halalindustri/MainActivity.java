package com.motion.halalindustri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
 ImageView imageView;
 EditText editText;
 Uri imageUri;
 RecyclerView recyclerView;
 ImageAdapter adapter;
 List<ImageClass> imageClasses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewS();
        setupListener();
        setupRecycler();
        imageClasses=new ArrayList<>();

    }

    private void setupRecycler() {
        String title=editText.getText().toString();
        imageClasses.add(new ImageClass(title,imageUri));
        adapter=new ImageAdapter(imageClasses);
        recyclerView.setAdapter(adapter);
    }

    private void setupListener() {
        imageView.setOnClickListener(view -> {
            requestPermissionsForGallery();

        });

    }
    //add something

    private void initViewS() {
        imageView=findViewById(R.id.image_food);
        editText=findViewById(R.id.edit_food);
        recyclerView=findViewById(R.id.recyclerView);
    }

    private void requestPermissionsForGallery() {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(MainActivity.this,new  String [] {Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }else {
            Intent galleryIntent =new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent,1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            Intent galleryIntent =new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent,1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode==RESULT_OK&&data!=null){
             imageUri=data.getData();
            imageView.setImageURI(imageUri);
        }
    }
}