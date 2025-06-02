package com.shadyalkolak.retrofit.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shadyalkolak.retrofit.databinding.AlbumsRcBinding;
import com.shadyalkolak.retrofit.databinding.PostsRcBinding;
import com.shadyalkolak.retrofit.models.AlbumsModel;
import com.shadyalkolak.retrofit.models.PostsModel;
import com.shadyalkolak.retrofit.view.activity.CommentScreen;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private PostsRcBinding binding;
    private List<PostsModel> posts;
    private Context context;

    public PostsAdapter(List<PostsModel> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = PostsRcBinding.inflate(LayoutInflater.from(context), parent, false);
        return new PostsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        PostsViewHolder postsViewHolder = (PostsViewHolder) holder;
        postsViewHolder.binding.tvTitle.setText(posts.get(position).getTitle());
        postsViewHolder.binding.tvBody.setText(posts.get(position).getBody());

        postsViewHolder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CommentScreen.class);
                intent.putExtra("POST_ID", posts.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    private class PostsViewHolder extends RecyclerView.ViewHolder {
        private PostsRcBinding binding;

        public PostsViewHolder(PostsRcBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
