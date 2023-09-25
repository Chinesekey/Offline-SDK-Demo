package com.ckey.demo.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ckey.demo.R
import com.ckey.demo.bean.JsonInfo
import com.ckey.demo.utils.SimpleListener

class LanguageAdapter(private val context: Context, private val list: List<JsonInfo>) :
    BaseAdapter() {
    var simpleListener: SimpleListener<*>? = null
        private set

    fun setOnSimpleListener(simpleListener: SimpleListener<*>?) {
        this.simpleListener = simpleListener
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        var viewHold: ViewHold = ViewHold()
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.layout_language_word, null)
            viewHold.tvTitle = convertView.findViewById<View>(R.id.tvTitle) as TextView
            convertView.tag = viewHold
        } else {
            viewHold = convertView.tag as ViewHold
        }
        val characterExhibitAudioBean = list[position]
        viewHold.tvTitle!!.text = characterExhibitAudioBean.name
        viewHold.tvTitle!!.setOnClickListener {
            if (simpleListener != null) {
                simpleListener!!.onClick(
                    characterExhibitAudioBean.code,
                    characterExhibitAudioBean.name
                )
            }
        }
        return convertView
    }

    private inner class ViewHold {
        var tvTitle: TextView? = null
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return 0
    }
}