package com.mllwf.mvpdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ML on 2017/4/19.
 * 写一个Adapter的基类，减少代码的复用
 */

public abstract class KBaseAdapter<T> extends BaseAdapter {

    protected List<T> mList = new ArrayList<>();
    protected Context context;
    protected LayoutInflater inflater;


    public KBaseAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    /**
     * 判断数据是否为空
     *
     * @return 为空返回true，不为空返回false
     */
    public boolean isEmpty() {
        return mList.isEmpty();
    }

    public void addItems(List<T> itemList) {
        this.mList.addAll(itemList);
        notifyDataSetChanged();
    }

    public void setItems(List<T> itemList) {
        mList.clear();
        this.mList = itemList;
        notifyDataSetChanged();
    }

    /**
     * 清空数据
     */
    public void clearItems() {
        mList.clear();
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);
}
