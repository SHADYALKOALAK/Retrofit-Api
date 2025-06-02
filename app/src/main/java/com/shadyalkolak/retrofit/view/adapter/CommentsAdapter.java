package com.shadyalkolak.retrofit.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shadyalkolak.retrofit.databinding.CommentsRcBinding;
import com.shadyalkolak.retrofit.databinding.PostsRcBinding;
import com.shadyalkolak.retrofit.models.CommentsModel;
import com.shadyalkolak.retrofit.models.PostsModel;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private CommentsRcBinding binding;
    private List<CommentsModel> comments;
    private Context context;

    public CommentsAdapter(List<CommentsModel> comments, Context context) {
        this.comments = comments;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = CommentsRcBinding.inflate(LayoutInflater.from(context), parent, false);
        return new CommentsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CommentsViewHolder postsViewHolder = (CommentsViewHolder) holder;
        postsViewHolder.binding.tvName.setText(comments.get(position).getName());
        postsViewHolder.binding.tvBody.setText(comments.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    private class CommentsViewHolder extends RecyclerView.ViewHolder {
        private CommentsRcBinding binding;

        public CommentsViewHolder(CommentsRcBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
