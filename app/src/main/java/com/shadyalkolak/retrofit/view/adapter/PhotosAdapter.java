package com.shadyalkolak.retrofit.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shadyalkolak.retrofit.databinding.PhotosRcBinding;
import com.shadyalkolak.retrofit.models.PhotosModel;

import java.util.List;

import retrofit2.http.Url;

public class PhotosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<PhotosModel> photos;
    private Context context;
    private PhotosRcBinding binding;

    public PhotosAdapter(List<PhotosModel> photos, Context context) {
        this.photos = photos;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhotosViewHolder(PhotosRcBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PhotosViewHolder photosViewHolder = (PhotosViewHolder) holder;
        String imageUrl = photos.get(position).getUrl();
        Uri imageUri = Uri.parse(imageUrl);
        photosViewHolder.binding.image.setImageURI(imageUri);


    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    private class PhotosViewHolder extends RecyclerView.ViewHolder {
        private PhotosRcBinding binding;

        public PhotosViewHolder(PhotosRcBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
