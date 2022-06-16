package com.slyrand.mvvmapp.character.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.slyrand.mvvmapp.R
import com.slyrand.mvvmapp.databinding.FragmentCharacterListBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterListFragment : Fragment(R.layout.fragment_character_list) {

    private lateinit var _binding: FragmentCharacterListBinding
    private lateinit var _adapter: CharacterAdapter
    private val _viewModel: CharactersListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentCharacterListBinding.bind(view).apply {
            _adapter = CharacterAdapter { }
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    _viewModel.state.collect { state ->
                        progress.visibility = if (state.loading) View.VISIBLE else View.GONE
                        _adapter.characters = state.characters
                    }
                }
            }
        }

    }
}