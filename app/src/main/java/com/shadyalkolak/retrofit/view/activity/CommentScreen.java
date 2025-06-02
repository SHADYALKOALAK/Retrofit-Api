package com.shadyalkolak.retrofit.view.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.shadyalkolak.retrofit.R;
import com.shadyalkolak.retrofit.databinding.ActivityCommentScreenBinding;
import com.shadyalkolak.retrofit.models.CommentsModel;
import com.shadyalkolak.retrofit.network.ApiClient;
import com.shadyalkolak.retrofit.network.ApiService;
import com.shadyalkolak.retrofit.view.adapter.CommentsAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentScreen extends AppCompatActivity {
    private ActivityCommentScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommentScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int postId = getIntent().getIntExtra("POST_ID", 0);
        getAllComments(postId);
    }

    private void getAllComments(int postId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClient apiClient = retrofit.create(ApiClient.class);
        apiClient.getAllCommentsByPostId(postId).enqueue(new Callback<List<CommentsModel>>() {
            @Override
            public void onResponse(Call<List<CommentsModel>> call, Response<List<CommentsModel>> response) {
                if (response.isSuccessful()) {
                    List<CommentsModel> comments = response.body();
                    CommentsAdapter adapter = new CommentsAdapter(comments, CommentScreen.this);
                    binding.rcComment.setLayoutManager(new LinearLayoutManager(CommentScreen.this));
                    binding.rcComment.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<CommentsModel>> call, Throwable t) {
                Toast.makeText(CommentScreen.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}