package com.shadyalkolak.retrofit.network;

import com.shadyalkolak.retrofit.models.AlbumsModel;
import com.shadyalkolak.retrofit.models.CommentsModel;
import com.shadyalkolak.retrofit.models.PhotosModel;
import com.shadyalkolak.retrofit.models.PostsModel;
import com.shadyalkolak.retrofit.models.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiClient {

    @GET(ApiService.END_POINT_USER)
    Call<List<UserModel>> getAllUsers();

    @GET(ApiService.END_POINT_ALBUM)
    Call<List<AlbumsModel>> getAllAlbums();

    @GET(ApiService.END_POINT_POST)
    Call<List<PostsModel>> getAllPosts();

    @GET(ApiService.END_POINT_COMMENTS)
    Call<List<CommentsModel>> getAllComments();

    @GET(ApiService.END_POINT_COMMENTS_BY_POST_ID)
    Call<List<CommentsModel>> getAllCommentsByPostId(@Path("id") int postId);

    @GET(ApiService.END_POINT_PHOTOS)
    Call<List<PhotosModel>> getAllPhotos();


}
