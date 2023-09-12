package com.ckey.demo.utils;

import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.os.Build;
import android.widget.ImageView;
import java.io.IOException;
import com.ckey.demo.R;


public class MusicUtil {

    public static boolean setPlaySpeed(MediaPlayer mediaPlayer, float speed) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                PlaybackParams params = mediaPlayer.getPlaybackParams();
                params.setSpeed(speed);
                mediaPlayer.setPlaybackParams(params);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }



    public static void initPlayer(MediaPlayer mediaPlayer,String audioUrl, ImageView ivAudioPlay) {

        try {
            mediaPlayer.setDataSource(audioUrl);
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                ivAudioPlay.setBackgroundResource(R.mipmap.ic_pinyin_sound_filling_pressed);
            }
        });
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                ivAudioPlay.setBackgroundResource(R.mipmap.ic_pinyin_sound_filling_pressed);
                return true;
            }
        });
    }

}
