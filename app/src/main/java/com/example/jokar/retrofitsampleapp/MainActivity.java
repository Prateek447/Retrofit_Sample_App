package com.example.jokar.retrofitsampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Button button;

    JsonPlaceHolderData jsonPlaceHolderData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_view);
        editText =  findViewById(R.id.edit_text_id);

        button = findViewById(R.id.button);


         // to see which fields we provide null values in the json response
        Gson gson =   new GsonBuilder().serializeNulls().create();


        HttpLoggingInterceptor loggingInterceptor  = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient =  new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(gson)).client(okHttpClient).build();

           jsonPlaceHolderData =  retrofit.create(JsonPlaceHolderData.class);



          putPost();

       // deletePost();


            //createPost();
//
//           button.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//
//                   getComments(Integer.parseInt(editText.getText().toString()));
//               }
//           });

        //getPosts();
    }

    private void getPosts() {
        Call<List<Post>> call  =  jsonPlaceHolderData.getPost();

        //get an response on the background thread and enqueue() create it automatically
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if (!response.isSuccessful()){
                    textView.setText("Error Code " + response.code());
                    return;
                }

                List<Post>  list = response.body();

                for (Post post : list){

                    textView.append(post.toString()+"\n\n");

                }


            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textView.setText(t.toString());
            }
        });
    }

    private void getComments(int id){


        Call<List<Comment>> list =  jsonPlaceHolderData.getComment(id);

        list.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                if (!response.isSuccessful()){
                    textView.setText("Error Code " + response.code());
                    return;
                }

                List<Comment>  list = response.body();

                for (Comment comment : list){

                    textView.append(comment.toString()+"\n\n");

                }

            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                  textView.setText("Something Went Wrong");
            }
        });
    }

    private void createPost(){

        Post  post   =  new Post(8,"Jordan","This is body like every body");


        Call<Post>  call =  jsonPlaceHolderData.createPost(post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if (!response.isSuccessful()){
                    textView.setText("Error Code " + response.code());
                    return;
                }

                Post  responsePost =   response.body();
                textView.setText(responsePost.toString());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textView.setText(t.toString());
            }
        });
    }


    private  void putPost(){

        Post post  = new Post(2,null,"body");

        Call<Post>  call =   jsonPlaceHolderData.putPost(5,post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if (!response.isSuccessful()){
                    textView.setText("Error Code " + response.code());
                    return;
                }

                Post  responsePost =   response.body();
                textView.setText(responsePost.toString());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textView.setText(t.toString());
            }
        });

    }

    private void deletePost(){

       Call<Void>  call =  jsonPlaceHolderData.deletePost(2);
             call.enqueue(new Callback<Void>() {
                 @Override
                 public void onResponse(Call<Void> call, Response<Void> response) {

//                     if (!response.isSuccessful()){
//                         textView.setText("Error " + response.code());
//                         return;
//                     }

                     textView.setText(String.valueOf(response.code()));
                 }

                 @Override
                 public void onFailure(Call<Void> call, Throwable t) {
                        // textView.setText(t.toString());
                 }
             });
    }


}
