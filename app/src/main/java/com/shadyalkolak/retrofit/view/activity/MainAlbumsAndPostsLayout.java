package com.shadyalkolak.retrofit.view.activity;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.shadyalkolak.retrofit.R;
import com.shadyalkolak.retrofit.databinding.ActivityMainAlbumsAndPostsLayoutBinding;
import com.shadyalkolak.retrofit.view.adapter.ViewPagerAdapter;

public class MainAlbumsAndPostsLayout extends AppCompatActivity {

    private ActivityMainAlbumsAndPostsLayoutBinding binding;
    private final Context context = MainAlbumsAndPostsLayout.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainAlbumsAndPostsLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupTabsAndViewPager();

    }

    private void setupTabsAndViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        binding.viewPager.setAdapter(adapter);

        new TabLayoutMediator(binding.tabs, binding.viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Albums");
                    break;
                case 1:
                    tab.setText("Posts");
                    break;
            }
        }).attach();
    }

}