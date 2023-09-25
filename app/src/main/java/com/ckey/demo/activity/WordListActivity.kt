package com.ckey.demo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ckey.demo.R
import kotlinx.android.synthetic.main.activity_word_list.*

class WordListActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_list)

        ivBack.setOnClickListener { finish() }
    }


}