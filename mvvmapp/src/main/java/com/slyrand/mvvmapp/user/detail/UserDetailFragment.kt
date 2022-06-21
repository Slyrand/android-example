package com.slyrand.mvvmapp.user.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.slyrand.mvvmapp.R
import com.slyrand.mvvmapp.core.extensions.loadImage
import com.slyrand.mvvmapp.databinding.FragmentUserDetailBinding
import com.slyrand.mvvmapp.navigation.Navigator
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.navigation.koinNavGraphViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class UserDetailFragment : Fragment(R.layout.fragment_user_detail) {

    private lateinit var _binding: FragmentUserDetailBinding
    private val _viewModel: UsersDetailViewModel by viewModel {
        parametersOf(arguments?.get("user_id"))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentUserDetailBinding.bind(view).apply {
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    _viewModel.state.collect { state ->

                        state.user?.let {
                            userThumbnail.loadImage(state.user.picture)
                            userName.text = it.firstName
                        }
                    }
                }
            }
        }
    }
}

