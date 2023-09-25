package com.ckey.demo.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.ckey.demo.bean.ChineseCharacterInfo;
import com.ckey.demo.bean.JsonInfo;
import com.dictionary.sdk.bean.ChineseCharacterBean;
import com.dictionary.sdk.log.XLog;
import com.dictionary.sdk.util.GsonUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * <读取Json文件的工具类>
 *
 * @author: 小嵩
 * @date: 2017/3/16 16:22

 */

public class GetJsonDataUtil {


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

    public static ArrayList<JsonInfo> parseData(String result) {//Gson 解析
        ArrayList<JsonInfo> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonInfo entity = gson.fromJson(data.optJSONObject(i).toString(), JsonInfo.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

    public static List<ChineseCharacterInfo> getChineseCharacterInfoList(String strs){
        List<ChineseCharacterInfo> list = new Gson().fromJson(strs, new TypeToken<List<ChineseCharacterInfo>>() {
        }.getType());
        XLog.e("----list--->>>"+list.size());
        return list;
    }


    /**
     * 功能描述：把JSON数据转换成指定的java对象列表
     *
     * @param jsonData JSON数据
     * @return
     * @throws Exception
     */
    public static <T> List<T> getBeanList(String jsonData) {

        try {
            return new Gson().fromJson(jsonData, new TypeToken<List<T>>() {
            }.getType());
        } catch (Exception e) {
            return null;
        }

    }

}

