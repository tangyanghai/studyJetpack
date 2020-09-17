package com.example.jetpackstudy.repository.net;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * <p>@author : tangyanghai</p>
 * <p>@time : 2020/9/17</p>
 * <p>@for : </p>
 * <p></p>
 */
public interface WanAndroidService {
    @GET("wxarticle/chapters/json")
    LiveData<ApiResponse<String>> getChapters();

    @GET("wxarticle/chapters/json")
    Call<ApiResponse<String>> getChaptersNormal();

}
