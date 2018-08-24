package qornanali.newsty.features.listarticles

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import qornanali.newsty.api.ApiRepository
import qornanali.newsty.api.TheNewsOrg
import qornanali.newsty.model.GetArticles

class ListArticlesPresenter(private val listArticlesView: ListArticlesView) {

    fun getArticlesByCategory(source: String?, category: String) {
        listArticlesView.loadingData(true)
        doAsync {
            val data = ApiRepository<GetArticles>().requestGet(
                    TheNewsOrg.getEverything(q = category, sources =  source), GetArticles::class.java)

            uiThread {
                if(data?.status.equals("ok")){
                    listArticlesView.insertListArticles(data?.articles)
                }
                listArticlesView.loadingData(false)
            }
        }
    }
}
