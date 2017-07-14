package com.dushu1.doubanai.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * Created by Administrator on 2017/7/8.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

   protected T itemContent;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public void updateMsg(T itemContent){
       this.itemContent = itemContent;
       bindView(itemContent);
    }


    protected abstract void bindView(T itemContent);


    /**
     * 因为不知道返回的Holder为什么类型，所以用返回接口的形式
     * @param <VH>
     */

    public interface BuilderItemViewHolder<VH> {
          VH  buildView(View itemView);
    }

}
