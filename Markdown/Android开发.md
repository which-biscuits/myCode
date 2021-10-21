# AndriodStudio项目结构

## .idea

存放自动生成的文件,无需手动编辑

## app

存放项目的代码文件以及资源文件

### libs

存放项目需要用到的第三包jar包

### release

非必要，可存在于项目目录下，存放打包后的apk文件

### src

#### androidTest

用来编写Android Test测试用例的， 进行自动化测试

#### test

用来编写Unit Test测试， 进行自动化测试

#### mian

存放项目源代码

#### res

存放资源文件（布局文件 / layout、字符串文件 / values、图片文件 / mipmap）

#### AndroidManifest.xml

注册四大组件，添加应用权限

### .gitignore

将app中的文件和目录排除在版本控制之外

### app.iml

IntelliJIDEA自动生成的文件

### build.gradle

app模块的gradle构建脚本，指定项目构建的相关配置

### proguard-rules.pro

混淆文件， 指定项目代码的混淆规则，为了防止apk文件被别人破解时采取混淆代码

## build

编译时自动生成的文件

## gradle

构建项目的gradle

gradle文件中的gradlewrapper配置文件,使用gradle warpper方式会自动联网下载gradle

**自定义:**File---Settings---Build,Exception,Deployment---gradle

## 文件

**.gitignore：**将指定的文件排除在版本控制之外

**build.gradle：**项目全局的gradle构建脚本，也是重要的文件之一

**gradle.properties：**全局的gradle配置文件，在这里配置的属性能影响到项目所有的gradle编译脚本

**gradlew&gradlew.bat：**用于在命令行界面下执行gradle命令， gradlew在linux和mac中执行， gradlew.bat则在windows下执行

**local.properties：**本机中的AndroidSDK的路径，一般自己生成，不需要修改

**XXX.iml：**表示该项目是InteliJIDEA项目

**setting.gradle：**用于指定项目中所有引入的模块，一般可自动生成，也可自行设置

# UI界面

一个Android应用的界面是由View和ViewGroup对象构建的。多个视图组件（View）可以存放在一个视图容器（ViewGroup）中

## 布局文件的创建

每个应用程序默认包含一个主界面布局文件，该文件位于项目的res/layout目录中

布局文件名称只能包含小写(a-z) /  (1-9) / ‘_’ 并且只能由小写字母开头

## 布局文件的启用

```java
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main(布局资源文件的名字));
    }
```

## 布局的类型

```xml
宽度    android:layout_width
高度    android:layout_height

match_parent: 强制性地使视图扩展至父元素大小
wrap_content: 强制性地使视图扩展以显示全部部分, 当前元素地宽高度只要能刚好包含里面地内容
```

## 控件单位

**px : **像素, 屏幕中可以显示最小元素单元, 在分辨率不同的手机上控件显示地大小不同

**pt : **磅数, 一磅等于1 / 72英寸, 一般pt都会作为字体地单位来显示. 在分辨率不同的手机上显示的大小也会不同

**dp : **密度无关像素, 又称dip 1dp单位在设备屏幕上总使等于1 / 160英寸, 使用dp的好处是与手机无关

**sp : **可伸缩像素, 与dp相同的设计理念, 推荐设置文字的大小时使用

## 相对布局

```xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">
</RelativeLayout>
```



|             属性声明             |       功能描述       |
| :------------------------------: | :------------------: |
|  android:layout_alignParentLeft  |  是否跟父布局左对齐  |
|  android:layout_alignParentTop   | 是否跟父布局顶部对齐 |
| android:layout_alignParentRight  |  是否跟父布局右对齐  |
| android:layout_alignParentBottom | 是否跟父布局底部对齐 |
|     android:layout_toRightOf     |   在指定控件地右边   |
|     android:layout_toLeftOf      |   在指定控件地左边   |
|       android:layout_above       |   在指定控件地上边   |
|       android:layout_below       |   在指定控件地下边   |
|   android:layout_alignBaseline   |  与指定控件水平对齐  |
|     android:layout_alignLeft     |   与指定控件左对齐   |
|    android:layout_alignRight     |   与指定控件右对齐   |
|     android:layout_alignTop      |  与指定控件顶部对齐  |
|    android:layout_alignBottom    |  与指定控件底部对齐  |

## 线性布局

android:orientation    设定线性布局方向

vertical    垂直    horizontal    水平

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">
</LinearLayout>
```

## 表格布局

**TableRow : **行对象, 