package com.slyrand.mvvmapp.user.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.slyrand.domain.core.model.DataError
import com.slyrand.mvvmapp.R
import com.slyrand.mvvmapp.databinding.FragmentUserListBinding
import com.slyrand.mvvmapp.navigation.Navigator
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserListFragment : Fragment(R.layout.fragment_user_list) {

    private lateinit var _binding: FragmentUserListBinding
    private lateinit var _adapter: UserAdapter
    private lateinit var _navigator: Navigator
    private val _viewModel: UsersListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _navigator = Navigator(findNavController())
        _binding = FragmentUserListBinding.bind(view).apply {

            _adapter = UserAdapter { _navigator.navigateToUserDetail(it.id) }
            usersList.adapter = _adapter
            usersList.layoutManager = LinearLayoutManager(requireContext())

            pullToRefresh.setOnRefreshListener {
                pullToRefresh.isRefreshing = false
                _viewModel.refresh()
            }

            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    _viewModel.state.collect { state -> updateUi(state) }
                }
            }
        }
    }

    private fun updateUi(state: UsersListViewModel.UiState) {
        with(_binding) {
            if (state.error == null) emptyContentView.visibility = View.GONE
            else {
                emptyContentView.visibility = View.VISIBLE
                updateError(state.error)
            }

            progress.visibility = if (state.loading) View.VISIBLE else View.GONE
            _adapter.users = state.users
        }
    }

    private fun updateError(error: DataError) {
        with(_binding) {
            when (error) {
                is DataError.ConnectionError -> {
                    emptyContentView.setTitle(getString(R.string.connection_error_title))
                    emptyContentView.setSubtitle(getString(R.string.connection_error_subtitle))
                }
                is DataError.NoResultsFoundFor -> {
                    emptyContentView.setTitle(getString(R.string.no_results_error_title))
                    emptyContentView.setSubtitle(
                        String.format(
                            getString(R.string.connection_error_subtitle),
                            error.query)
                    )
                }
                else -> {
                    emptyContentView.setTitle(getString(R.string.generic_error_title))
                    emptyContentView.setSubtitle(getString(R.string.generic_error_subtitle))
                }
            }
        }
    }
}