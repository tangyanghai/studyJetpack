package com.example.jetpackstudy.repository.net;

import com.example.jetpackstudy.repository.bean.WanAndroidBean;

import java.util.List;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

import static com.example.jetpackstudy.repository.net.Urls.BASE_URL;

/**
 * <p>@author : tangyanghai</p>
 * <p>@time : 2020/9/17</p>
 * <p>@for : </p>
 * <p></p>
 */
public class Net {
    // 第2部分：在创建Retrofit实例时通过.baseUrl()设置
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL) //设置网络请求的Url地址
            .addConverterFactory(FastJsonConverterFactory.create()) //设置数据解析器
            .addCallAdapterFactory(new LiveDataCallAdapterFactory())//支持LiveData
            .build();

    public static <T> T getService(Class<T> clz, String baseUrl) {
        Retrofit rf = getRetrofitByBaseUrl(baseUrl);
        return rf.create(clz);
    }

    public static <T> T getService(Class<T> clz) {
        return getService(clz, BASE_URL);
    }

    private static Retrofit getRetrofitByBaseUrl(String baseUrl) {
        if (baseUrl == null) {
            baseUrl = BASE_URL;
        }
        return new Retrofit.Builder()
                .baseUrl(baseUrl) //设置网络请求的Url地址
                .addConverterFactory(FastJsonConverterFactory.create()) //设置数据解析器
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())//支持LiveData
                .build();
    }
}
