package qornanali.newsty.features.listarticles

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import qornanali.newsty.R
import qornanali.newsty.features.detailarticle.DetailArticleActivity
import qornanali.newsty.model.Article
import qornanali.newsty.util.OnItemClickListener
import qornanali.newsty.util.adapter.ListArticlesAdapter

class ListArticlesFragment : Fragment(), ListArticlesView {

    private lateinit var rvArticles: RecyclerView
    private lateinit var pbArticles: ProgressBar
    private lateinit var adapter: ListArticlesAdapter
    private var articles = ArrayList<Article>()
    private lateinit var presenter: ListArticlesPresenter


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

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val rootView = inflater.inflate(R.layout.fragment_listarticles, container, false)

        init(rootView)

        arguments?.takeIf { it.containsKey("category") }?.apply {
            presenter.getArticlesByCategory((activity as ArticleBySourceActivity).getSourceName(), getString("category"))
        }
        return rootView
    }

    private fun init(view : View){
        pbArticles = view.find(R.id.pb_articles)

        rvArticles = view.find(R.id.rv_articles)
        rvArticles.layoutManager = LinearLayoutManager(activity)
        rvArticles.isNestedScrollingEnabled = true

        adapter = ListArticlesAdapter(articles)
        adapter.setOnItemClickListener(OnItemClickListener {
            activity?.startActivity<DetailArticleActivity>("article" to it)
        })

        rvArticles.adapter = adapter

        presenter = ListArticlesPresenter(this)
    }

}