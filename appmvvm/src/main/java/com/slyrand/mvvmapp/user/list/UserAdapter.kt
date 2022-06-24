package com.slyrand.mvvmapp.user.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.slyrand.domain.user.model.User
import com.slyrand.mvvmapp.core.extensions.loadThumbnail
import com.slyrand.mvvmapp.databinding.ItemUserBinding
import kotlin.properties.Delegates

class UserAdapter(
    private val onUserClick: (User) -> Unit,
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    var users: List<User> by Delegates.observable(emptyList()) { _, old, new ->
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = old.size
            override fun getNewListSize(): Int = new.size
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldItem = old[oldItemPosition]
                val newItem = new[newItemPosition]
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return old[oldItemPosition] == new[newItemPosition]
            }
        }).dispatchUpdatesTo(this)
    }
    private lateinit var _binding: ItemUserBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(_binding, onUserClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    class ViewHolder(
        private val binding: ItemUserBinding,
        private val onUserClick: (User) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            with(binding) {
                userName.text = "${user.firstName} ${user.lastName}"
                userThumbnail.loadThumbnail(user.picture)
                root.setOnClickListener { onUserClick(user) }
            }
        }
    }
}