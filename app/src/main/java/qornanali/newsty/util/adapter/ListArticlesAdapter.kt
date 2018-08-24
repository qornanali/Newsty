package qornanali.newsty.util.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import qornanali.newsty.R
import qornanali.newsty.model.Article
import qornanali.newsty.util.OnItemClickListener
import qornanali.newsty.util.holder.ItemArticleHolder

class ListArticlesAdapter(val list: ArrayList<Article> = ArrayList()) : RecyclerView.Adapter<ItemArticleHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener<Article>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemArticleHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_article, parent, false)
        return ItemArticleHolder(view)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener<Article>) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onBindViewHolder(holder: ItemArticleHolder, position: Int) {
        holder.bind(list.get(position))
    }

    override fun getItemCount(): Int {
        return list.size
    }
}