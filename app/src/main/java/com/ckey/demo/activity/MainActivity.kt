package com.ckey.demo.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ckey.demo.R
import com.dictionary.sdk.bean.ChineseCharacter
import com.dictionary.sdk.global.ChinesekeyConfigure
import com.dictionary.sdk.log.XLog
import com.dictionary.sdk.util.GsonUtils
import com.dictionary.sdk.util.ToastUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        tvInit.setOnClickListener { //                //初始化
//            initChinesekeyConfigure(packName)
//        }
        tvShow.setOnClickListener { getChineseCharacterByWord() }
        tvAllShow.setOnClickListener {
            val intent = Intent(this, WordListActivity::class.java)
            startActivity(intent)
        }

        mlinearWord.setOnClickListener {
            if (character != null) {
                val intent = Intent(this, WordDetailActivity::class.java)
                intent.putExtra("data", GsonUtils.objectToString(character))
                startActivity(intent)
            }
        }

        edWord.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                edWord.postDelayed(Runnable {
                    var index = edWord.text.toString().trim { it <= ' ' }.length
                    if (index > 0) {
                        wordName = edWord.text.toString().trim()
                    } else {
                        wordName = ""
                        mlinearDetail.visibility = View.GONE
                    }
                }, 20L)
            }

            override fun afterTextChanged(editable: Editable) {}
        })
    }


    private fun initChinesekeyConfigure(packName: String?) {
        ChinesekeyConfigure.preInit(this, "device010", packName)
    }

    private var character: ChineseCharacter? = null
    private var wordName: String = ""

    private fun getChineseCharacterByWord() {

        wordName = edWord.text.toString().trim()
        if (TextUtils.isEmpty(wordName)) {
            ToastUtils.showMessage(this, "请输入要查询的汉字")
            return
        }
        character = ChinesekeyConfigure.getChineseCharacterByName(this, wordName)
        XLog.e("----character>>>" + GsonUtils.objectToString(character))
        if (character != null) {
            if (!TextUtils.isEmpty(character!!.characterName)) {
                mlinearDetail.visibility = View.VISIBLE
                tvWordShow.text = character!!.characterName
                tvPinyin.text = character!!.characterPinyin
            } else {
                ToastUtils.showMessage(this, "未查询到想搜索的汉字")
                mlinearDetail.visibility = View.GONE
            }
        }

    }


}