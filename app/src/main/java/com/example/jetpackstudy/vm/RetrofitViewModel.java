package com.example.jetpackstudy.vm;

import com.example.jetpackstudy.repository.RetrofitRepository;
import com.example.jetpackstudy.repository.bean.WanAndroidBean;
import com.example.jetpackstudy.repository.net.ApiResponse;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

/**
 * <p>@author : tangyanghai</p>
 * <p>@time : 2020/9/17</p>
 * <p>@for : </p>
 * <p></p>
 */
public class RetrofitViewModel extends ViewModel {

    RetrofitRepository repository;
    MutableLiveData<ApiResponse<List<WanAndroidBean>>> data;

    public RetrofitViewModel() {
        repository = new RetrofitRepository();
        data = new MutableLiveData<>();
    }

    public LiveData<ApiResponse<List<WanAndroidBean>>> getResponse() {
        return data;
    }

    public void loadData(LifecycleOwner owner) {
        LiveData<ApiResponse<List<WanAndroidBean>>> apiResponseLiveData = repository.loadData();
        apiResponseLiveData.observe(owner, new Observer<ApiResponse<List<WanAndroidBean>>>() {
            @Override
            public void onChanged(ApiResponse<List<WanAndroidBean>> listApiResponse) {
                data.setValue(apiResponseLiveData.getValue());
            }
        });
    }
}
