package com.slyrand.mvvmapp.user.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.slyrand.mvvmapp.R
import com.slyrand.mvvmapp.core.extensions.loadThumbnail
import com.slyrand.mvvmapp.databinding.FragmentUserDetailBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.text.SimpleDateFormat
import java.util.*

class UserDetailFragment : Fragment(R.layout.fragment_user_detail) {

    private lateinit var _binding: FragmentUserDetailBinding
    private val _viewModel: UsersDetailViewModel by viewModel {
        parametersOf(arguments?.get("user_id"))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentUserDetailBinding.bind(view).apply {
            toolBar.setNavigationOnClickListener { findNavController().navigateUp() }
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    _viewModel.state.collect { state ->

                        state.user?.let { user ->
                            userThumbnail.loadThumbnail(state.user.picture)
                            userName.text = "${user.firstName} ${user.lastName}"
                            userEmail.setText(user.email)
                            userPhone.setText(user.phone)

                            val dateFormatter = SimpleDateFormat(
                                "dd / MM / yyyy",
                                Locale.getDefault()
                            )
                            user.dateOfBirth?.let {
                                userBirthDate.setText(dateFormatter.format(it))
                            }
                        }
                    }
                }
            }
        }
    }
}

