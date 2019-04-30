package com.helloworld.library.utils;

import android.content.Context;

import com.helloworld.library.bean.CityJsonDto;

import java.util.ArrayList;

/**
 * 创建时间：2019/4/30 14:28
 * 作者：Hyman峰
 * 功能描述：数据源
 */
public class DataUtils {

    private static ArrayList<CityJsonDto> jsonBean = new ArrayList<>();
    private static ArrayList<String> optionsProvinces = new ArrayList<>();
    private static ArrayList<ArrayList<String>> optionsCitys = new ArrayList<>();
    private static ArrayList<ArrayList<ArrayList<String>>> optionsAreas = new ArrayList<>();

    public static void initCityList(Context context) {
        //获取assets目录下的json文件数据
        String JsonData = Tools.getJson(context, "province.json");//获取assets目录下的json文件数据
        jsonBean = Tools.parseData(JsonData);//用Gson 转成实体


        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表
                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    City_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }
            optionsCitys.add(CityList);
            optionsAreas.add(Province_AreaList);
        }
    }

    /**
     * 返回json数据
     */
    public static ArrayList<CityJsonDto> returnJson() {
        return jsonBean;
    }

    /**
     * 返回二级市区数据
     */
    public static ArrayList<ArrayList<String>> returnCitys() {
        return optionsCitys;
    }

    /**
     * 返回三级县级数据
     */
    public static ArrayList<ArrayList<ArrayList<String>>> returnAreas() {
        return optionsAreas;
    }

}
