package qornanali.newsty.features.listsources

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import org.jetbrains.anko.find
import qornanali.newsty.R
import qornanali.newsty.features.headlines.ListSourcesPresenter
import qornanali.newsty.features.headlines.ListSourcesView
import qornanali.newsty.model.Source
import qornanali.newsty.util.OnItemClickListener
import qornanali.newsty.util.adapter.ListSourcesAdapter

class ListSourcesActivity : AppCompatActivity(), ListSourcesView {


    private lateinit var rvSources: RecyclerView
    private lateinit var pbSources: ProgressBar
    private lateinit var toolbar: Toolbar
    private lateinit var adapter: ListSourcesAdapter
    private var sources = ArrayList<Source>()
    private lateinit var presenter: ListSourcesPresenter


    override fun insertListSources(data: List<Source>?) {
        data?.let {
            sources.addAll(it)
            adapter.notifyDataSetChanged()
        }
    }

    override fun loadingData(isNotFinished: Boolean) {
        pbSources.visibility = if (isNotFinished) View.VISIBLE else View.GONE
        rvSources.visibility = if (isNotFinished) View.GONE else View.VISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listsources)

        init()

        presenter.getSources()
    }

    private fun init() {
        pbSources = find(R.id.pb_sources)

        toolbar = find(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.do_choose_sources)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        rvSources = find(R.id.rv_sources)
        rvSources.layoutManager = GridLayoutManager(this, 2)

        adapter = ListSourcesAdapter(sources)
        adapter.setOnItemClickListener(OnItemClickListener {
            //
        })

        rvSources.adapter = adapter

        presenter = ListSourcesPresenter(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}