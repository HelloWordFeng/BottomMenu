# BottomMenu  
## 仿IOS、QQ底部导航栏效果,可自定义多种属性效果  
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

##1、使用方法 
1、默认IOS弹框

```python
    private List<String> list = new ArrayList<>();

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
```

2、QQ图片长按弹框
```python
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
                .setCancelPadding(0,0,0,0)
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
```

##2、属性说明 

### 
|  方法名  |  描述  |  参数值【默认】  |
| :-------- | --------:| :--: |
| addMenuItems  | 数据源item选项 |  List<String> menuItems   |
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

### **补充说明** 
如果上诉属性设置均未满足需求，可自行下载源码进行修改。
