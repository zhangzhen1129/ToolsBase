# ToolsBase
[![](https://jitpack.io/v/zhangzhen1129/ToolsBase.svg)](https://jitpack.io/#zhangzhen1129/ToolsBase)
![GitHub](https://img.shields.io/github/license/zhangzhen1129/ToolsBase)


ToolsBase 是一个简单的 WebView 工具库，旨在帮助您轻松集成 WebView 到您的 Android 应用中。这个库提供了一种方便的方式来处理 Web 页面加载和交互。

## 集成方式

### 使用 JitPack 引入库

要使用 ToolsBase，您可以通过 JitPack 引入库到您的 Android 项目中。

#### AGP 8.+ 根目录中的 settings.gradle

打开您的 AGP 8.+ 根目录中的 `settings.gradle` 文件，并添加以下代码：

```groovy
dependencyResolutionManagement {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    
    implementation 'com.github.zhangzhen1129:ToolsBase:1.0.8'
}

```


