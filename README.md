一、根据设备号或者设备码，下载相对于的aar包，并把aar包放入项目libs中

二、把aar包的依赖添加进入项目中
implementation files('libs/ckey_4468C36AFD28F984E7FC83C05D317DD4N91X5H5R.aar')


三、项目引入
然后在Application的onCreate方法中初始化

/**
*其中imei为设备识别码，
*packageName为项目包名
**/
ChinesekeyConfigure.preInit(this, imei,packageName);


四、调用sdk中的方法，获取所有汉字信息
/**
*通过调用getAllChineseCharacter方法获取所有汉字信息
*返回数据是ChineseCharacter类型的数据集合
**/
List<ChineseCharacter> = ChinesekeyConfigure.getAllChineseCharacter(this)

五、通过所获取的汉字信息，根据业务需求展示相对于的汉字信息，以下是对应的数据信息的实体类。
public class ChineseCharacter implements Serializable {


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


}
