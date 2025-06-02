package com.shadyalkolak.retrofit.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shadyalkolak.retrofit.R;
import com.shadyalkolak.retrofit.databinding.FragmentAlbumsBinding;
import com.shadyalkolak.retrofit.models.AlbumsModel;
import com.shadyalkolak.retrofit.network.ApiClient;
import com.shadyalkolak.retrofit.network.ApiService;
import com.shadyalkolak.retrofit.view.adapter.AlbumsAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlbumsFragment extends Fragment {
    private FragmentAlbumsBinding binding;

    public AlbumsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAlbumsBinding.inflate(inflater, container, false);
        getAllAlbums();
        return binding.getRoot();
    }

    private void getAllAlbums() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClient apiClient = retrofit.create(ApiClient.class);
        apiClient.getAllAlbums().enqueue(new Callback<List<AlbumsModel>>() {
            @Override
            public void onResponse(Call<List<AlbumsModel>> call, Response<List<AlbumsModel>> response) {
                if (response.isSuccessful()) {
                    List<AlbumsModel> albums = response.body();
                    AlbumsAdapter adapter = new AlbumsAdapter(albums, getContext());
                    binding.rcAlbums.setLayoutManager(new GridLayoutManager(getContext(),2));
                    binding.rcAlbums.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<AlbumsModel>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}