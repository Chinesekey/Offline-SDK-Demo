package com.ckey.demo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ckey.demo.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ckey.demo.bean.CharacterExhibitAudioInfo;
import com.ckey.demo.utils.ScreenUtils;
import com.dictionary.sdk.bean.CharacterExhibitAudioBean;
import com.lxj.xpopup.core.CenterPopupView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class CenterComponentPopupView extends CenterPopupView {

    private int loadCount = 0;
    private CharacterExhibitAudioInfo characterExhibitAudioBean;

    public CenterComponentPopupView(@NonNull Context context, CharacterExhibitAudioInfo characterExhibitAudioBean) {
        super(context);
        this.characterExhibitAudioBean = characterExhibitAudioBean;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.pop_component;
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


        TextView tvWord = findViewById(R.id.tv_word);
        TextView tvName = findViewById(R.id.tv_name);
        TextView tvContent = findViewById(R.id.tv_content);
        //  XLog.e("-----${mComponent}---"+mComponent);
        if(!TextUtils.isEmpty(characterExhibitAudioBean.name)){
            tvWord.setText(characterExhibitAudioBean.name);
        }
        if(!TextUtils.isEmpty(characterExhibitAudioBean.componentBasicInterpret)){
            tvName.setText(characterExhibitAudioBean.componentBasicInterpret);
        }
//
        if(!TextUtils.isEmpty(characterExhibitAudioBean.componentEtymologyRemark)){
            tvContent.setText(characterExhibitAudioBean.componentEtymologyRemark);
        }
//
    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
    }

    @Override
    protected int getMaxWidth() {
        return ScreenUtils.dp2px(getContext(), 300);
    }


}
