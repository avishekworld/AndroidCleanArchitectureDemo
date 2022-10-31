package com.example.cleanarchitecturedemo.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cleanarchitecturedemo.databinding.FragmentUserProfileBinding
import com.example.cleanarchitecturedemo.viewmodels.UseProfileViewModel
import com.example.cleanarchitecturedemo.viewmodels.UserProfileEvent
import com.example.cleanarchitecturedemo.viewmodels.UserProfileViewState
import com.example.domain.models.UserProfile
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserProfileFragment : Fragment() {

    private lateinit var binding: FragmentUserProfileBinding

    private val viewModel: UseProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                is UserProfileViewState.InitState -> {}
                is UserProfileViewState.ShowProcessingDialog -> showProcessingDialog(it.msg)
                is UserProfileViewState.HideProcessingDialog -> hideProcessingDialog()
                is UserProfileViewState.ShowUserProfile -> showUserProfile(it.userProfile)
            }
        }
        viewModel.onEvent(UserProfileEvent.GetUserProfile(1))
    }

    private fun showProcessingDialog(msg: String) {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProcessingDialog() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showUserProfile(userProfile: UserProfile) {
        binding.userProfileTextView.text = userProfile.toString()
    }
}
