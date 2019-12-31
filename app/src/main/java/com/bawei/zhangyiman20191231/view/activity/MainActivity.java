package com.bawei.zhangyiman20191231.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.zhangyiman20191231.R;
import com.bawei.zhangyiman20191231.base.BaseActivity;
import com.bawei.zhangyiman20191231.contract.IHomeContract;
import com.bawei.zhangyiman20191231.model.bean.Bean;
import com.bawei.zhangyiman20191231.presenter.HomePresenter;
import com.bawei.zhangyiman20191231.view.adapter.MyAdapter;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<HomePresenter> implements IHomeContract.IView {

    @BindView(R.id.rl)
    RecyclerView rl;
    @BindView(R.id.tv_main)
    TextView tvMain;

    @Override
    protected void initData() {
        mPresenter.getHomeData();
        tvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SeacondActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected HomePresenter ProviderPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onSuccess(Bean bean) {
        List<Bean.RankingBean> ranking = bean.getRanking();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rl.setLayoutManager(linearLayoutManager);
        MyAdapter myAdapter = new MyAdapter(ranking);
        rl.setAdapter(myAdapter);
        myAdapter.setOnClickItemListeren(new MyAdapter.onClickItemListeren() {
            @Override
            public void onClickItem(int i) {
                Toast.makeText(MainActivity.this, "成功点击第" + i + "条", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onError(Throwable throwable) {
        Toast.makeText(this, "没有网络", Toast.LENGTH_SHORT).show();

    }


}
