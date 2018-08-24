package qornanali.newsty.api

import android.util.Log
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import qornanali.newsty.BuildConfig
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.net.URL
import java.security.KeyStore
import java.security.cert.Certificate
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory


class ApiRepository<T> {

    fun requestGet(url: String, c: Class<T>): T? {
        Log.i("wew", "get_request -> " + url)
        val client = OkHttpClient()
        val request = Request.Builder().url(url)
                .addHeader("X-Api-Key", BuildConfig.NEWS_API_KEY).build()
        val response = client.newCall(request).execute()

        return Gson().fromJson(response.body()?.string(), c)
    }

}