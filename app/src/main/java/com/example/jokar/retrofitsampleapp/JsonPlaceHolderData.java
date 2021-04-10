package com.example.jokar.retrofitsampleapp;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JsonPlaceHolderData {

    @GET("posts")
    Call<List<Post>> getPost();

    //this method returns same this as above @query for ?
    //Call<List<Post>> getPost(@Query("userId"),int id)  this means that (posts?userId=id)

    @GET("posts/{id}/comments")
    Call<List<Comment>>  getComment(@Path("id") int id);


    @POST("posts")
    Call<Post> createPost(@Body Post post);



    //Same as Above but here we pass our values into the  method and retrofit create Object for that automatically

//    @FormUrlEncoded
//    @POST("posts")
//    Call<Post> createPost(
//            @Field("userId") int userId,
//            @Field("title") String titll,
//            @Field("body") String text
//    );



   //Same as above but here we pass Map<k,V> when we call this method then retrofit convert it automatically
//    @FormUrlEncoded
//    @POST("posts")
//    Call<Post>  createPost(@FieldMap Map<String, String>  fields);



    //it update all fields with the given id
    @PUT("posts/{id}")
    Call<Post> putPost(@Path("id") int id, @Body Post post);


    //it update only the provided in the  body....
    @PATCH("posts/{id}")
    Call<Post> patchPost(@Path("id") int id, @Body Post post);

    //method to delete an post of having id
    @DELETE("posts/{id}")
    Call<Void>  deletePost(@Path("id") int id);
}
