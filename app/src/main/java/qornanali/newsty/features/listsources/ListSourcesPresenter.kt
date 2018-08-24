package qornanali.newsty.features.headlines

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import qornanali.newsty.api.ApiRepository
import qornanali.newsty.api.TheNewsOrg
import qornanali.newsty.model.GetSources

class ListSourcesPresenter(private val listSourcesView: ListSourcesView) {

    fun getSources() {
        doAsync {
            val data = ApiRepository<GetSources>().requestGet(
                    TheNewsOrg.getSources(), GetSources::class.java)

            uiThread {
                if(data?.status.equals("ok")){
                    listSourcesView.insertListSources(data?.sources)
                }
            }
        }
    }

}