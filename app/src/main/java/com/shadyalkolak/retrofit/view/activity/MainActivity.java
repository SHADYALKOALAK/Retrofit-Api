package com.shadyalkolak.retrofit.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.shadyalkolak.retrofit.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final Context context = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new Thread(() -> {
            try {
                Thread.sleep(3000);
                startActivity(new Intent(context,UsersScreen.class));
                finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}