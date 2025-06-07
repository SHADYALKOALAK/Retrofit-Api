package com.shadyalkolak.retrofit.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shadyalkolak.retrofit.databinding.AlbumsRcBinding;
import com.shadyalkolak.retrofit.models.AlbumsModel;
import com.shadyalkolak.retrofit.view.activity.PhotosScreen;

import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private AlbumsRcBinding binding;
    private List<AlbumsModel> albums;
    private Context context;

    public AlbumsAdapter(List<AlbumsModel> albums, Context context) {
        this.albums = albums;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = AlbumsRcBinding.inflate(LayoutInflater.from(context), parent, false);
        return new AlbumsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AlbumsViewHolder albumsViewHolder = (AlbumsViewHolder) holder;
        albumsViewHolder.binding.tvAlbums.setText(albums.get(position).getTitle());
        albumsViewHolder.binding.getRoot().setOnClickListener(v -> {
            Intent intent = new Intent(context, PhotosScreen.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    private class AlbumsViewHolder extends RecyclerView.ViewHolder {
        private AlbumsRcBinding binding;

        public AlbumsViewHolder(AlbumsRcBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
