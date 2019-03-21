package com.helloworld.library.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/7.
 * recyclerview适配器
 */

public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerHolder> {

    private Context context;//上下文
    private List<T> list;//数据源
    private LayoutInflater inflater;//布局器
    private int itemLayoutId;//布局id
    private boolean isScrolling;//是否在滚动
    private OnItemClickListener listener;//点击事件监听器
    private OnItemLongClickListener longClickListener;//长按监听器
    private RecyclerView recyclerView;

    //在RecyclerView提供数据的时候调用
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }

    /**
     * 定义一个点击事件接口回调
     */
    public interface OnItemClickListener {
        void onItemClick(RecyclerView parent, View view, int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(RecyclerView parent, View view, int position);
    }

    /**
     * 插入一项
     *
     * @param item
     * @param position
     */
    public void insert(T item, int position) {
        list.add(position, item);
        notifyItemInserted(position);
    }

    public void addItem(T item) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(item);
        notifyDataSetChanged();
    }

    /**
     * 更新
     *
     * @param item
     */
    public void setList(List<T> item) {
        this.list = item;
        notifyDataSetChanged();
    }

    /**
     * 删除一项
     *
     * @param position 删除位置
     */
    public void delete(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemChanged(position);
        notifyDataSetChanged();
    }

    /**
     * 删除全部
     */
    public void deleteAll() {
        list.removeAll(list);
        notifyDataSetChanged();
    }

    public void clear(){
        if (list == null) {
            list = new ArrayList<T>();
        }
        list.clear();
        notifyDataSetChanged();
    }

    public CommonRecyclerAdapter(Context context, int itemLayoutId, List<T> list) {
        this.context = context;
        this.list = list;
        this.itemLayoutId = itemLayoutId;
        inflater = LayoutInflater.from(context);
        //        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
        //            @Override
        //            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        //                super.onScrollStateChanged(recyclerView, newState);
        //                isScrolling = !(newState == RecyclerView.SCROLL_STATE_IDLE);
        //                if (!isScrolling) {
        //                    notifyDataSetChanged();
        //                }
        //            }
        //        });
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(itemLayoutId, parent, false);
        return RecyclerHolder.getRecyclerHolder(context, view);
    }

    @Override
    public void onBindViewHolder(final RecyclerHolder holder, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null && view != null && recyclerView != null) {
                    int position = recyclerView.getChildAdapterPosition(view);
                    listener.onItemClick(recyclerView, view, position);
                }
            }
        });


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (longClickListener != null && view != null && recyclerView != null) {
                    int position = recyclerView.getChildAdapterPosition(view);
                    longClickListener.onItemLongClick(recyclerView, view, position);
                    return true;
                }
                return false;
            }
        });

        convert(holder, list.get(position), position);

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    /**
     * 填充RecyclerView适配器的方法，子类需要重写
     *
     * @param holder   ViewHolder
     * @param item     子项
     * @param position 位置
     */
    public abstract void convert(RecyclerHolder holder, T item, int position);
}
