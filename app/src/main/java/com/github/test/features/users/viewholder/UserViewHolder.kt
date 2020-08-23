package com.github.test.features.users.viewholder

import android.view.View
import com.github.test.base.BaseViewHolder
import com.github.test.entity.response.SearchUserEntity
import com.github.test.features.users.adapter.UserClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_item.view.*

class UserViewHolder internal constructor(private val view: View): BaseViewHolder<SearchUserEntity>(view) {

    private lateinit var clickListener: UserClickListener

    fun setOnClickListener(listener: UserClickListener) {
        this.clickListener = listener
    }

    override fun onBind(userEntity: SearchUserEntity) {
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