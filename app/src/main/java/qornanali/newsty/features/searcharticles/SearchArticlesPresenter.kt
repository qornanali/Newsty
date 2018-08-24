package qornanali.newsty.features.searcharticles

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import qornanali.newsty.api.ApiRepository
import qornanali.newsty.api.TheNewsOrg
import qornanali.newsty.model.GetArticles

class SearchArticlesPresenter(private val searchArticlesView: SearchArticlesView) {

    fun getArticles(query: String) {
        searchArticlesView.loadingData(true)
        doAsync {
            val data = ApiRepository<GetArticles>().requestGet(
                    TheNewsOrg.getEverything(q = query), GetArticles::class.java)

            uiThread {
                if (data?.status.equals("ok")) {
                    searchArticlesView.insertListArticles(data?.articles)
                }
                searchArticlesView.loadingData(false)
            }
        }
    }


}