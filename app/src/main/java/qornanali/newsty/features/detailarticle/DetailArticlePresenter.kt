package qornanali.newsty.features.detailarticle

import qornanali.newsty.model.Article

class DetailArticlePresenter(private val detailArticleView: DetailArticleView) {

    fun loadDetailArticle(article: Article){
        detailArticleView.loadUrl(article.url)
    }
}