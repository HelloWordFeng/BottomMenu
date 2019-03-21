package com.helloworld.bottommenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.helloworld.library.BottomMenuFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.findViewById(R.id.open_qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQQDialog();
            }
        });
        this.findViewById(R.id.open_ios).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showIOSDialog();
            }
        });
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
}
