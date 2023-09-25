package com.ckey.demo.bean;

import java.io.Serializable;
import java.util.List;

public class ChineseCharacterInfo implements Serializable {


    /**
     * 汉字ID
     */
    public Integer characterId;

    /**
     * 汉字编码
     */
    public String characterCode;

    /**
     * 汉字唯一标识
     */
    public String characterMark;

    /**
     * 汉字字形
     */
    public String characterName;

    /**
     * 汉字拼音
     */
    public String characterPinyin;

    /**
     * 汉字结构
     */
    public String characterStructure;

    /**
     * 汉字结构编码
     */
    public String characterStructureDiagram;

    /**
     * 汉字声符
     */
    public String characterAudio;

    /**
     * 汉字附图
     */
    public String characterScene;

    /**
     * 汉字书写动图
     */
    public String characterWrite;

    /**
     * 汉字附图-卡通图
     */
    public String characterCartoon;

    /**
     * 汉字书法
     */
    public String characterGlyph;

    /**
     * 汉字发音-女音
     */
    public String characterAudioWoman;

    /**
     * 汉字意符
     */
    public String characterIntend;

    /**
     * 汉字包含构件数量
     */
    public String characterComponentCount;

    /**
     * 汉字字体
     */
    public Integer characterFontLibrary;


    public String languageCode;

    public String characterLiteralSense;

    public String characterTranslation;

    public String characterResolve;

    public String characterEtymologyRemark;

    public String componentName;

    public String componentBasicInterpret;

    public String componentEtymologyRemark;

    public List<CharacterExhibitAudioInfo> characterExhibitIntend;

    public List<CharacterExhibitAudioInfo> characterExhibitAudio;



}
