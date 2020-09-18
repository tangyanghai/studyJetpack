package com.example.jetpackstudy.repository;

import com.example.jetpackstudy.repository.net.ApiResponse;
import com.example.jetpackstudy.repository.net.Net;
import com.example.jetpackstudy.repository.net.WanAndroidService;

import androidx.lifecycle.LiveData;

/**
 * <p>@author : tangyanghai</p>
 * <p>@time : 2020/9/18</p>
 * <p>@for : </p>
 * <p></p>
 */
public class RetrofitRepository {
    public LiveData<ApiResponse<String>> loadData(){
        return Net.getService(WanAndroidService.class).getChapters();
    }
}
