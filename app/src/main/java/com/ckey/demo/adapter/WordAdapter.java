package com.ckey.demo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.ckey.demo.R;
import com.dictionary.sdk.bean.ChineseCharacter;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.util.BannerUtils;

import java.util.List;

public class WordAdapter extends BaseAdapter {

    private Context context;
    private List<ChineseCharacter> list;

    public WordAdapter(Context contexts, List<ChineseCharacter> list) {
        this.context = contexts;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHold viewHold = new ViewHold();
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.layout_word, null);
            viewHold.tvpinyin = (TextView) convertView.findViewById(R.id.tvpinyin);
            viewHold.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            convertView.setTag(viewHold);
        } else {
            viewHold = (ViewHold) convertView.getTag();
        }

        ChineseCharacter date = list.get(position);

        viewHold.tvTitle.setText(date.characterName);
        viewHold.tvpinyin.setText(date.characterPinyin);

        return convertView;
    }

    class ViewHold {
        private TextView tvTitle,tvpinyin;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


}
