package com.example.jetpackstudy.vm;

import com.example.jetpackstudy.repository.RetrofitRepository;
import com.example.jetpackstudy.repository.bean.WanAndroidBean;
import com.example.jetpackstudy.repository.net.ApiResponse;

import java.util.List;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

/**
 * <p>@author : tangyanghai</p>
 * <p>@time : 2020/9/17</p>
 * <p>@for : </p>
 * <p></p>
 */
public class RetrofitViewModel extends BaseViewModel {

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
        setFirstLoading(false);
        showLoading(true);
        repository.loadData().observe(owner, new Observer<ApiResponse<List<WanAndroidBean>>>() {
            @Override
            public void onChanged(ApiResponse<List<WanAndroidBean>> listApiResponse) {
                data.setValue(listApiResponse);
                showLoading(false);
            }
        });
    }


}
