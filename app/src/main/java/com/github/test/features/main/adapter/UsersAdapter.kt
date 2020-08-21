package com.github.test.features.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.test.R
import com.github.test.entity.response.SearchUserEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_item.view.*
import java.util.*

class UsersAdapter(val clickListener: UserClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mItems = ArrayList<SearchUserEntity>()

    @Synchronized
    fun setItems(users: List<SearchUserEntity>) {
        mItems.clear()
        mItems.addAll(users)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false))

    override fun getItemCount(): Int = mItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is UserViewHolder) {
            val car = mItems[position]
            holder.bind(car)
        }
    }

    inner class UserViewHolder internal constructor(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(userEntity: SearchUserEntity) {
            itemView.setOnClickListener {
                clickListener.onUserClicked(userEntity)
            }
            itemView.user_name.text = userEntity.login
            // TODO add request to get repos for a user
            itemView.user_repos.text = userEntity.score.toString()
            Picasso.with(view.context)
                .load(userEntity.avatarUrl)
                .into(itemView.user_image)
        }
    }

}