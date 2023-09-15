package com.app.tools

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.webkit.*
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class EasyWebView(context: Context) : WebView(context), DefaultLifecycleObserver {
    private var lifecycleOwner: LifecycleOwner? = null
    private lateinit var myProgress: (Int) -> Unit
    private lateinit var myPageFinished: (String) -> Unit


    fun go(path: String, lifecycleObserver: LifecycleOwner) {
        bindToLifecycle(lifecycleObserver)
        if (path.isNotEmpty()) {
            load(false, path)
        }
    }

    fun goAntoPath(
        path: String = "",
        lifecycleObserver: LifecycleOwner,
        progress: (Int) -> Unit = {},
        pageFinished: (String) -> Unit = {},
        pageStart: () -> Unit = {},
        isPost: Boolean = false
    ) {
        this.myProgress = progress
        this.myPageFinished = pageFinished
        bindToLifecycle(lifecycleObserver)
        if (path.isNotEmpty()) {
            pageStart.invoke()
            val newPath: String = if (path.contains("http")) {
                path
            } else {
                "file://$path"
            }
            load(isPost = isPost, url = newPath)
        }
    }

    fun addSettings(): EasyWebView {
        try {
            settings.apply {
                enableJavaScript(settings = this)
                setSupportZoom(true)
                builtInZoomControls = false
                textZoom = 100
                databaseEnabled = true
                loadsImagesAutomatically = true
                setSupportMultipleWindows(false)
                // 是否阻塞加载网络图片  协议http or https
                blockNetworkImage = false
                // 允许加载本地文件html  file协议
                allowFileAccess = true
                loadWithOverviewMode = false
                useWideViewPort = false
                domStorageEnabled = true
                setNeedInitialFocus(true)
                defaultTextEncodingName = "utf-8" //设置编码格式
                setGeolocationEnabled(true)
                setMixedContentMode(settings = this)
                setLayerType(LAYER_TYPE_HARDWARE, null)

                javaScriptCanOpenWindowsAutomatically = true
                layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return this
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun enableJavaScript(enabled: Boolean = true, settings: WebSettings?): EasyWebView {
        settings?.javaScriptEnabled = enabled
        return this
    }

    //设置兼容模式
    private fun setMixedContentMode(settings: WebSettings?): EasyWebView {
        //适配5.0不允许http和https混合使用情况
        if (BuildConfig.DEBUG) {
            settings?.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW//不安全
        } else {
            settings?.mixedContentMode = WebSettings.MIXED_CONTENT_NEVER_ALLOW//安全
        }
        return this
    }


    fun setWebChromeClient(): EasyWebView {
        webChromeClient = MyWebChromeClient()
        return this
    }

    fun setCustomWebViewClient(): EasyWebView {
        webViewClient = CustomWebViewClient()
        return this
    }


    fun addJavaScriptAny(any: Any?, name: String): EasyWebView {
        any?.let { addJavascriptInterface(it, name) }
        return this
    }


    fun evaluateJavascriptAny(name: String, params: String = "") {
        if (name.isEmpty()) return
        val sb = StringBuilder()
        sb.append("javascript:$name")
        if (params.isEmpty()) {
            sb.append("()")
        } else sb.append("(").append(concat(params)).append(")")
        ex(scr = sb.toString())
    }


    private fun ex(scr: String) {
        post { evaluateJavascript(scr) {} }
    }


    private fun concat(vararg params: String): String {
        val mStringBuilder = StringBuilder()
        for (i in params.indices) {
            val param = params[i]
            if (!isJsonOb(param)) {
                mStringBuilder.append("\"").append(param).append("\"")
            } else {
                mStringBuilder.append(param)
            }
            if (i != params.size - 1) {
                mStringBuilder.append(" , ")
            }
        }
        return mStringBuilder.toString()
    }

    private fun isJsonOb(target: String): Boolean {
        if (TextUtils.isEmpty(target)) {
            return false
        }
        val tag: Boolean = try {
            if (target.startsWith("[")) {
                JSONArray(target)
            } else {
                JSONObject(target)
            }
            true
        } catch (ignore: JSONException) {
            false
        }
        return tag
    }


    private fun load(isPost: Boolean, url: String) {
        if (isPost) {
            postUrl(url, byteArrayOf())
        } else loadUrl(url)
    }

    fun deCanGoBack(): Boolean = canGoBack()


    fun back() = goBack()

    private inner class MyWebChromeClient : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            myProgress.invoke(newProgress)
        }
    }

    private inner class CustomWebViewClient : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            myPageFinished.invoke(url ?: "")
        }

        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            return false
        }
    }


    private fun bindToLifecycle(owner: LifecycleOwner?) {
        lifecycleOwner = owner
        owner?.lifecycle?.addObserver(this)
    }

    override fun onResume(owner: LifecycleOwner) {
        this.onResume()
        super<DefaultLifecycleObserver>.onResume(owner)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        this.destroy()
        super.onDestroy(owner)
    }

    override fun onPause(owner: LifecycleOwner) {
        this.onPause()
        super<DefaultLifecycleObserver>.onPause(owner)
    }
}