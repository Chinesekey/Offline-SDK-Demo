package com.ckey.demo.activity

import android.graphics.drawable.AnimationDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.ckey.demo.R
import com.ckey.demo.adapter.AlphaPageTransformerNew
import com.ckey.demo.adapter.ImageAdapter
import com.ckey.demo.utils.MusicUtil
import com.ckey.demo.utils.ScreenUtils
import com.dictionary.sdk.bean.ChineseCharacter
import com.dictionary.sdk.log.XLog
import com.dictionary.sdk.util.GsonUtils
import com.youth.banner.config.IndicatorConfig
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.util.BannerUtils
import kotlinx.android.synthetic.main.activity_word_detail.*
import kotlinx.android.synthetic.main.item_learn_center_word.*
import kotlinx.android.synthetic.main.item_learn_pinyin_bottom_audio.*
import kotlinx.android.synthetic.main.item_learn_pinyin_bottom_git.*
import kotlinx.android.synthetic.main.item_learn_pinyin_bottom_img.*
import java.io.IOException
import java.util.*

class WordDetailActivity : AppCompatActivity(), View.OnClickListener {

    private var character: ChineseCharacter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_detail)
        initViewShow()

        val data = intent.getStringExtra("data")
        if (!TextUtils.isEmpty(data)) {
            character =
                GsonUtils.getSingleBean(data, ChineseCharacter::class.java) as ChineseCharacter
            if (character != null) {
                if (!TextUtils.isEmpty(character!!.characterName)) {
                    tv_Word.text = character!!.characterName
                }

                if (!TextUtils.isEmpty(character!!.characterPinyin)) {
                    tv_pinyins.text = character!!.characterPinyin
                }

                if (!TextUtils.isEmpty(character!!.characterStructure)) {
                    tv_top.text = character!!.characterStructure
                }

                imageShow()

                if (!TextUtils.isEmpty(character!!.characterWrite)) {
                    iv_edit.visibility = View.VISIBLE
                } else {
                    iv_edit.visibility = View.GONE
                }

                if (!TextUtils.isEmpty(character!!.characterAudioWoman)) {
                    audioUrl = character!!.characterAudioWoman
                    iv_sound_filling.visibility = View.VISIBLE
                    initPlayer()
                } else {
                    iv_sound_filling.visibility = View.GONE

                }

            }
        }
    }

    private fun imageShow() {
        bannerList = ArrayList()
        if (!TextUtils.isEmpty(character!!.characterScene)) {
            iv_image.visibility = View.VISIBLE
            var list = character!!.characterScene.split(",")
            bannerList!!.addAll(list)
            banner.setDatas(bannerList as List<Nothing>?)
        } else if (!TextUtils.isEmpty(character!!.characterCartoon)) {
            iv_image.visibility = View.VISIBLE
            var list = character!!.characterCartoon.split(",")
            bannerList!!.addAll(list)
            banner.setDatas(bannerList as List<Nothing>?)
        } else {
            iv_image.visibility = View.GONE
        }
        imageAdapter!!.notifyDataSetChanged()

    }

    private fun initViewShow() {

        val layoutParams = mFramenLayout.layoutParams as ViewGroup.LayoutParams
        layoutParams.height = ScreenUtils.getScreenWidth(this)
        mFramenLayout.layoutParams = layoutParams
        initBanner()
        initBannerGif()

        iv_sound_filling.setOnClickListener(this)
        iv_image.setOnClickListener(this)
        iv_edit.setOnClickListener(this)
        ivBack.setOnClickListener(this)
    }


    private var imageAdapter: ImageAdapter? = null
    private var bannerList: MutableList<String>? = null
    private var bannerGifList: MutableList<String>? = null
    private var imageGifAdapter: ImageAdapter? = null

    private fun initBanner() {
        val linearParams1 = ll_img.layoutParams as ViewGroup.LayoutParams
        linearParams1.height = (ScreenUtils.getScreenWidth(this) / 16 * 9)
        ll_img.layoutParams = linearParams1

        imageAdapter = ImageAdapter(bannerList, this)
        // banner.setAdapter(ImageAdapter(bannerList))
        banner.addBannerLifecycleObserver(this)//添加生命周期观察者
            .setAdapter(imageAdapter)
        // .setIndicator(CircleIndicator(context));

        banner.indicator = CircleIndicator(this)
        banner.setIndicatorSelectedColorRes(R.color.black)
        banner.setIndicatorNormalColorRes(R.color.teal_200)
        banner.setIndicatorWidth(BannerUtils.dp2px(6f), BannerUtils.dp2px(6f))
        banner.setIndicatorGravity(IndicatorConfig.Direction.CENTER)
        banner.setIndicatorSpace(BannerUtils.dp2px(6f))
        banner.setIndicatorMargins(IndicatorConfig.Margins(BannerUtils.dp2px(6f)))
        banner.addPageTransformer(AlphaPageTransformerNew())
        // banner.setOnBannerListener(OnBannerListener<Any?> { data, position -> } as Nothing)
        banner.setLoopTime(4000)
        banner.start()

    }


    private fun initBannerGif() {
        val linearParams1 = fl_bottom.layoutParams as ViewGroup.LayoutParams
        linearParams1.height = (ScreenUtils.getScreenWidth(this) / 16 * 9)
        fl_bottom.layoutParams = linearParams1
        //flBottom.setVisibility(View.GONE);
        imageGifAdapter = ImageAdapter(bannerGifList, this)
        // banner.setAdapter(ImageAdapter(bannerList))
        bannerGif.addBannerLifecycleObserver(this)//添加生命周期观察者
            .setAdapter(imageGifAdapter)
        // .setIndicator(CircleIndicator(context));

        bannerGif.indicator = CircleIndicator(this)
        bannerGif.setIndicatorSelectedColorRes(R.color.black)
        banner.setIndicatorNormalColorRes(R.color.teal_200)
        bannerGif.setIndicatorWidth(BannerUtils.dp2px(6f), BannerUtils.dp2px(6f))
        bannerGif.setIndicatorGravity(IndicatorConfig.Direction.CENTER)
        bannerGif.setIndicatorSpace(BannerUtils.dp2px(6f))
        bannerGif.setIndicatorMargins(IndicatorConfig.Margins(BannerUtils.dp2px(6f)))
        banner.addPageTransformer(AlphaPageTransformerNew())
        // banner.setOnBannerListener(OnBannerListener<Any?> { data, position -> } as Nothing)
        bannerGif.setLoopTime(4000)
        bannerGif.start()

    }

    var mediaPlayer: MediaPlayer? = null
    private var audioUrl = ""
    var prepared = false
    private var mTimer: Timer? = null
    private var mTimerTask: TimerTask? = null


    private fun visibility(position: Int) {

        fl_bottom.visibility = View.VISIBLE
        iv_play.background = null
        ll_img.visibility = View.GONE
        ll_audio.visibility = View.GONE
        ll_gif.visibility = View.GONE
        iv_sound_filling.isSelected = false
        iv_image.isSelected = false
        iv_play.isSelected = false
        iv_edit.isSelected = false
        if (position == 1) {
            ll_img.visibility = View.GONE
            ll_gif.visibility = View.VISIBLE
            iv_edit.isSelected = true
            if (character != null) {
                bannerGifList = ArrayList()
                if (!TextUtils.isEmpty(character!!.characterWrite)) {
                    var list = character!!.characterWrite.split(",")
                    bannerGifList!!.addAll(list)
                }
                bannerGif.setDatas(bannerGifList as List<Nothing>?)
                imageGifAdapter!!.notifyDataSetChanged()

            }
        } else if (position == 2) {
            ll_audio.visibility = View.VISIBLE
            iv_sound_filling.isSelected = true
            try {
                if (null != mediaPlayer && mediaPlayer!!.isPlaying) {
                    mediaPlayer!!.stop()
                    prepared = false
                    mediaPlayer!!.prepare()
                    mediaPlayer!!.seekTo(0)
                    iv_audio_play.setBackgroundResource(R.mipmap.ic_pinyin_sound_filling_pressed)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } else if (position == 3) {
            ll_img.visibility = View.VISIBLE
            iv_image.isSelected = true

        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_sound_filling -> {
                visibility(2)
                playAudio()
            }
            R.id.iv_image -> {
                visibility(3)
            }
            R.id.iv_edit -> {
                visibility(1)
            }
            R.id.ivBack -> {
                finish()
            }
//            R.id.learn_interpretation_more -> {
//                if (learn_interpretation_more.isSelected) {
//                    tv_text_content.maxLines = 2
//                    tv_text_more.setText(R.string.learn_more)
//                } else {
//                    tv_text_content.maxLines = 200
//                    tv_text_more.text = ""
//                }
//                learn_interpretation_more.isSelected = !learn_interpretation_more.isSelected
//            }

            R.id.iv_audio_play -> {
                playAudio()
            }
        }
    }


    private fun initPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer!!.release()
        }
        mediaPlayer = MediaPlayer()
        MusicUtil.initPlayer(mediaPlayer, audioUrl, iv_audio_play)
        mediaPlayer!!.setOnPreparedListener(null ?: MediaPlayer.OnPreparedListener {
            prepared = true
        })
    }

    private fun playAudio() {
        if (!prepared) {
            return
        }
        try {
            if (null != mediaPlayer && mediaPlayer!!.isPlaying) {
                mediaPlayer!!.stop()
                prepared = false
                mediaPlayer!!.prepare()
                mediaPlayer!!.seekTo(0)

                iv_audio_play.background = getDrawable(R.mipmap.ic_pinyin_sound_filling_pressed)
            } else {
                iv_audio_play.setBackgroundResource(R.drawable.pinyin_animation_audio)
                mediaPlayer!!.start()
                val drawable = iv_audio_play.background as AnimationDrawable
                drawable.start()
//                iv_audio_play.background = context.getDrawable(R.mipmap.ic_pinyin_sound_filling_pressed)
                doPlay()
            }
        } catch (e: java.lang.Exception) {
        }
    }

    private fun doPlay() {
        MusicUtil.setPlaySpeed(
            mediaPlayer, 0.8f
        )
        mTimer = Timer()
        mTimerTask = object : TimerTask() {
            override fun run() {
                try {
                    if (mTimer != null) {
                        mTimer!!.cancel()
                    }
                } catch (e: Exception) {
                }
            }
        }
        mTimer!!.schedule(mTimerTask, 0, 500)
    }

}