package com.shadyalkolak.retrofit.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.shadyalkolak.retrofit.databinding.ActivityUsersScreenBinding;
import com.shadyalkolak.retrofit.models.UserModel;
import com.shadyalkolak.retrofit.network.ApiClient;
import com.shadyalkolak.retrofit.network.ApiService;
import com.shadyalkolak.retrofit.view.adapter.UserAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsersScreen extends AppCompatActivity implements UserAdapter.OnItemClickListener {

    private ActivityUsersScreenBinding binding;
    private final Context context = UsersScreen.this;
    private List<UserModel> users;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUsersScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showProgressDialog();
        getUsers();
    }

    private void getUsers() {
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClient apiClient = retrofit.create(ApiClient.class);
        apiClient.getAllUsers().enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    users = response.body();
                    UserAdapter adapter = new UserAdapter(context, users, UsersScreen.this);
                    binding.rvUsers.setLayoutManager(new LinearLayoutManager(context));
                    binding.rvUsers.setAdapter(adapter);
                } else {
                    Toast.makeText(context, "لم يتم تحميل البيانات بنجاح", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "حدث خطأ: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(UserModel user, int position, ClickType clickType) {
        Intent intent;
        switch (clickType) {
            case ITEM:
                Toast.makeText(context, "تم الضغط على العنصر", Toast.LENGTH_SHORT).show();
                break;
            case WEBSITE:
                String url = "https://" + user.getWebsite();
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                context.startActivity(intent);
                break;
            case CALL:
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + user.getPhone()));
                context.startActivity(intent);
                break;
            case LOCATION:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:" + user.getAddress().getGeo().getLat() + "," + user.getAddress().getGeo().getLng()));
                context.startActivity(intent);
                break;
        }
    }

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("جاري تحميل البيانات...");
        progressDialog.setCancelable(false);
    }
}
