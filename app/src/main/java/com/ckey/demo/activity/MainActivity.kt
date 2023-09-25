package com.ckey.demo.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.ckey.demo.R
import com.ckey.demo.adapter.WordAdapter
import com.ckey.demo.bean.ChineseCharacterInfo
import com.ckey.demo.utils.GetJsonDataUtil
import com.ckey.demo.utils.SimpleListener
import com.ckey.demo.view.LanguagePopViewView
import com.dictionary.sdk.global.ChinesekeyConfigure
import com.dictionary.sdk.log.XLog
import com.dictionary.sdk.util.GsonUtils
import com.dictionary.sdk.util.ToastUtils
import com.lxj.xpopup.XPopup
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var chineseCharacters: List<ChineseCharacterInfo> = ArrayList()

    private var character: ChineseCharacterInfo? = null
    private var wordName: String = ""
    private var mAdapter: WordAdapter? = null

    private var Handler :Handler = Handler()

    private var languageCode = "zh-CN"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvShow.setOnClickListener { getChineseCharacterByWord() }
        tvAllShow.setOnClickListener {
            allChineseCharacter
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

        tvChangeLanguage.setOnClickListener {
            updateLanguage()
        }

    }


    private fun getChineseCharacterByWord() {

        wordName = edWord.text.toString().trim()
        if (TextUtils.isEmpty(wordName)) {
            ToastUtils.showMessage(this, "请输入要查询的汉字或翻译")
            return
        }

       var strs = ChinesekeyConfigure.getChineseCharacterByName(this, wordName)
        character = GsonUtils.getSingleBean(strs,
            ChineseCharacterInfo::class.java) as ChineseCharacterInfo?
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


    private val allChineseCharacter: Unit
        get() {
            var strs = ChinesekeyConfigure.getAllChineseCharacter(this)
            chineseCharacters = GetJsonDataUtil.getChineseCharacterInfoList(strs)
            mAdapter = WordAdapter(this, chineseCharacters)
            mRecyclerView!!.adapter = mAdapter
            mRecyclerView!!.onItemClickListener =
                AdapterView.OnItemClickListener { adapterView, view, i, l ->
                    val chineseCharacter = chineseCharacters!![i]
                    val intent = Intent(this, WordDetailActivity::class.java)
                    intent.putExtra("data", GsonUtils.objectToString(chineseCharacter))
                    startActivity(intent)
                }
        }


    private fun updateLanguage() {

        val cent = LanguagePopViewView(this)
        XPopup.Builder(this)
            .hasShadowBg(true)
            .dismissOnBackPressed(true)
            .dismissOnTouchOutside(false)
            .asCustom(cent)
            .show()

        cent!!.setOnSimpleListener(object : SimpleListener<String>() {
            override fun onClick(code:String,name:String) {
               if(!TextUtils.isEmpty(code)){
                   languageCode = code
                   tvChangeLanguage.text = name
                   //根据设备号初始化字典sdk
                   ChinesekeyConfigure.preInit(application, "device018", packageName, languageCode)

                   var strs = ChinesekeyConfigure.getAllChineseCharacter(application)
                   chineseCharacters = GetJsonDataUtil.getChineseCharacterInfoList(strs)

                   mlinearDetail.visibility = View.GONE
               }
            }
        })

    }


}