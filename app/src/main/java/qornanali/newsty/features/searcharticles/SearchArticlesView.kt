package qornanali.newsty.features.searcharticles

import qornanali.newsty.model.Article

interface SearchArticlesView {

    fun insertListArticles(data: List<Article>?)
    fun loadingData(isNotFinished: Boolean)

}