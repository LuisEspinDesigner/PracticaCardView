package com.example.practicacardview.retrofill;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface Irevistas {
    @GET("ws/issues.php")
    Call<List<Revista>> RetroRevistas(@Query("j_id") String id);
}
