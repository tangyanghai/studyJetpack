package com.example.jetpackstudy.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.jetpackstudy.R;
import com.example.jetpackstudy.repository.net.ApiResponse;
import com.example.jetpackstudy.vm.RetrofitViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

/**
 * <p>@author : tangyanghai</p>
 * <p>@time : 2020/9/17</p>
 * <p>@for : </p>
 * <p></p>
 */
public class RetrofitActivity extends AppCompatActivity {
    RetrofitViewModel model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        TextView tv = findViewById(R.id.tv_content);

        model = new ViewModelProvider(this).get(RetrofitViewModel.class);
        findViewById(R.id.bt_load_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.getResponse().observe(RetrofitActivity.this, new Observer<ApiResponse<String>>() {
                    @Override
                    public void onChanged(ApiResponse<String> response) {
                        tv.setText(response.body);
                    }
                });
            }
        });
    }
}
