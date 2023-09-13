# 离线Android SDK集成

## 1. 获取SDK的aar文件

客户（开发方）通过和中文之钥的合作，提供对应的硬件的设备码给中文之钥，运营方会给客户直接发放SDK包。

## 2. 将SDK包计入工程项目中的libs

客户从中文之钥营运放中拿到的SDK是以aar为后缀的依赖包，将对应的aar依赖包继承于项目中的libs包下面：

![libs集成](http://8.210.212.227:4999/server/index.php?s=/api/attachment/visitFile&sign=a5c885636d58a9512a643e49eb02d163 "libs集成")

## 3. build.gradle中配置依赖

在对应的module下面的build.gradle文件中，加入如下依赖：

```gradle
implementation files('libs/ckey_4468C36AFD28F984E7FC83C05D317DD4N91X5H5R.aar')
```

通过上面的方法，就已经是把对应的SDK包引入到了项目中了，重新编译下项目使SDK生效。

## 4. 初始化SDK

在Application的onCreate方法中初始化，代码如下：

```java
/**
* 其中imei为设备号、设备识别码，
* packageName为项目包名
**/
public void onCreate(){
        ChinesekeyConfigure.preInit(this, imei,packageName);
}
```

## 5. 使用SDK

初始化完中文之钥SDK后，接下来就可以通过调用中文之钥SDK的方法来使用中文之钥SDK的服务了。

现在的中文之钥SDK暂时只提供两个方法：

```java
/**
 * 调用getChineseCharacterByName方法获取所指定汉字信息
 * 返回指定的单个汉字的信息， 类型为： ChineseCharacter
 **/
ChineseCharacter character = ChinesekeyConfigure.getChineseCharacterByName(this,wordName);


/**
 * 调用getAllChineseCharacter方法获取所有汉字信息
 * 返回数据是ChineseCharacter类型的数据集合
 **/
List<ChineseCharacter> list = ChinesekeyConfigure.getAllChineseCharacter(this);
```

## 6. 其他

现在，您已经成功集成中文之钥SDK到您的项目中，您还可以通过直接在github上面下载[中文之钥SDK demo](https://github.com/Chinesekey/Offline-SDK-Demo)来查看更多的代码细节。