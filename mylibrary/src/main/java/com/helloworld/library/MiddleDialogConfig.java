package com.helloworld.library;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.helloworld.library.adapter.Myadapter;
import com.helloworld.library.base.CommonRecyclerAdapter;
import com.helloworld.library.utils.DialogEnum;
import com.helloworld.library.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间：2019/3/28 18:03
 * 作者：Hyman峰
 * 功能描述：常见的弹出框
 */
public class MiddleDialogConfig {

    private static final String TAG = "MiddleDialogConfig";
    private Context mContext;
    private Dialog dialog;
    //背景
    private LinearLayout middleLinear;
    //标题文字
    private TextView middleTitle;
    //提示内容
    private TextView middleContent;
    //取消文字
    private TextView middleCancel;
    //确定文字
    private TextView middleDetermine;
    //分割线
    private View middleLine, middleWidthLine;
    //输入框
    private EditText middleEdit;
    //弹框框高
    private WindowManager.LayoutParams params;
    //样式
    private DialogEnum dialogEnum = DialogEnum.BASIC;
    //提示文字
    private String textHint = "请输入文本内容";

    private RecyclerView middleRecycler;
    private Myadapter adapter;
    //数据源
    private List<String> mDatas = new ArrayList<>();

    public MiddleDialogConfig builder(Context mContext) {
        this.mContext = mContext;
        // 获取Dialog布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_middle, null);
        // 获取自定义Dialog布局中的控件
        middleLinear = view.findViewById(R.id.middle_linear);
        middleTitle = view.findViewById(R.id.middle_title);
        middleContent = view.findViewById(R.id.middle_content);
        middleCancel = view.findViewById(R.id.middle_cancel);
        middleDetermine = view.findViewById(R.id.middle_determine);
        middleLine = view.findViewById(R.id.middle_line);
        middleWidthLine = view.findViewById(R.id.middle_width_line);
        middleEdit = view.findViewById(R.id.middle_edit);
        middleRecycler = view.findViewById(R.id.middle_recycler);
        //点击事件
        initClick();
        //初始化适配器
        initAdapter();
        // 定义Dialog布局和参数
        dialog = new Dialog(mContext, R.style.AlertDialogStyle);
        //点击外部是否取消
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        //设置View布局
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        params = window.getAttributes();
        window.setAttributes(params);
        return this;
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        adapter = new Myadapter(mContext, mDatas);
        middleRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        middleRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new CommonRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                dialog.dismiss();
                if (mDatas.size() > 0 && null != listener) {
                    listener.item(mDatas.get(position));
                }
            }
        });
    }

    /**
     * 设置弹框样式（默认普通提示框）
     */
    public MiddleDialogConfig setDialogStyle(DialogEnum dialogStyle) {
        this.dialogEnum = dialogStyle;
        if (dialogStyle == DialogEnum.BASIC) {
            middleContent.setVisibility(View.VISIBLE);
            middleEdit.setVisibility(View.GONE);
            middleRecycler.setVisibility(View.GONE);
        } else if (dialogStyle == DialogEnum.EDIT) {
            middleContent.setVisibility(View.GONE);
            middleEdit.setVisibility(View.VISIBLE);
            middleRecycler.setVisibility(View.GONE);
        } else if (dialogStyle == DialogEnum.LIST) {
            middleContent.setVisibility(View.GONE);
            middleEdit.setVisibility(View.GONE);
            middleRecycler.setVisibility(View.VISIBLE);
        }
        return this;
    }

    /**
     * 设置数据源
     */
    public MiddleDialogConfig setDatas(List<String> mDatas) {
        this.mDatas = mDatas;
        if (dialogEnum == DialogEnum.LIST) {
            adapter.setList(mDatas);
        }
        return this;
    }

    /**
     * 设置超过几个列表可滑动
     * 需要传dialog 宽度否则item错乱 0-1
     */
    public MiddleDialogConfig setItemSlidingCount(int count, double dialogWidth) {
        ViewGroup.LayoutParams params = middleRecycler.getLayoutParams();
        params.height = ScreenUtils.dip2px(mContext, count) * 45;
        params.width = (int) (ScreenUtils.getScreenWidth(mContext) * dialogWidth);
        Log.e(TAG, "initAdapter: " + params.height);
        middleRecycler.setLayoutParams(params);
        return this;
    }


    /**
     * 点击外部是否可取消
     */
    public MiddleDialogConfig setIsCancel(boolean isCancel) {
        dialog.setCanceledOnTouchOutside(isCancel);
        dialog.setCancelable(isCancel);
        return this;
    }

    /**
     * 设置弹框高度-自身控件高度
     * 屏幕高度的百分比（0-1）
     */
    public MiddleDialogConfig setHeight(double height) {
        params.height = (int) (ScreenUtils.getScreenWidth(mContext) * height);
        return this;
    }

    /**
     * 设置弹框宽度
     * 屏幕宽度的百分比（0-1）
     */
    public MiddleDialogConfig setWidth(double width) {
        params.width = (int) (ScreenUtils.getScreenWidth(mContext) * width);
        return this;
    }

    /**
     * 设置垂直分隔线颜色
     */
    public MiddleDialogConfig setHeightLineColor(String color) {
        middleLine.setBackgroundColor(Color.parseColor(color));
        return this;
    }

    /**
     * 设置横向分隔线颜色
     */
    public MiddleDialogConfig setWidthLineColor(String color) {
        middleWidthLine.setBackgroundColor(Color.parseColor(color));
        return this;
    }


    /**
     * 设置标题的内容
     */
    public MiddleDialogConfig setTitle(String title) {
        middleTitle.setText(title);
        return this;
    }

    /**
     * 设置标题字体颜色
     */
    public MiddleDialogConfig setTitleColor(String color) {
        middleTitle.setTextColor(Color.parseColor(color));
        return this;
    }

    /**
     * 是否显示标题
     */
    public MiddleDialogConfig setTitleVis(boolean isVis) {
        if (isVis) {
            middleTitle.setVisibility(View.VISIBLE);
        } else {
            middleTitle.setVisibility(View.GONE);
        }
        return this;
    }

    /**
     * 设置标题栏背景颜色
     * drawable类型
     */
    public MiddleDialogConfig setTitleBgColor(int bgColor) {
        middleTitle.setBackgroundResource(bgColor);
        return this;
    }

    /**
     * 设置标题边距
     */
    public MiddleDialogConfig setTitlePadding(int left, int top, int right, int bottom) {
        middleTitle.setPadding(ScreenUtils.dip2px(mContext, left),
                ScreenUtils.dip2px(mContext, top),
                ScreenUtils.dip2px(mContext, right),
                ScreenUtils.dip2px(mContext, bottom));
        return this;
    }

    /**
     * 设置提示的内容
     */
    public MiddleDialogConfig setContent(String content) {
        middleContent.setText(content);
        return this;
    }

    /**
     * 设置提示的内容带下
     */
    public MiddleDialogConfig setContentSize(int size) {
        middleContent.setTextSize(size);
        return this;
    }

    /**
     * 设置内容颜色
     */
    public MiddleDialogConfig setContentColor(String color) {
        middleContent.setTextColor(Color.parseColor(color));
        return this;
    }

    /**
     * 设置内容背景颜色
     * drawable类型
     */
    public MiddleDialogConfig setContentBgColor(int bgColor) {
        middleContent.setBackgroundResource(bgColor);
        return this;
    }

    /**
     * 设置内容边距
     */
    public MiddleDialogConfig setContentPadding(float left, float top, float right, float bottom) {
        middleContent.setPadding(ScreenUtils.dip2px(mContext, left),
                ScreenUtils.dip2px(mContext, top),
                ScreenUtils.dip2px(mContext, right),
                ScreenUtils.dip2px(mContext, bottom));
        return this;
    }

    /**
     * 设置最大显示几行
     */
    public MiddleDialogConfig setContentMaxLine(int line) {
        middleContent.setMaxLines(line);
        middleContent.setEllipsize(TextUtils.TruncateAt.END);
        return this;
    }

    /**
     * 设置左侧按钮文字
     */
    public MiddleDialogConfig setLeft(String leftString) {
        middleCancel.setText(leftString);
        return this;
    }

    /**
     * 设置左侧按钮文字颜色
     */
    public MiddleDialogConfig setLeftColor(String leftColor) {
        middleCancel.setTextColor(Color.parseColor(leftColor));
        return this;
    }

    /**
     * 设置左侧是否显示
     */
    public MiddleDialogConfig setLeftVis(boolean isVis) {
        if (isVis) {
            middleCancel.setVisibility(View.VISIBLE);
            middleLine.setVisibility(View.VISIBLE);
        } else {
            middleCancel.setVisibility(View.GONE);
            middleLine.setVisibility(View.GONE);
            middleDetermine.setBackgroundResource(R.drawable.bottom_shape);
        }
        return this;
    }

    /**
     * 设置右侧按钮文字
     */
    public MiddleDialogConfig setRight(String rightString) {
        middleDetermine.setText(rightString);
        return this;
    }

    /**
     * 设置右侧按钮文字颜色
     */
    public MiddleDialogConfig setRightColor(String rightColor) {
        middleDetermine.setTextColor(Color.parseColor(rightColor));
        return this;
    }

    /**
     * 设置右侧是否显示
     */
    public MiddleDialogConfig setRightVis(boolean isVis) {
        if (isVis) {
            middleDetermine.setVisibility(View.VISIBLE);
            middleLine.setVisibility(View.VISIBLE);
        } else {
            middleDetermine.setVisibility(View.GONE);
            middleLine.setVisibility(View.GONE);
            middleCancel.setBackgroundResource(R.drawable.bottom_shape);

        }
        return this;
    }

    /**
     * 设置底部右边边文字边距
     */
    public MiddleDialogConfig setBottomPadding(int left, int top, int right, int bottom) {
        middleDetermine.setPadding(ScreenUtils.dip2px(mContext, left),
                ScreenUtils.dip2px(mContext, top),
                ScreenUtils.dip2px(mContext, right),
                ScreenUtils.dip2px(mContext, bottom));
        return this;
    }

    /**
     * 设置弹框样式
     * drawable类型
     */
    public MiddleDialogConfig setBackageShape(int resId) {
        middleLinear.setBackgroundResource(resId);
        return this;
    }


    /**
     * 设置输入框提示字
     */
    public MiddleDialogConfig setEditHint(String hint) {
        middleEdit.setHint(hint);
        return this;
    }

    /**
     * 设置输入框提示字
     */
    public MiddleDialogConfig setEditHintNull(String hint) {
        this.textHint = hint;
        return this;
    }

    /**
     * 设置输入框提示字
     */
    public MiddleDialogConfig setEditHintColor(String hintColor) {
        middleEdit.setHintTextColor(Color.parseColor(hintColor));
        return this;
    }

    /**
     * 设置输入框文字颜色
     */
    public MiddleDialogConfig setEditTextColor(String hintColor) {
        middleEdit.setTextColor(Color.parseColor(hintColor));
        return this;
    }

    /**
     * 设置输入框样式
     * drawable类型
     */
    public MiddleDialogConfig setEditBackageShape(int resId) {
        middleEdit.setBackgroundResource(resId);
        return this;
    }


    /**
     * 设置输入框内边距
     */
    public MiddleDialogConfig setEditPadding(int left, int top, int right, int bottom) {
        middleEdit.setPadding(ScreenUtils.dip2px(mContext, left),
                ScreenUtils.dip2px(mContext, top),
                ScreenUtils.dip2px(mContext, right),
                ScreenUtils.dip2px(mContext, bottom));
        return this;
    }

    /**
     * 设置输入框距外边距
     */
    public MiddleDialogConfig setEditMargin(int left, int top, int right, int bottom) {
        LinearLayout.LayoutParams olp = (LinearLayout.LayoutParams) middleEdit.getLayoutParams();
        olp.setMargins(ScreenUtils.dip2px(mContext, left),
                ScreenUtils.dip2px(mContext, top),
                ScreenUtils.dip2px(mContext, right),
                ScreenUtils.dip2px(mContext, bottom));
        middleEdit.setLayoutParams(olp);
        return this;
    }

    /**
     * 左右2边点击事件
     */
    private void initClick() {
        middleCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (leftBack != null) {
                    String cont = middleEdit.getText().toString().trim();
                    leftBack.leftBtn(cont);
                }
                dialog.dismiss();
            }
        });
        middleDetermine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rightBack != null) {
                    String cont = null;
                    //样式为输入框样式时
                    if (dialogEnum == DialogEnum.EDIT) {
                        cont = middleEdit.getText().toString().trim();
                        if (TextUtils.isEmpty(cont)) {
                            showToast(textHint);
                            return;
                        }
                    }
                    rightBack.rightBtn(cont);
                    dialog.dismiss();
                }
            }
        });
    }

    /**
     * 左右点击事件回调
     *
     * @param leftBack
     * @return
     */
    public MiddleDialogConfig setLeftCallBack(LeftCallBack leftBack) {
        this.leftBack = leftBack;
        return this;
    }

    public MiddleDialogConfig setRightCallBack(RightCallBack rightBack) {
        this.rightBack = rightBack;
        return this;
    }

    /**
     * item点击事件
     */
    public MiddleDialogConfig setItemCallBack(ItemCallBackListener listener) {
        this.listener = listener;
        return this;
    }

    /**
     * 字体大小
     */
    public MiddleDialogConfig setItemSize(int size) {
        adapter.setItemSize(size);
        return this;
    }

    /**
     * 字体颜色
     */
    public MiddleDialogConfig setItemColor(String color) {
        adapter.setItemColor(color);
        return this;
    }

    /**
     * 设置item边距
     */
    public MiddleDialogConfig setItemPadding(int left, int top, int right, int bottom) {
        adapter.setItemPadding(left, top, right, bottom);
        return this;
    }

    /**
     * 设置item位置
     */
    public MiddleDialogConfig setItemGravity(int gravity) {
        adapter.setItemGravity(gravity);
        return this;
    }

    private LeftCallBack leftBack;

    public interface LeftCallBack {
        void leftBtn(String cont);
    }

    private RightCallBack rightBack;

    public interface RightCallBack {
        void rightBtn(String cont);

    }

    private ItemCallBackListener listener;

    public interface ItemCallBackListener {
        void item(String str);

    }

    /**
     * 显示底部弹框
     */
    public void show() {
        if (dialog != null && (!dialog.isShowing())) {
            dialog.show();
        }
    }

    /**
     * 提示文本
     */
    private void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}

