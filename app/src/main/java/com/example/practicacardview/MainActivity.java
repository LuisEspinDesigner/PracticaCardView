package com.example.practicacardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;


import com.example.practicacardview.Adapter.AdaptadorRevista;
import com.example.practicacardview.retrofill.Irevistas;
import com.example.practicacardview.retrofill.Revista;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.RViewrevistas);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RetroRevistas(this,"2");

    }
    public  void RetroRevistas(Context ctx,String id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://revistas.uteq.edu.ec/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        Irevistas revist = retrofit.create(Irevistas.class);
        Call <List<Revista>> llamado= revist.RetroRevistas(id);
        llamado.enqueue(new Callback<List<Revista>>() {
            @Override
            public void onResponse(Call<List<Revista>> call, Response<List<Revista>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Revista> lista = response.body();
                /*JSONArray jsonArray = new JSONArray();
                for (Revista Revista: lista){
                    try {
                        JSONObject obj = new JSONObject();
                        obj.put("issue_id",Revista.getIssue_id());
                        obj.put("volume",Revista.getVolume());
                        obj.put("number",Revista.getNumber());
                        obj.put("year",Revista.getYear());
                        obj.put("date_published",Revista.getDate_published());
                        obj.put("title",Revista.getTitle());
                        obj.put("doi",Revista.getDoi());
                        obj.put("cover",Revista.getCover());
                        jsonArray.put(obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }*/
                AdaptadorRevista adaptadorRevista = new AdaptadorRevista(ctx,lista);
                recyclerView.setAdapter(adaptadorRevista);
            }
            @Override
            public void onFailure(Call<List<Revista>> call, Throwable t) {

            }
        });
    }
}