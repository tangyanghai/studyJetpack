package com.example.jetpackstudy.repository.net;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * <p>@author : tangyanghai</p>
 * <p>@time : 2020/9/17</p>
 * <p>@for : </p>
 * <p></p>
 */
public class Net {
    // 第2部分：在创建Retrofit实例时通过.baseUrl()设置
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Urls.BASE_URL) //设置网络请求的Url地址
            .addConverterFactory(FastJsonConverterFactory.create()) //设置数据解析器
            .addCallAdapterFactory(new LiveDataCallAdapterFactory())//支持LiveData
            .build();

    public LiveData<ApiResponse<String>> getChapters() {
        return retrofit.create(WanAndroidService.class).getChapters();
    }

    public Call<ApiResponse<String>> getChaptersNormal() {
        return retrofit.create(WanAndroidService.class).getChaptersNormal();
    }

}
