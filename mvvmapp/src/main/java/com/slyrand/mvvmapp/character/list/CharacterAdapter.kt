package com.slyrand.mvvmapp.character.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.slyrand.domain.character.model.Character
import com.slyrand.mvvmapp.databinding.ItemCharacterBinding

class CharacterAdapter(
    private val onCharacterClick: (Character) -> Unit,
) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    var characters: List<Character> = listOf()
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