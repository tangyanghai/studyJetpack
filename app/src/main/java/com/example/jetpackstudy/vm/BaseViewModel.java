package com.example.jetpackstudy.vm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * <p>@author : tangyanghai</p>
 * <p>@time : 2020/9/18</p>
 * <p>@for : </p>
 * <p></p>
 */
public class BaseViewModel extends ViewModel {
    private MutableLiveData<Boolean> showLoading;
    private MutableLiveData<Boolean> firstLoad = new MutableLiveData<>(true);

    public BaseViewModel() {

    }

    public MutableLiveData<Boolean> getFirstLoad(){
        return firstLoad;
    }

    public void setFirstLoading(boolean b) {
        firstLoad.setValue(b);
    }

    public void attachLoadingData(MutableLiveData<Boolean> showLoading) {
        this.showLoading = showLoading;
    }

    /**
     * @param show true=显示加载中弹窗
     */
    public void showLoading(boolean show) {
        if (showLoading != null) {
            showLoading.setValue(show);
        }
    }
}
