package com.helloworld.bottommenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.helloworld.library.BottomMenuFragment;
import com.helloworld.library.MiddleDialogConfig;
import com.helloworld.library.utils.DataUtils;
import com.helloworld.library.utils.DialogEnum;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<String> list = new ArrayList<>();
    private TextView cityText;
    private ArrayList<String> options = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityText = this.findViewById(R.id.city_text);
        findViewById(R.id.open_click1).setOnClickListener(this);
        findViewById(R.id.open_click2).setOnClickListener(this);
        findViewById(R.id.open_click3).setOnClickListener(this);
        findViewById(R.id.open_click4).setOnClickListener(this);
        findViewById(R.id.open_click5).setOnClickListener(this);
        findViewById(R.id.open_click6).setOnClickListener(this);
        findViewById(R.id.open_click7).setOnClickListener(this);
        initCity();

    }

    /**
     * 初始化省市区数据源（需要时添加）
     */
    private void initCity() {
        DataUtils.initCityList(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.open_click1:
                showDialogType();
                break;
            case R.id.open_click2:
                showListDialog();
                break;
            case R.id.open_click3:
                showDialogBasic1();
                break;
            case R.id.open_click4:
                showDialogBasic2();
                break;
            case R.id.open_click5:
                showQQDialog();
                break;
            case R.id.open_click6:
                showIOSDialog();
                break;
            case R.id.open_click7:
                appendCity = "";//置空
                options.clear();
                //遍历省份数据
                for (int i = 0; i < DataUtils.returnJson().size(); i++) {
                    options.add(DataUtils.returnJson().get(i).getName());
                }
                showCityDialog(0);
                break;
        }
    }

    /**
     * 城市弹框
     */
    //拼接省市区
    private String appendCity;
    private int selectPosition;

    private void showCityDialog(final int selectType) {
        //超过几个可滑动
        int size = 6;
        String title = null;
        if (options.size() < size) {
            size = options.size();
        }
        if (selectType == 0) {
            title = "请选择省份";
        } else if (selectType == 1) {
            title = "请选择城市";
        } else if (selectType == 2) {
            title = "请选择区县";
        }
        new MiddleDialogConfig().builder(this)
                .setTitle(title)
                .setDialogStyle(DialogEnum.CITY)
                .setDatas(options)
                .setItemSlidingCount(size, 0.7)
                .setCityLevel(selectType)
                .setLeftRightVis()
                .setItemCallBack(new MiddleDialogConfig.ItemCallBackListener() {
                    @Override
                    public void item(String str) {
                        String[] cont = str.split(",");
                        appendCity += cont[1] + "\u3000";
                        cityText.setText(appendCity);
                        //int type= Integer.parseInt(cont[2]);
                        int position = Integer.parseInt(cont[0]);
                        if (null != options) {
                            options.clear();
                        }
                        if (selectType == 0) {
                            for (int i = 0; i < DataUtils.returnCitys().get(position).size(); i++) {
                                options.add(DataUtils.returnCitys().get(position).get(i));
                            }
                            selectPosition = position;
                            showCityDialog(1);
                        } else if (selectType == 1) {
                            for (int i = 0; i < DataUtils.returnAreas().get(selectPosition).get(position).size(); i++) {
                                options.add(DataUtils.returnAreas().get(selectPosition).get(position).get(i));
                            }
                            showCityDialog(2);
                        }
                    }
                })
                .show();
    }

    /**
     * 输入框弹框类型
     */
    private void showDialogType() {
        new MiddleDialogConfig().builder(this)
                .setDialogStyle(DialogEnum.EDIT)
                .setRightCallBack(new MiddleDialogConfig.RightCallBack() {
                    @Override
                    public void rightBtn(String cont) {
                        showToast("点击了左边："+cont);
                    }
                })
                .setLeftCallBack(new MiddleDialogConfig.LeftCallBack() {
                    @Override
                    public void leftBtn(String cont) {
                        showToast("点击了右边：" + cont);
                    }
                }).show();
    }

    /**
     * list弹框
     */
    private void showListDialog() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("选项Item" + i);
        }
        new MiddleDialogConfig().builder(this)
                .setDialogStyle(DialogEnum.LIST)
                .setDatas(list)
                .setItemSlidingCount(6, 0.85)
                .setRightCallBack(new MiddleDialogConfig.RightCallBack() {
                    @Override
                    public void rightBtn(String cont) {
                        showToast("点击了左边：");
                    }
                })
                .setLeftCallBack(new MiddleDialogConfig.LeftCallBack() {
                    @Override
                    public void leftBtn(String cont) {
                        showToast("点击了右边：");
                    }
                })
                .setItemCallBack(new MiddleDialogConfig.ItemCallBackListener() {
                    @Override
                    public void item(String str) {
                        showToast("选择了：" + str);
                    }
                })
                .show();
    }

    /**
     * 输入框弹框类型
     */
    private void showDialogBasic1() {
        new MiddleDialogConfig().builder(this)
                .setRightCallBack(new MiddleDialogConfig.RightCallBack() {
                    @Override
                    public void rightBtn(String cont) {
                        showToast("点击了左边：");
                    }
                })
                .setLeftCallBack(new MiddleDialogConfig.LeftCallBack() {
                    @Override
                    public void leftBtn(String cont) {
                        showToast("点击了右边：");
                    }
                }).show();
    }

    /**
     * 输入框弹框类型
     */
    private void showDialogBasic2() {
        new MiddleDialogConfig().builder(this)
                .setTitleVis(false)
                .setContentColor("#ff0000")
                .setLeftVis(false)
                .setContentPadding(20, 20, 20, 20)
                .setContentMaxLine(2)
                .setContentSize(16)
                .setIsCancel(true)
                .setRightCallBack(new MiddleDialogConfig.RightCallBack() {
                    @Override
                    public void rightBtn(String cont) {
                        showToast("点击了右边：");
                    }
                }).show();
    }


    /**
     * 仿ios弹框
     */
    private void showIOSDialog() {
        list.clear();
        for (int i = 0; i < 4; i++) {
            list.add("文本内容" + i);
        }
        new BottomMenuFragment(this)
                .setTitle("标题")
                .addMenuItems(list)
                .setOnItemClickListener(new BottomMenuFragment.OnItemClickListener() {
                    @Override
                    public void onItemClick(TextView menu, int position) {
                        Toast.makeText(MainActivity.this, menu.getText().toString(),
                                Toast.LENGTH_SHORT).show();

                    }
                })
                .show();
    }

    /**
     * 仿QQ图片长按弹框
     */
    private void showQQDialog() {
        list.clear();
        list.add("发送给好友");
        list.add("转载到空间相册");
        list.add("群相册");
        list.add("保存到手机");
        list.add("提取图中文字");
        list.add("收藏");
        new BottomMenuFragment(this)
                .setContentSize(16)
                .setCancelTextTitle("取消")
                .setCancelTextSize(16)
                .setCancelTextColor("#454545")
                .setCancelTextHeight(45)
                .setCancelTextMarginTop(20)
                .setContentColor("#454545")
                .addMenuItems(list)
                .setBackgroundColor("#ebebeb")
                .setCancelPadding(0, 0, 0, 0)
                .setSizeOneShape(R.drawable.bottom_menu_mid_selector)
                .setTopShape(R.drawable.bottom_menu_mid_selector)
                .setMiddleShape(R.drawable.bottom_menu_mid_selector)
                .setBottomShape(R.drawable.bottom_menu_mid_selector)
                .setCancelShape(R.drawable.bottom_menu_mid_selector)
                .setOnItemClickListener(new BottomMenuFragment.OnItemClickListener() {
                    @Override
                    public void onItemClick(TextView menu, int position) {
                        Toast.makeText(MainActivity.this, menu.getText().toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
