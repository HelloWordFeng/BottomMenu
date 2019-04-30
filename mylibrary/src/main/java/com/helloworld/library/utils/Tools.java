package com.helloworld.library.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.helloworld.library.bean.CityJsonDto;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 创建时间：2019/4/30 14:28
 * 作者：Hyman峰
 * 功能描述：工具类
 */
public class Tools {

    /**
     * 读取Json文件
     */
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 解析数据
     */
    public static ArrayList<CityJsonDto> parseData(String result) {//Gson 解析
        ArrayList<CityJsonDto> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                CityJsonDto entity = gson.fromJson(data.optJSONObject(i).toString(), CityJsonDto.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

}
