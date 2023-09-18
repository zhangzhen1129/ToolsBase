# ToolsBase

[![](https://jitpack.io/v/zhangzhen1129/ToolsBase.svg)](https://jitpack.io/#zhangzhen1129/ToolsBase)


使用JitPack的方式, 引入库.
AGP 8.+ 根目录中的 settings.gradle
dependencyResolutionManagement {
    ...
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
AGP 8.- 根目录中的 build.gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
APP目录中的 build.gradle
dependencies {
 ...
 implementation 'com.github.zhangzhen1129:ToolsBase:1.0.8'
}
