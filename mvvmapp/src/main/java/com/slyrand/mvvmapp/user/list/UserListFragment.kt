package com.slyrand.mvvmapp.user.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.slyrand.mvvmapp.R
import com.slyrand.mvvmapp.databinding.FragmentUserListBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserListFragment : Fragment(R.layout.fragment_user_list) {

    private lateinit var _binding: FragmentUserListBinding
    private lateinit var _adapter: UserAdapter
    private val _viewModel: UsersListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentUserListBinding.bind(view).apply {

            _adapter = UserAdapter { }
            usersList.adapter = _adapter
            usersList.layoutManager = GridLayoutManager(requireContext(), 2)

            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    _viewModel.state.collect { state ->
                        progress.visibility = if (state.loading) View.VISIBLE else View.GONE
                        _adapter.users = state.users
                    }
                }
            }
        }
    }
}