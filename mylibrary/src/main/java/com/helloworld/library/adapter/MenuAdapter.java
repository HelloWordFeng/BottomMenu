package com.helloworld.library.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.helloworld.library.R;
import com.helloworld.library.base.CommonRecyclerAdapter;
import com.helloworld.library.base.RecyclerHolder;

import java.util.List;


/**
 * 创建时间：2019/3/20 19:47
 * 作者：Hyman峰
 * 功能描述：
 */
public class MenuAdapter extends CommonRecyclerAdapter<String> {

    private int titleSize;
    private int contentSize;
    private double lineHeight;
    private String titleColor;
    private String contentColor;
    private String lineColor;
    //资源shape
    private int sizeShape;
    private int topShape;
    private int middleShape;
    private int bottomShape;
    //是否有辩标题
    private boolean isShowTitle;

    public MenuAdapter(Context context, List<String> list, boolean isShowTitle) {
        super(context, R.layout.item_menu, list);
        this.isShowTitle = isShowTitle;
    }

    @Override
    public void convert(RecyclerHolder holder, String item, int position) {

        TextView textView = holder.getView(R.id.menu_item);
        View viewLine = holder.getView(R.id.view_line);
        textView.setText(item);

        //只有一个选项4边都要圆角
        if (getItemCount() == 1) {
            textView.setBackgroundResource(sizeShape);
        } else {
            //将第一个position上面左右圆角设置
            if (position == 0) {
                textView.setBackgroundResource(topShape);
                if (isShowTitle){
                    textView.setTextColor(Color.parseColor(titleColor));
                    textView.setTextSize(titleSize);
                }else {
                    textView.setTextColor(Color.parseColor(contentColor));
                }
                //中间选项不需要圆角
            } else if (position < getItemCount() - 1) {
                textView.setBackgroundResource(middleShape);
                textView.setTextColor(Color.parseColor(contentColor));
                textView.setTextSize(contentSize);
            } else {
                //将底部也就是第一个position下面面左右圆角设置
                textView.setBackgroundResource(bottomShape);
                textView.setTextColor(Color.parseColor(contentColor));
                textView.setTextSize(contentSize);
            }
            //分割线
            if (position == getItemCount() - 1) {
                viewLine.setVisibility(View.GONE);
            } else {
                viewLine.setVisibility(View.VISIBLE);
            }
            //设置分割线高度
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, (int) lineHeight);
            viewLine.setLayoutParams(params);
            //设置颜色
            viewLine.setBackgroundColor(Color.parseColor(lineColor));
        }
    }

    /**
     * 字体大小
     */
    public MenuAdapter setTitleSize(int size) {
        titleSize = size;
        return this;
    }

    /**
     * 字体颜色
     */
    public MenuAdapter setTitleColor(String color) {
        titleColor = color;
        return this;
    }

    /**
     * 内容大小
     */
    public MenuAdapter setContentSize(int size) {
        contentSize = size;
        return this;
    }

    /**
     * 内容颜色
     */
    public MenuAdapter setContentColor(String color) {
        contentColor = color;
        return this;
    }

    /**
     * 分割线高度
     */
    public MenuAdapter setLineHeight(double size) {
        lineHeight = size;
        return this;
    }

    /**
     * 分割线颜色
     */
    public MenuAdapter setLineColor(String color) {
        lineColor = color;
        return this;
    }

    /**
     * 背景颜色(list大小为1时)
     */
    public MenuAdapter setSizeOneShape(int resId) {
        sizeShape = resId;
        return this;
    }

    public MenuAdapter setTopShape(int resId) {
        topShape = resId;
        return this;
    }

    public MenuAdapter setMiddleShape(int resId) {
        middleShape = resId;
        return this;
    }

    public MenuAdapter setBottomShape(int resId) {
        bottomShape = resId;
        return this;
    }
}
