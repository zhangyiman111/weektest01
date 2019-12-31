package com.bawei.zhangyiman20191231.view.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.zhangyiman20191231.R;
import com.bawei.zhangyiman20191231.base.BaseActivity;
import com.bawei.zhangyiman20191231.base.BasePresenter;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SeacondActivity extends BaseActivity {

    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.bt01)
    Button bt01;
    @BindView(R.id.bt02)
    Button bt02;
    @BindView(R.id.tv_sea)
    TextView tvSea;

    @Override
    protected void initData() {
        tvSea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = tvSea.getText().toString();
                if (!TextUtils.isEmpty(s)){
                    Bitmap image = CodeUtils.createImage(s, 600, 600, null);
                    iv.setImageBitmap(image);
                }
            }
        });
        iv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CodeUtils.analyzeByImageView(iv, new CodeUtils.AnalyzeCallback() {
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        Toast.makeText(SeacondActivity.this, result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnalyzeFailed() {
                        Toast.makeText(SeacondActivity.this, "解析失败", Toast.LENGTH_SHORT).show();
                    }
                });
                return true;
            }
        });
        bt01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post("微信");
            }
        });
        bt02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post("QQ");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void getWX(String  s){
        Toast.makeText(this, "成功接收"+s, Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void getQQ(String  s1){
        Toast.makeText(this, "成功接收"+s1, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected BasePresenter ProviderPresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_seacond;
    }

}
