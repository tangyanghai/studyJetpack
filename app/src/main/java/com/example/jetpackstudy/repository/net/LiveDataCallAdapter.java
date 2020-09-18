package com.example.jetpackstudy.repository.net;

import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * <p>@author : tangyanghai</p>
 * <p>@time : 2020/9/17</p>
 * <p>@for : </p>
 * <p></p>
 */
public class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<R>> {

    private Type responseType;

    public LiveDataCallAdapter(Type responseType) {
        this.responseType = responseType;
    }

    @Override
    public Type responseType() {
        return responseType;
    }

    @Override
    public LiveData<R> adapt(Call<R> call) {
        return new LiveData<R>() {
            AtomicBoolean started = new AtomicBoolean(false);

            @Override
            protected void onActive() {
                super.onActive();
                if (started.compareAndSet(false, true)) {
                    call.enqueue(new Callback<R>() {
                        @Override
                        public void onResponse(Call<R> call1, Response<R> response) {
                            postValue(response.body());
                        }

                        @Override
                        public void onFailure(Call<R> call1, Throwable t) {
                            postValue((R) new ApiResponse(null, -1, t.getMessage() == null ? "" : t.getMessage()));
                        }
                    });
                }
            }
        };
    }
}
