# BottomMenu  
## 各种简单样式的弹出框 现包含IOS、QQ底部弹出框、单个EditText输入框、List列表和提示框等效果，可以自定义多种属性效果。  
# 效果图
![效果图](https://github.com/HelloWordFeng/BottomMenu/blob/master/picture/design_sketch.gif)  
## 如何使用
1、项目根目录加入
dependencies {  
```python
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
```
在build.gradle加入依赖

```python
      implementation 'com.github.HelloWordFeng:BottomMenu:1.0.1'

```

## 1、使用方法 

```python
    public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.open_click1).setOnClickListener(this);
        findViewById(R.id.open_click2).setOnClickListener(this);
        findViewById(R.id.open_click3).setOnClickListener(this);
        findViewById(R.id.open_click4).setOnClickListener(this);
        findViewById(R.id.open_click5).setOnClickListener(this);
        findViewById(R.id.open_click6).setOnClickListener(this);
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
        }
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
                        showToast("点击了左边：");
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
                        showToast("点击了左边：" );
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
                .setContentPadding(20,20,20,20)
                .setContentMaxLine(2)
                .setContentSize(16)
                .setIsCancel(true)
                .setRightCallBack(new MiddleDialogConfig.RightCallBack() {
                    @Override
                    public void rightBtn(String cont) {
                        showToast("点击了右边：" );
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

```

## 2、属性说明 
1、BottomMenuFragment （底部样式）
### 
|  方法名  |  描述  |  参数值【默认】  |
| :--------: | :--------:| :--: |
| addMenuItems  | 数据源item选项 |  （List<String> menuItems）   |
| setCancelTextTitle |  底部文字文本  |  取消  |
| setCancelTextSize |    底部文字大小  | 16sp  |
| setCancelTextColor |    底部文字颜色  | （#467CD4蓝色）  |
| setCancelTextHeight  |    底部文字高度 | dip2px（45）)  |
| setCancelTextMarginTop  |    底部取消文字边距(距离顶部) | 20  |
| setCancelPadding  |    底部文字整体边距 | int left, int top, int right, int bottom （20） |
| setCancelShape  |    取消文字点击样式 | int resId （例如：R.drawable.bottom_menu_selector 非图片 样式shape） |
| setBackgroundColor  |    底部整体背景 | （#00000000）  |
| setTitle  |    标题 | -  |
| setTitleSize  |    文字大小 | 16  |
| setTitleColor  |    文字颜色 | （#ff0000 红色）  |
| setContentSize  |    item内容文字大小 | 15sp  |
| setContentColor  |    item内容文字颜色 | （#467CD4蓝色）  |
| setLineHeight  |    item分割线高度 | 1dp  |
| setLineColor  |    item分割线颜色 | （#ebebeb 灰色）  |
| setSizeOneShape  |    数据size为1个样式 |  （例如：R.drawable.bottom_menu_selector 非图片 样式shape）  |
| setTopShape  |    item第一个样式  | （例如：R.drawable.bottom_menu_top_selector 非图片 样式shape）  |
| setMiddleShape  |    item中间样式 | （例如：R.drawable.bottom_menu_mid_selector 非图片 样式shape）  |
| setBottomShape  |    item最后一个样式 | （例如：R.drawable.bottom_menu_bottom_selector 非图片 样式shape）  |

2、MiddleDialogConfig （中间样式）
### 
|  方法名  |  描述  |  参数值【默认】  |
| :--------: | :--------:| :--: |
| setDialogStyle  | 弹框样式（默认BASIC） |   BASIC,EDIT,LIST   |
| setDatas |  数据源  |  List<String> mDatas  |
| setItemSlidingCount |    超过个数列表可滑动、弹框宽度屏幕占比  | int count, double dialogWidth(0-1)  |
| setIsCancel |    点击外部是否可取消  | boolean isCancel  |
| setHeight  |    弹框高度屏幕的百分比 | double heigh（0-1）  |
| setWidth  |    弹框宽度屏幕的百分比 | double width（0-1）  |
| setHeightLineColor  |    垂直分隔线颜色 | （默认："#454545"） |
| setWidthLineColor  |    横向分隔线颜色 | （默认："#454545"） |
| setTitleVis  |    是否显示标题 | boolean isVis  |
| setTitleBgColor  |    设置标题栏背景 | int bgColor（非图片 样式shape）  |
| setTitlePadding  |    设置标题边距 | int left, int top, int right, int bottom （15）  |
| setContent  |    设置提示的内容 | （String类型）  |
| setContentBgColor  |    设置内容背景 | int bgColor（非图片 样式shape）  |
| setContentPadding  |    设置内容边距 | int left, int top, int right, int bottom （20）  |
| setContentMaxLine  |    设置最大显示几行 | -  |
| setLeft  |    设置左侧按钮文字 | （String类型）  |
| setLeftColor  |    左侧按钮文字颜色 |  （默认："#454545"）  |
| setLeftVis  |    左侧是否显示  | boolean isVis  |
| setLeftCallBack  |    左侧点击事件  | LeftCallBack leftBack  |
| setBottomPadding  |    设置底部右边边文字边距 | int left, int top, int right, int bottom （15）  |
| setBackageShape  |    弹框样式 | int resId（非图片 样式shape）  |
| setEditHint  |    输入框提示文字 | （String类型）  |
| setEditHintNull  |    输入框为空提示字 | （String类型）  |
| setEditHintColor  |    输入框为空提示字颜色 | （默认："#828282"）  |
| setEditTextColor  |    输入框文字颜色 | （默认："#454545"）  |
| setEditBackageShape  |    输入框样式 | int resId（非图片 样式shape 15dp圆角））  |
| setEditPadding  |    输入框内边距 | int left, int top, int right, int bottom （15）  |
| setEditMargin  |    输入框距外边距 | int left, int top, int right, int bottom （15）  |
| setItemCallBack  |    item点击事件 | ItemCallBackListener listener  |
| setItemSize  |    item字体大小 | 15sp  |
| setItemColor  |    item字体颜色 | （默认："#454545"）  |
| setItemPadding  |    item字体内边距 | int left, int top, int right, int bottom （15）  |
| setItemGravity  |    item字体位置 | Gravity.TOP、Gravity.RIGHT、Gravity.LEFT、Gravity.BOTTOM...  |
| show  |    显示底部弹框 | -  |

注意：-为系统默认值

## 3、 补充说明
如果上诉属性设置均未满足需求，可自行下载源码进行修改。
