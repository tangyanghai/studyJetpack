package com.example.jetpackstudy.view;

import android.os.Bundle;

import com.example.jetpackstudy.vm.BaseViewModel;
import com.example.jetpackstudy.vm.LoadingObserver;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * <p>@author : tangyanghai</p>
 * <p>@time : 2020/9/18</p>
 * <p>@for : </p>
 * <p></p>
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected MutableLiveData<Boolean> mLoadingVM = new MutableLiveData<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoadingVM.observe(this, initLoadingObserver());
        initViewModels();
    }

    protected abstract void initViewModels();

    private Observer<Boolean> initLoadingObserver() {
        return new LoadingObserver(this);
    }

    protected void attachLoadingData(BaseViewModel model) {
        if (model != null) {
            model.attachLoadingData(mLoadingVM);
        } else {
            throw new RuntimeException(getClass().getName() + "的model = null,不能附加弹窗View");
        }
    }

    protected <T extends ViewModel> T createViewModel(Class<T> clz) {
        T t = new ViewModelProvider(this).get(clz);
        if (t instanceof BaseViewModel) {
            attachLoadingData((BaseViewModel) t);
        }
        return t;
    }
}
