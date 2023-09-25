package com.ckey.demo.view;

import android.view.View;

import androidx.viewpager2.widget.ViewPager2;

public class AlphaPageTransformerNew implements ViewPager2.PageTransformer {
    private static final float DEFAULT_MIN_ALPHA = 0.5f;
    private float mMinAlpha = DEFAULT_MIN_ALPHA;

    @Override
    public void transformPage(View view, float position) {
        view.setScaleY(1.0f);//hack
        if (position < -1) {
            view.setAlpha(mMinAlpha);
        } else if (position <= 1) { // [-1,1]

            if (position < 0) //[0，-1]
            {
                float factor = mMinAlpha + (1 - mMinAlpha) * (1 + position);
                view.setAlpha(factor);
            } else//[1，0]
            {
                float factor = mMinAlpha + (1 - mMinAlpha) * (1 - position);
                view.setAlpha(factor);
            }
        } else { // (1,+Infinity]
            view.setAlpha(mMinAlpha);
        }
    }
}
