package qornanali.newsty.features.headlines

import qornanali.newsty.model.Article

interface HeadlinesView {

    fun insertListArticles(data: List<Article>?)
    fun loadingData(isNotFinished: Boolean)

}