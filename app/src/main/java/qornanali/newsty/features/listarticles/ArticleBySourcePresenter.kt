package qornanali.newsty.features.listarticles

import android.content.res.Resources
import qornanali.newsty.R

class ArticleBySourcePresenter(private val articleBySourceView: ArticleBySourceView) {

    fun loadTabs(resources : Resources){
        val tabs = ArrayList<String>()
        val categories = resources.getStringArray(R.array.categories)

        tabs.add(resources.getString(R.string.headlines))
        for (i in categories.indices) {
            tabs.add(categories[i])
        }

        articleBySourceView.showTabs(tabs)
    }
}