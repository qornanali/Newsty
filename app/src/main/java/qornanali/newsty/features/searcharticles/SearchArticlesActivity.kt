package qornanali.newsty.features.searcharticles

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import qornanali.newsty.R
import qornanali.newsty.features.detailarticle.DetailArticleActivity
import qornanali.newsty.features.listarticles.ArticleBySourceActivity
import qornanali.newsty.model.Article
import qornanali.newsty.util.OnItemClickListener
import qornanali.newsty.util.adapter.ListArticlesAdapter

class SearchArticlesActivity : AppCompatActivity(), SearchArticlesView {


    private lateinit var rvArticles: RecyclerView
    private lateinit var etQuery: EditText
    private lateinit var pbArticles: ProgressBar
    private lateinit var adapter: ListArticlesAdapter
    private var articles = ArrayList<Article>()
    private lateinit var presenter: SearchArticlesPresenter


    override fun insertListArticles(data: List<Article>?) {
        articles.clear()
        data?.let {
            articles.addAll(it)
            adapter.notifyDataSetChanged()
        }
    }

    override fun loadingData(isNotFinished: Boolean) {
        pbArticles.visibility = if (isNotFinished) View.VISIBLE else View.GONE
        rvArticles.visibility = if (isNotFinished) View.GONE else View.VISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searcharticles)

        init()
    }

    private fun init() {
        pbArticles = find(R.id.pb_articles)

        rvArticles = find(R.id.rv_articles)
        rvArticles.layoutManager = LinearLayoutManager(this)

        adapter = ListArticlesAdapter(articles)
        adapter.setOnItemClickListener(OnItemClickListener {
            startActivity<DetailArticleActivity>("article" to it)
        })

        rvArticles.adapter = adapter

        etQuery = find(R.id.et_query)
        etQuery.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                presenter.getArticles(editable.toString())
            }

            override fun beforeTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        presenter = SearchArticlesPresenter(this)
    }


    fun onIvBackClicked(view: View) {
        finish()
    }

}