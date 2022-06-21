package com.slyrand.mvvmapp.character.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.slyrand.domain.character.model.Character
import com.slyrand.mvvmapp.databinding.ItemCharacterBinding
import kotlin.properties.Delegates

class CharacterAdapter(
    private val onCharacterClick: (Character) -> Unit,
) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    var characters: List<Character> by Delegates.observable(emptyList()) { _, old, new ->
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
    private lateinit var _binding: ItemCharacterBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(_binding, onCharacterClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int = characters.size

    class ViewHolder(
        private val binding: ItemCharacterBinding,
        private val onCharacterClick: (Character) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            with(binding) {
                characterName.text = character.name

                root.setOnClickListener { onCharacterClick(character) }
            }
        }
    }
}