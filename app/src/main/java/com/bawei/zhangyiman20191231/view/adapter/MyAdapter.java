package com.bawei.zhangyiman20191231.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.zhangyiman20191231.R;
import com.bawei.zhangyiman20191231.model.bean.Bean;
import com.bawei.zhangyiman20191231.util.NetUtil;

import java.util.List;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *@auther:张奕漫
 *@Date: 2019/12/31
 *@Time:9:25
 *@Description:
 * */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Bean.RankingBean> ranking;

    public MyAdapter(List<Bean.RankingBean> ranking) {

        this.ranking = ranking;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(parent.getContext(), R.layout.item, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String name = ranking.get(position).getName();
        holder.tv01.setText(name);
        String rank = ranking.get(position).getRank();
        holder.tv02.setText(rank);
        NetUtil.getInstance().getPhoto(ranking.get(position).getAvatar(),holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItemListeren.onClickItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ranking.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.tv01)
        TextView tv01;
        @BindView(R.id.tv02)
        TextView tv02;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    onClickItemListeren onClickItemListeren;

    public void setOnClickItemListeren(MyAdapter.onClickItemListeren onClickItemListeren) {
        this.onClickItemListeren = onClickItemListeren;
    }

    public interface onClickItemListeren{
        void onClickItem(int i);
    }
}
