package com.example.bosta_task.presentation.view.profile.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bosta_task.R
import com.example.bosta_task.databinding.FragmentProfileBinding
import com.example.bosta_task.presentation.view.profile.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val profileViewModel by viewModels<ProfileViewModel>()
    private lateinit var albumAdapter: AlbumAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        initObservers()
    }

    private fun initViews() {
        albumAdapter = AlbumAdapter {
            val action = ProfileFragmentDirections.actionProfileFragmentToAlbumDetailsFragment2(it)
            findNavController().navigate(action)
        }
        binding.rvAlbums.apply {
            adapter = albumAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            profileViewModel.getUserById()
            profileViewModel.profileState.collectLatest { profileState ->
                if (profileState.loading) {
                    showLoadingState()
                } else {
                    populateUi(profileState)
                }
            }
            profileViewModel.errorState.collect {
                showErrorState(it)
            }
        }
    }

    private fun showLoadingState() {
        binding.apply {
            loading.visibility = View.VISIBLE
            loading.setAnimation(R.raw.loading)
            textView4.visibility = View.GONE
            textView6.visibility = View.GONE
        }
        Toast.makeText(requireContext(), "loading", Toast.LENGTH_SHORT).show()
    }

    private fun populateUi(profileState: ProfileState.Display) {
        binding.apply {
            loading.visibility = View.GONE
            textView4.visibility = View.VISIBLE
            textView6.visibility = View.VISIBLE
            tvName.text = profileState.userUiDto.name
            tvAddress.text =
                "${profileState.userUiDto.address.city},${profileState.userUiDto.address.suite}, ${profileState.userUiDto.address.street}, ${profileState.userUiDto.address.zipcode}"
        }
        albumAdapter.submitList(profileState.albums)
    }

    private fun showErrorState(errorState: ProfileState.Failure) {
        Toast.makeText(requireContext(), errorState.errorMsg, Toast.LENGTH_SHORT).show()
    }
}
