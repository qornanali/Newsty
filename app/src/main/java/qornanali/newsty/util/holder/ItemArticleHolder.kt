package qornanali.newsty.util.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.qornanali.footballclubkt.util.DateFormatter
import com.squareup.picasso.Picasso
import qornanali.newsty.R
import qornanali.newsty.model.Article
import java.net.URL

class ItemArticleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val ivSourceLogo: ImageView = itemView.findViewById(R.id.iv_source_logo)
    private val tvArticleSource: TextView = itemView.findViewById(R.id.tv_article_source)
    private val tvArticleTitle: TextView = itemView.findViewById(R.id.tv_article_title)
    private val tvArticleDesc: TextView = itemView.findViewById(R.id.tv_article_desc)
    private val ivArticleImage: ImageView = itemView.findViewById(R.id.iv_article_image)

    fun bind(article: Article) {
        val x = article
        Picasso.get().load(article.urlToImage).resize(100, 100).centerInside().into(ivArticleImage)
        val url = URL(article.url)
        Picasso.get().load("https://logo.clearbit.com/" + url.protocol + "/" + url.authority)
                .resize(50, 50).centerInside()
                .into(ivSourceLogo)
        tvArticleDesc.text = article.description
        tvArticleTitle.text = article.title
        var source = ""
        try {
            val splitStrDate = article.publishedAt?.split("T")
            val strDate = splitStrDate?.get(0) + " " + splitStrDate?.get(1)?.substring(0, splitStrDate?.get(1)?.length-1)
            val articleDate = DateFormatter.toDate(strDate, "yyyy-MM-dd hh:mm:ss")
            source = DateFormatter.toString(articleDate, "dd MMM yy") + ", "
        } catch (e: Exception) {

        }
        tvArticleSource.text = source + article.source?.name
    }
}
