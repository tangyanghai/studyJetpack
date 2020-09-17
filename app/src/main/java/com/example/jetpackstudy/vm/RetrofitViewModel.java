package com.example.jetpackstudy.vm;

import com.example.jetpackstudy.repository.net.ApiResponse;
import com.example.jetpackstudy.repository.net.Net;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

/**
 * <p>@author : tangyanghai</p>
 * <p>@time : 2020/9/17</p>
 * <p>@for : </p>
 * <p></p>
 */
public class RetrofitViewModel extends ViewModel {

    public LiveData<ApiResponse<String>> getResponse(){
        return new Net().getChapters();
    }

}
