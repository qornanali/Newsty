package qornanali.newsty.features.listsources

import qornanali.newsty.model.Source

interface ListSourcesView {

    fun insertListSources(data: List<Source>?)
    fun loadingData(isNotFinished: Boolean)

}