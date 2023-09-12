package com.ckey.demo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.ckey.demo.R;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.util.BannerUtils;

import java.util.List;

public class ImageAdapter extends BannerAdapter<String, RecyclerView.ViewHolder> {
    private Context context;

    public ImageAdapter(List<String> mDatas, Context context) {
        super(mDatas);
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new ImageHolder(BannerUtils.getView(parent, R.layout.banner_image));
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public void onBindView(RecyclerView.ViewHolder holder, String data, int position, int size) {
        ImageHolder imageHolder = (ImageHolder) holder;
        if (!TextUtils.isEmpty(data)) {
            imageHolder.imageView.setVisibility(View.VISIBLE);
            RequestOptions requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.banner)  //指定加载前显示的图片资源
                    .fallback(R.drawable.banner)//指定传递加载资源为 null 的时候，显示的图片资源
                    .error(R.drawable.banner);//指定加载失败显示的图片资源
            try {
                if (!TextUtils.isEmpty(data) && !data.contains("null")) {
                    Glide.with(context).load(data).apply(requestOptions).into(imageHolder.imageView);
                } else {
                    Glide.with(context).load(R.drawable.banner).apply(requestOptions).into(imageHolder.imageView);
                }
            } catch (Exception e) {
                Glide.with(context).load(R.drawable.banner).apply(requestOptions).into(imageHolder.imageView);
            }

        } else {
        }


    }


}
