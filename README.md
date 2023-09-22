# ToolsBase
[![](https://jitpack.io/v/zhangzhen1129/ToolsBase.svg)](https://jitpack.io/#zhangzhen1129/ToolsBase)
![GitHub](https://img.shields.io/github/license/zhangzhen1129/ToolsBase)
![Kotlin 100%](https://img.shields.io/badge/Kotlin-100%25-blue)
![androidx 100%](https://img.shields.io/badge/androidx-100%25-blue)
![API 100%](https://img.shields.io/badge/API-21+-blue)



ToolsBase 是一个简单的 WebView 工具库，旨在帮助您轻松集成 WebView 到您的 Android 应用中。这个库提供了一种方便的方式来处理 Web 页面加载和交互，
使用 EasyWebView。

# 集成方式

## 使用 JitPack 引入库

要使用 ToolsBase，您可以通过 JitPack 引入库到您的 Android 项目中。

### AGP 8.+ 根目录中的 settings.gradle

打开您的 AGP 8.+ 根目录中的 `settings.gradle` 文件，并添加以下代码：

```groovy
dependencyResolutionManagement {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

AGP 8.- 根目录中的 build.gradle:

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

```
APP目录中的 build.gradle

```groovy
dependencies {
    
    implementation 'com.github.zhangzhen1129:ToolsBase:1.0.8'
}

```
## 使用 webView

```groovy
//简单加载网页  自动管理生命周期
EasyWebView.go((path : String, lifecycleObserver: LifecycleOwner)


//更多
EasyWebView.go( path: String = "",
        lifecycleObserver
        progress {},
        pageFinished{},
        pageStart{},)

progress 加载进度回调
pageFinished 页面完成监听
pageStart 加载开始监听

使用 progress 和 pageFinished 必须设置
setWebChromeClient()
setCustomWebViewClient()


//添加h5 交互 object  name
addJavaScriptAny()


//执行 js 方法 方法名 参数 json 字符串
evaluateJavascriptAny()


```

