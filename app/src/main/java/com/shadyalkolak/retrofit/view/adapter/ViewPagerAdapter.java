package com.shadyalkolak.retrofit.view.adapter;

import android.view.FrameStats;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.shadyalkolak.retrofit.view.fragment.AlbumsFragment;
import com.shadyalkolak.retrofit.view.fragment.PostsFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1) {
            return new PostsFragment();
        }
        return new AlbumsFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

