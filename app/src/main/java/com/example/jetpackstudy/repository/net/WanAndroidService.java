package com.example.jetpackstudy.repository.net;

import com.example.jetpackstudy.repository.bean.WanAndroidBean;

import java.util.List;

import androidx.lifecycle.LiveData;
import retrofit2.http.GET;

/**
 * <p>@author : tangyanghai</p>
 * <p>@time : 2020/9/17</p>
 * <p>@for : </p>
 * <p></p>
 */
public interface WanAndroidService {
    @GET("wxarticle/chapters/json")
    LiveData<ApiResponse<List<WanAndroidBean>>> getChapters();
}
