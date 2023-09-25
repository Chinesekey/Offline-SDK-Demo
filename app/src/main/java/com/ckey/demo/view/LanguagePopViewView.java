package com.ckey.demo.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.ckey.demo.R;
import com.ckey.demo.adapter.LanguageAdapter;
import com.ckey.demo.bean.JsonInfo;
import com.ckey.demo.utils.GetJsonDataUtil;
import com.ckey.demo.utils.ScreenUtils;
import com.ckey.demo.utils.SimpleListener;
import com.dictionary.sdk.bean.CharacterExhibitAudioBean;
import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.core.CenterPopupView;

import java.util.ArrayList;
import java.util.List;


public class LanguagePopViewView extends BottomPopupView {

    private int loadCount = 0;

    private List<JsonInfo> jsonBean;

    private SimpleListener simpleListener;

    public SimpleListener getSimpleListener() {
        return simpleListener;
    }

    public void setOnSimpleListener(SimpleListener simpleListener) {
        this.simpleListener = simpleListener;
    }


    public LanguagePopViewView(@NonNull Context context) {
        super(context);
        String JsonData = new GetJsonDataUtil().getJson(getContext(), "language.json");//获取assets目录下的json文件数据
        jsonBean = new GetJsonDataUtil().parseData(JsonData);//用Gson 转成实体
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.pop_language;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        findViewById(R.id.iv_delete).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        LanguageAdapter languageAdapter = new LanguageAdapter(getContext(), jsonBean);
        ListView mList = findViewById(R.id.mList);
        mList.setAdapter(languageAdapter);

        languageAdapter.setOnSimpleListener(new SimpleListener(){
            @Override
            public void onClick(String code,String name) {
                super.onClick();
                if(simpleListener !=null && !TextUtils.isEmpty(code)){
                    simpleListener.onClick(code,name);
                    dismiss();
                }
            }
        });

    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
    }

    @Override
    protected int getMaxWidth() {
        return ScreenUtils.getScreenWidth(getContext());
    }


}
