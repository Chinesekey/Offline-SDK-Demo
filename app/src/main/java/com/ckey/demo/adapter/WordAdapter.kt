package com.ckey.demo.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ckey.demo.R
import com.ckey.demo.bean.ChineseCharacterInfo
import com.dictionary.sdk.log.XLog

class WordAdapter(private val context: Context, list: List<ChineseCharacterInfo>) : BaseAdapter() {
    private val list: List<ChineseCharacterInfo>

    init {
        this.list = list
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView: View? = convertView
        var viewHold : ViewHold = ViewHold()
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.layout_word, null)
            viewHold.tvpinyin = convertView.findViewById<View>(R.id.tvpinyin) as TextView
            viewHold.tvTitle = convertView.findViewById<View>(R.id.tvTitle) as TextView
            convertView.tag = viewHold
        } else {
            viewHold = convertView.tag as ViewHold
        }
        var data: ChineseCharacterInfo = list[position]
        viewHold.tvTitle!!.text = data.characterName
        viewHold.tvpinyin!!.text = data!!.characterPinyin
        return convertView
    }

    internal inner class ViewHold {
        var tvTitle: TextView? = null
        var tvpinyin: TextView? = null
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }
}