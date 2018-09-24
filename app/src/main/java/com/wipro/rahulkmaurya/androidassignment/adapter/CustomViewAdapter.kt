package com.wipro.rahulkmaurya.androidassignment.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import com.wipro.rahulkmaurya.androidassignment.R
import com.wipro.rahulkmaurya.androidassignment.model.Fact
import kotlinx.android.synthetic.main.custom_row.view.*

/**
 * Class used to <Inflate Recyclerview on the basis of fact data>
 *
 * Created by rahul.k.maurya on 2018-09-24.
 */
class CustomViewAdapter(private var facts: MutableList<Fact>) :
        RecyclerView.Adapter<CustomViewAdapter.ViewHolder>() {
    var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.custom_row, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fact = facts[position]
        holder.title.text = fact.title
        holder.description.text = fact.description
        holder.image.setImageResource(0)
        val builder = Picasso.Builder(context)
        builder.downloader(OkHttp3Downloader(context))
        if (fact.imageHref != null)
            builder.build().load(fact.imageHref.toString())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(holder.image)

        with(holder.itemView) {
            tag = fact
        }
    }

    override fun getItemCount(): Int {
        return facts.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.title
        val description: TextView = view.description
        val image: ImageView = view.image
    }
}