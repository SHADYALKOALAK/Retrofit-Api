package com.shadyalkolak.retrofit.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shadyalkolak.retrofit.databinding.CardUserRcBinding;
import com.shadyalkolak.retrofit.models.UserModel;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<UserModel> users;
    private CardUserRcBinding binding;
    private OnItemClickListener listener;

    public UserAdapter(Context context, List<UserModel> users, OnItemClickListener listener) {
        this.context = context;
        this.users = users;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = CardUserRcBinding.inflate(LayoutInflater.from(context), parent, false);
        return new UserViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UserViewHolder userViewHolder = (UserViewHolder) holder;
        userViewHolder.binding.tvName.setText(users.get(position).getName());
        userViewHolder.binding.tvCompanyName.setText(users.get(position).getCompany().getName());
        userViewHolder.binding.tvAddress.setText(users.get(position).getAddress().getCity() + ", "
                + users.get(position).getAddress().getStreet() + ", " + users.get(position).getAddress().getZipcode());

        userViewHolder.itemView.setOnClickListener(v ->
                listener.onItemClick(users.get(position), position, OnItemClickListener.ClickType.ITEM)
        );

        userViewHolder.binding.icWebsite.setOnClickListener(v ->
                listener.onItemClick(users.get(position), position, OnItemClickListener.ClickType.WEBSITE)
        );

        userViewHolder.binding.icCall.setOnClickListener(v ->
                listener.onItemClick(users.get(position), position, OnItemClickListener.ClickType.CALL)
        );

        userViewHolder.binding.icLocation.setOnClickListener(v ->
                listener.onItemClick(users.get(position), position, OnItemClickListener.ClickType.LOCATION)
        );
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    private static class UserViewHolder extends RecyclerView.ViewHolder {
        CardUserRcBinding binding;

        public UserViewHolder(CardUserRcBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(UserModel user, int position, ClickType clickType);

        enum ClickType {
            ITEM,
            WEBSITE,
            CALL,
            LOCATION
        }
    }
}
