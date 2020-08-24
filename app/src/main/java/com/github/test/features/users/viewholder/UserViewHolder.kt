package com.github.test.features.users.viewholder

import android.view.View
import com.github.test.R
import com.github.test.base.BaseViewHolder
import com.github.test.entity.response.UserDetailsResponse
import com.github.test.features.users.adapter.UserClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_item.view.*

class UserViewHolder internal constructor(private val view: View): BaseViewHolder<UserDetailsResponse>(view) {

    private lateinit var clickListener: UserClickListener

    fun setOnClickListener(listener: UserClickListener) {
        this.clickListener = listener
    }

    override fun onBind(userEntity: UserDetailsResponse) {
        itemView.setOnClickListener {
            clickListener.onUserClicked(userEntity)
        }
        val name = itemView.context.getString(R.string.search_user_name)
        val repos = itemView.context.getString(R.string.search_user_repos)
        itemView.user_name.text = String.format(name, userEntity.login)
        itemView.user_repos.text = String.format(repos, userEntity.publicRepos)
        Picasso.get()
            .load(userEntity.avatarUrl)
            .into(itemView.user_image)
    }

}