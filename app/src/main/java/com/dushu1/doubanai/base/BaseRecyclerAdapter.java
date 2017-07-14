package com.dushu1.doubanai.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2017/7/7.
 */

public class BaseRecyclerAdapter<T,VH extends BaseViewHolder<T> > extends RecyclerView.Adapter<VH>{

    BaseViewHolder.BuilderItemViewHolder<VH> builderItemViewHolder;
    protected List<T> dataList;
    private int LayoutRes;


    public BaseRecyclerAdapter(BaseViewHolder.BuilderItemViewHolder<VH> builderItemViewHolder, List<T> data, int layoutRes) {
        setData(data);
        this.builderItemViewHolder = builderItemViewHolder;
        LayoutRes = layoutRes;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(LayoutRes,parent,false);
        return builderItemViewHolder.buildView(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        if (holder == null) return;
        holder.updateMsg(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setData(List<T> data) {
        dataList = data;
        notifyDataSetChanged();
    }

   /* private void setList(List<T> movies) {
        dataList =  checkNotNull(movies);
    }

    public void replaceData(List<T> data) {
        setList(data);
        notifyDataSetChanged();
    }*/


    public List<T> getData(){
        return dataList;
    }

}
