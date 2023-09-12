package com.ckey.demo.activity

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import com.ckey.demo.R
import com.ckey.demo.adapter.WordAdapter
import com.dictionary.sdk.bean.ChineseCharacter
import com.dictionary.sdk.global.ChinesekeyConfigure
import com.dictionary.sdk.util.GsonUtils
import kotlinx.android.synthetic.main.activity_word_list.*

class WordListActivity : AppCompatActivity() {

    private var mAdapter: WordAdapter? = null
    private var chineseCharacters: List<ChineseCharacter?> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_list)
        allChineseCharacter
        ivBack.setOnClickListener { finish() }
    }

    private val allChineseCharacter: Unit
        get() {
            chineseCharacters =
                ChinesekeyConfigure.getAllChineseCharacter(this) as List<ChineseCharacter?>
            mAdapter = WordAdapter(this, chineseCharacters)
            mRecyclerView!!.adapter = mAdapter
            mRecyclerView!!.onItemClickListener = OnItemClickListener { adapterView, view, i, l ->
                val chineseCharacter = chineseCharacters[i]
                val intent = Intent(this@WordListActivity, WordDetailActivity::class.java)
                intent.putExtra("data", GsonUtils.objectToString(chineseCharacter))
                startActivity(intent)
            }
        }
}