package qornanali.newsty.features.listarticles

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.qornanali.footballclubkt.util.adapter.ListArticlesPagerAdapter
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import qornanali.newsty.R
import qornanali.newsty.features.searcharticles.SearchArticlesActivity
import qornanali.newsty.model.Source

class ArticleBySourceActivity : AppCompatActivity(), ArticleBySourceView {

    private lateinit var presenter: ArticleBySourcePresenter
    private lateinit var viewPager: ViewPager
    private lateinit var toolbar: Toolbar
    private lateinit var source: Source
    private lateinit var pagerAdapter: ListArticlesPagerAdapter
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articlebysource)

        init()

        presenter.loadTabs(resources)
    }

    fun getSourceName(): String?{
        return source.id
    }

    override fun showTabs(tabs: ArrayList<String>) {
        for (i in 0 .. tabs.size - 1){
            pagerAdapter.addFragment(ListArticlesFragment(), tabs[i])
        }
        pagerAdapter.notifyDataSetChanged()
    }


    private fun init() {
        source = intent.extras.getSerializable("source") as Source

        toolbar = find(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = source.name
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewPager = find(R.id.view_pager)
        tabLayout = find(R.id.tab_layout)
        pagerAdapter = ListArticlesPagerAdapter(supportFragmentManager)

        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)

        presenter = ArticleBySourcePresenter(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_articlebysource, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
            }
            R.id.action_open_search -> {
                startActivity<SearchArticlesActivity>()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}