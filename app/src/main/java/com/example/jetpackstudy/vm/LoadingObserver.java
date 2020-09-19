package com.example.jetpackstudy.vm;

import android.app.Activity;
import android.util.Log;

/**
 * <p>@author : tangyanghai</p>
 * <p>@time : 2020/9/18</p>
 * <p>@for : </p>
 * <p></p>
 */
public class LoadingObserver implements androidx.lifecycle.Observer<Boolean> {
    Activity context;

    public LoadingObserver(Activity context) {
        this.context = context;
    }

    @Override
    public void onChanged(Boolean t) {
        if (t) {
            Log.e("===","开始加载了...");
        }else {
            Log.e("===","加载结束了...");
        }
    }
}
