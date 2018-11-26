package com.hossain.webapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.hossain.webapi.WebApi.Api_response;
import com.hossain.webapi.WebApi.Api_service;
import com.hossain.webapi.WebApi.Example;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView nameTv,emailTv;
    private Api_service service;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameTv = findViewById(R.id.name);
        emailTv = findViewById(R.id.email);

        service = Api_response.getUser().create(Api_service.class);
        Call<Example> response = service.getAllUser();
        response.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(response.code() == 200) {
                    Example userResponse = response.body();
                    nameTv.setText(userResponse.getResults().get(0).getName().getFirst());
                    emailTv.setText(userResponse.getResults().get(0).getEmail());
                    Picasso.get().load(userResponse.getResults().get(0).getPicture().getMedium());

                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }
}
