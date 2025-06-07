package com.shadyalkolak.retrofit.view.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import com.shadyalkolak.retrofit.R;
import com.shadyalkolak.retrofit.databinding.ActivityPhotosScreenBinding;
import com.shadyalkolak.retrofit.models.PhotosModel;
import com.shadyalkolak.retrofit.network.ApiClient;
import com.shadyalkolak.retrofit.network.ApiService;
import com.shadyalkolak.retrofit.view.adapter.PhotosAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotosScreen extends AppCompatActivity {
    private ActivityPhotosScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhotosScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getPhotos();
    }

    private void getPhotos() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClient apiClient = retrofit.create(ApiClient.class);
        apiClient.getAllPhotos().enqueue(new Callback<List<PhotosModel>>() {
            @Override
            public void onResponse(Call<List<PhotosModel>> call, Response<List<PhotosModel>> response) {
                if (response.isSuccessful()) {
                    List<PhotosModel> photos = response.body();
                    PhotosAdapter adapter = new PhotosAdapter(photos, PhotosScreen.this);
                    binding.rcPhotos.setLayoutManager(new GridLayoutManager(PhotosScreen.this,2));
                    binding.rcPhotos.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<PhotosModel>> call, Throwable t) {
                Toast.makeText(PhotosScreen.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}