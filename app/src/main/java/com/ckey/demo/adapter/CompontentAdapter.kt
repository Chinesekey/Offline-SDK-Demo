package com.ckey.demo.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ckey.demo.R
import com.ckey.demo.bean.CharacterExhibitAudioInfo
import com.ckey.demo.view.CenterComponentPopupView
import com.lxj.xpopup.XPopup

class CompontentAdapter(
    private val context: Context,
    private val list: List<CharacterExhibitAudioInfo>
) : BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        var viewHold: ViewHold = ViewHold()
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.layout_component_word, null)
            viewHold.tvTitle = convertView.findViewById<View>(R.id.tvTitle) as TextView
            convertView.tag = viewHold
        } else {
            viewHold = convertView.tag as ViewHold
        }
        val characterExhibitInfo = list[position]
        viewHold.tvTitle!!.text = characterExhibitInfo.name
        viewHold.tvTitle!!.setOnClickListener { showPopupView(context, characterExhibitInfo) }
        return convertView
    }

    fun showPopupView(
        context: Context?,
        characterExhibitAudioInfo: CharacterExhibitAudioInfo?
    ): CenterComponentPopupView {
        val cent = CenterComponentPopupView(context!!, characterExhibitAudioInfo)
        XPopup.Builder(context)
            .hasShadowBg(true)
            .dismissOnBackPressed(true)
            .dismissOnTouchOutside(false)
            .asCustom(cent)
            .show()
        return cent
    }

    internal inner class ViewHold {
        var tvTitle: TextView? = null
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return 0
    }
}