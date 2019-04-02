package com.helloworld.library.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.helloworld.library.R;
import com.helloworld.library.base.CommonRecyclerAdapter;
import com.helloworld.library.base.RecyclerHolder;
import com.helloworld.library.utils.ScreenUtils;

import java.util.List;

/**
 * 创建时间：2019/3/29 16:19
 * 作者：Hyman峰
 * 功能描述：
 */
public class Myadapter extends CommonRecyclerAdapter<String> {

    private static final String TAG = "Myadapter";
    private Context mContext;

    private int itemSize = 15;
    private String itemColor = "#454545";
    private int leftPadding = 12;
    private int topPadding = 12;
    private int rightPadding = 12;
    private int bottomPadding = 12;
    private int itemGravity = Gravity.LEFT;

    public Myadapter(Context context, List<String> list) {
        super(context, R.layout.item_adapter, list);
        this.mContext = context;
    }

    @Override
    public void convert(RecyclerHolder holder, String item, int position) {
        TextView textView = holder.getView(R.id.item_title);
        //设置文本内容
        textView.setText(item);
        //字体大小
        textView.setTextSize(itemSize);
        //文字颜色
        textView.setTextColor(Color.parseColor(itemColor));
        //距离父布局文本内容
        //textView.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);
        textView.setPadding(ScreenUtils.dip2px(mContext, leftPadding),
                ScreenUtils.dip2px(mContext, topPadding),
                ScreenUtils.dip2px(mContext, rightPadding),
                ScreenUtils.dip2px(mContext, bottomPadding));
        //位置
        textView.setGravity(itemGravity);
    }

    /**
     * 字体大小
     */
    public Myadapter setItemSize(int size) {
        itemSize = size;
        return this;
    }

    /**
     * 字体颜色
     */
    public Myadapter setItemColor(String color) {
        itemColor = color;
        return this;
    }

    /**
     * 字体位置
     */
    public Myadapter setItemGravity(int gravity) {
        itemGravity = gravity;
        return this;
    }

    /**
     * 左、上、右、下边距
     */
    public Myadapter setItemPadding(int left, int top, int right, int bottom) {
        leftPadding = left;
        topPadding = top;
        rightPadding = right;
        bottomPadding = bottom;
        return this;
    }
}
