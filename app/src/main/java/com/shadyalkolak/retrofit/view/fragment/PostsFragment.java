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
import com.shadyalkolak.retrofit.databinding.FragmentPostsBinding;
import com.shadyalkolak.retrofit.models.AlbumsModel;
import com.shadyalkolak.retrofit.models.PostsModel;
import com.shadyalkolak.retrofit.network.ApiClient;
import com.shadyalkolak.retrofit.network.ApiService;
import com.shadyalkolak.retrofit.view.adapter.AlbumsAdapter;
import com.shadyalkolak.retrofit.view.adapter.PostsAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PostsFragment extends Fragment {
    private FragmentPostsBinding binding;

    public PostsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostsBinding.inflate(inflater, container, false);
        getAllPosts();
        return binding.getRoot();

    }


    private void getAllPosts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClient apiClient = retrofit.create(ApiClient.class);
        apiClient.getAllPosts().enqueue(new Callback<List<PostsModel>>() {
            @Override
            public void onResponse(Call<List<PostsModel>> call, Response<List<PostsModel>> response) {
                if (response.isSuccessful()) {
                    List<PostsModel> posts = response.body();
                    PostsAdapter adapter = new PostsAdapter(posts, getContext());
                    binding.rcPosts.setLayoutManager(new LinearLayoutManager(getContext()));
                    binding.rcPosts.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<PostsModel>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}