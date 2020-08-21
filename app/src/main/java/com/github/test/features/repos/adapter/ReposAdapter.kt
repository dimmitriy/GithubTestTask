package com.github.test.features.repos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.test.R
import com.github.test.entity.response.SearchUserEntity
import com.github.test.entity.response.UserRepoEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.repo_item.view.*
import kotlinx.android.synthetic.main.user_item.view.*
import java.util.*

class ReposAdapter(val clickListener: RepoClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mItems = ArrayList<UserRepoEntity>()

    @Synchronized
    fun setItems(users: List<UserRepoEntity>) {
        mItems.clear()
        mItems.addAll(users)
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
            itemView.repo_name.text = repoEntity.name
            // TODO check what field is responsible for stars count
            itemView.repo_stars.text = repoEntity.stargazersCount.toString()
            itemView.repo_forks.text = repoEntity.forksCount.toString()
        }
    }

}