package qornanali.newsty.util.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import qornanali.newsty.R
import qornanali.newsty.model.Article
import qornanali.newsty.model.Source
import qornanali.newsty.util.OnItemClickListener
import qornanali.newsty.util.holder.ItemSourceHolder

class ListSourcesAdapter(val list: ArrayList<Source> = ArrayList()) : RecyclerView.Adapter<ItemSourceHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener<Source>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemSourceHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_source, parent, false)
        return ItemSourceHolder(view)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener<Source>) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onBindViewHolder(holder: ItemSourceHolder, position: Int) {
        holder.bind(list.get(position))
        holder.itemView.setOnClickListener { it ->
            if(onItemClickListener != null){
                onItemClickListener.onClicked(list.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}