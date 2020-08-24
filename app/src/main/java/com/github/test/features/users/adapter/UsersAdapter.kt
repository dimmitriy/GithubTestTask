package com.github.test.features.users.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.test.R
import com.github.test.base.BaseViewHolder
import com.github.test.entity.response.UserDetailsResponse
import com.github.test.features.users.viewholder.ProgressViewHolder
import com.github.test.features.users.viewholder.UserViewHolder
import java.util.*

class UsersAdapter private constructor(private val mItems: MutableList<UserDetailsResponse>) :
    RecyclerView.Adapter<BaseViewHolder<UserDetailsResponse>>() {

    private var mShowProgress = false
    private var onClickListener: UserClickListener? = null

    constructor() : this(ArrayList<UserDetailsResponse>())

    fun cleanItems() {
        mItems.clear()
        notifyDataSetChanged()
    }

    fun addItems(items: List<UserDetailsResponse>) {
        if (mShowProgress) {
            hideProgress()
        }
        mItems.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<UserDetailsResponse>
            = if (viewType == ITEM_TYPE_USER) {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item, parent, false)
        UserViewHolder(itemView)
    } else {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.progress_item, parent, false)
        ProgressViewHolder(itemView)
    }

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: BaseViewHolder<UserDetailsResponse>, position: Int) {
        if (holder is UserViewHolder) {
            holder.setOnClickListener(onClickListener!!)
        }

        if (position == mItems.size + 1) {
            holder.onBind(null)
        } else {
            holder.onBind(mItems[position])
        }
    }

    override fun getItemCount(): Int =
        if (mShowProgress) {
            mItems.size + 1
        } else {
            mItems.size
        }

    override fun getItemViewType(position: Int): Int =
        if (position == mItems.size + 1) {
            ITEM_TYPE_PROGRESS
        } else {
            ITEM_TYPE_USER
        }

    fun showProgress() {
        if (mShowProgress) return
        mShowProgress = true
        notifyItemInserted(mItems.size + 2)
    }

    fun hideProgress() {
        if (!mShowProgress) return
        mShowProgress = false
        notifyItemRemoved(mItems.size + 1)
    }

    fun setOnClickListener(onClickListener: UserClickListener?) {
        this.onClickListener = onClickListener
    }

    companion object {
        const val ITEM_TYPE_PROGRESS = 0
        const val ITEM_TYPE_USER = 1
    }

}