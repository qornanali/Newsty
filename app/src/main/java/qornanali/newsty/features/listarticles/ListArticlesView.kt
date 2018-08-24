package qornanali.newsty.features.listarticles

import qornanali.newsty.model.Article

interface ListArticlesView {

    fun insertListArticles(data: List<Article>?)
    fun loadingData(isNotFinished: Boolean)

}