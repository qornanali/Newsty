package qornanali.newsty.api

import android.net.Uri
import qornanali.newsty.BuildConfig

object TheNewsOrg {

    fun getTopHeadlines(category: String? = "",
                        sources: String? = "",
                        q: String? = "",
                        pageSize: Int? = 10,
                        pageNum: Int? = 1,
                        country: String = "us"): String {
        return Uri.parse(BuildConfig.BASE_NEWS_API_URL).buildUpon()
                .appendPath("top-headlines")
                .appendQueryParameter("category", category)
                .appendQueryParameter("sources", sources)
                .appendQueryParameter("q", q)
                .appendQueryParameter("pageSize", pageSize.toString())
                .appendQueryParameter("pageNum", pageNum.toString())
                .appendQueryParameter("country", country)
                .build()
                .toString()
    }

    fun getEverything(sources: String? = "",
                      sortBy: String? = "publishedAt",
                      q: String? = "",
                      pageSize: Int? = 10,
                      pageNum: Int? = 1): String {
        return Uri.parse(BuildConfig.BASE_NEWS_API_URL).buildUpon()
                .appendPath("everything")
                .appendQueryParameter("sources", sources)
                .appendQueryParameter("sortBy", sortBy)
                .appendQueryParameter("q", q)
                .appendQueryParameter("pageSize", pageSize.toString())
                .appendQueryParameter("pageNum", pageNum.toString())
                .build()
                .toString()
    }

    fun getSources(): String {
        return Uri.parse(BuildConfig.BASE_NEWS_API_URL).buildUpon()
                .appendPath("sources")
                .appendQueryParameter("country", "us")
                .build()
                .toString()
    }
}