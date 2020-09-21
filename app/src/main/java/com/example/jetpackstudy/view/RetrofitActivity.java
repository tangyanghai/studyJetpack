package com.example.jetpackstudy.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jetpackstudy.R;
import com.example.jetpackstudy.repository.bean.WanAndroidBean;
import com.example.jetpackstudy.repository.net.ApiResponse;
import com.example.jetpackstudy.vm.RetrofitViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * <p>@author : tangyanghai</p>
 * <p>@time : 2020/9/17</p>
 * <p>@for : </p>
 * <p></p>
 */
public class RetrofitActivity extends BaseActivity {
    RetrofitViewModel model;
    private Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        RecyclerView rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this);
        rv.setAdapter(adapter);
        findViewById(R.id.bt_load_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.loadData(RetrofitActivity.this);
            }
        });
    }

    @Override
    protected void initViewModels() {
        model = createViewModel(RetrofitViewModel.class);
        model.getResponse().observe(RetrofitActivity.this, new Observer<ApiResponse<List<WanAndroidBean>>>() {
            @Override
            public void onChanged(ApiResponse<List<WanAndroidBean>> listApiResponse) {
                if (listApiResponse.data == null) {
                    Toast.makeText(RetrofitActivity.this, listApiResponse.errorMsg, Toast.LENGTH_LONG).show();
                    return;
                }
                adapter.setList(listApiResponse.data);
            }
        });
        model.getFirstLoad().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean first) {
                if (first) {
                    model.setFirstLoading(false);
                    model.loadData(RetrofitActivity.this);
                }
            }
        });
    }

    class Adapter extends RecyclerView.Adapter<VH> {

        public Adapter(Context con) {
            mInflater = LayoutInflater.from(con);
        }

        private LayoutInflater mInflater;
        private List<WanAndroidBean> mList;

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new VH(mInflater.inflate(R.layout.item_rv_wan_android, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, int position) {
            TextView tv = holder.itemView.findViewById(R.id.tv);
            tv.setText(mList.get(position).getName());
        }

        @Override
        public int getItemCount() {
            return mList == null ? 0 : mList.size();
        }

        public void setList(List<WanAndroidBean> list) {
            mList = list;
            notifyDataSetChanged();
        }
    }

    class VH extends RecyclerView.ViewHolder {

        public VH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
