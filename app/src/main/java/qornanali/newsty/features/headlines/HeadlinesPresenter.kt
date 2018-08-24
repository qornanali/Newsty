package qornanali.newsty.features.headlines

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import qornanali.newsty.api.ApiRepository
import qornanali.newsty.api.TheNewsOrg
import qornanali.newsty.model.GetArticles

class HeadlinesPresenter(private val listHeadlinesView: HeadlinesView) {

    fun getHeadlineArticles() {
        doAsync {
            val data = ApiRepository<GetArticles>().requestGet(
                    TheNewsOrg.getTopHeadlines(), GetArticles::class.java)

            uiThread {
                if(data?.status.equals("ok")){
                    listHeadlinesView.insertListArticles(data?.articles)
                }
            }
        }
    }

}