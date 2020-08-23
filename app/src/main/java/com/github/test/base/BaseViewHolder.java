package com.github.test.base;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    public BaseViewHolder(View view) {
        super(view);
    }

    public abstract void onBind(T data);

}
