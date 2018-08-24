package qornanali.newsty.features.headlines

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import qornanali.newsty.R
import qornanali.newsty.features.detailarticle.DetailArticleActivity
import qornanali.newsty.features.listsources.ListSourcesActivity
import qornanali.newsty.model.Article
import qornanali.newsty.util.OnItemClickListener
import qornanali.newsty.util.adapter.ListArticlesAdapter

class HeadlinesActivity : AppCompatActivity(), HeadlinesView {

    private lateinit var rvArticles: RecyclerView
    private lateinit var pbArticles: ProgressBar
    private lateinit var adapter: ListArticlesAdapter
    private var articles = ArrayList<Article>()
    private lateinit var presenter: HeadlinesPresenter


    override fun insertListArticles(data: List<Article>?) {
        data?.let {
            articles.addAll(it)
            adapter.notifyDataSetChanged()
        }
    }

    override fun loadingData(isNotFinished: Boolean) {
        pbArticles.visibility = if(isNotFinished) View.VISIBLE else View.GONE
        rvArticles.visibility = if(isNotFinished) View.GONE else View.VISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_headlines)

        init()

        presenter.getHeadlineArticles()
    }

    private fun init() {
        pbArticles = find(R.id.pb_articles)

        rvArticles = find(R.id.rv_articles)
        rvArticles.layoutManager = LinearLayoutManager(this)
        rvArticles.isNestedScrollingEnabled = true

        adapter = ListArticlesAdapter(articles)
        adapter.setOnItemClickListener(OnItemClickListener {
            startActivity<DetailArticleActivity>("article" to it)
        })

        rvArticles.adapter = adapter

        presenter = HeadlinesPresenter(this)
    }

    fun onCvBrowseNewsClicked(view: View){
        startActivity<ListSourcesActivity>()
    }

}