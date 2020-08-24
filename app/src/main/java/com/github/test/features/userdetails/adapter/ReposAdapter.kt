package com.github.test.features.userdetails.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.test.R
import com.github.test.entity.response.UserRepoEntity
import kotlinx.android.synthetic.main.repo_item.view.*
import java.util.*

class ReposAdapter(val clickListener: RepoClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mItems = ArrayList<UserRepoEntity>()

    @Synchronized
    fun setItems(repos: List<UserRepoEntity>) {
        mItems.clear()
        mItems.addAll(repos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            RepoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false))

    override fun getItemCount(): Int = mItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RepoViewHolder) {
            val car = mItems[position]
            holder.bind(car)
        }
    }

    inner class RepoViewHolder internal constructor(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(repoEntity: UserRepoEntity) {
            itemView.setOnClickListener {
                clickListener.onRepoClicked(repoEntity)
            }
            val forks = itemView.context.getString(R.string.repo_forks)
            val stargazers = itemView.context.getString(R.string.repo_stargazers)
            itemView.repo_name.text = repoEntity.name
            itemView.repo_stars.text = String.format(stargazers, repoEntity.stargazersCount)
            itemView.repo_forks.text = String.format(forks, repoEntity.forksCount)
        }
    }

}