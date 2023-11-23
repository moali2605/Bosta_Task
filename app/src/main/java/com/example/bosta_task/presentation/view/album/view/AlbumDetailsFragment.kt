package com.example.bosta_task.presentation.view.album.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bosta_task.R
import com.example.bosta_task.databinding.FragmentAlbumDetailsBinding
import com.example.bosta_task.presentation.view.album.viewmodel.AlbumDetailsViewModel
import com.example.bosta_task.presentation.view.profile.view.ProfileFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AlbumDetailsFragment : Fragment() {
    private lateinit var binding: FragmentAlbumDetailsBinding
    private val albumViewModel by viewModels<AlbumDetailsViewModel>()
    private lateinit var photosAdapter: PhotosAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbumDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        photosAdapter = PhotosAdapter(requireContext()) {
            val action =
                AlbumDetailsFragmentDirections.actionAlbumDetailsFragmentToPhotoViewerFragment(it)
            findNavController().navigate(action)
        }
        binding.rvPhotos.apply {
            adapter = photosAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }

        binding.btnDetailBack.setOnClickListener {
            findNavController().popBackStack()
        }
        val album = AlbumDetailsFragmentArgs.fromBundle(requireArguments()).album
        binding.tvAlbumDetailsName.text = album.title
        albumViewModel.getPhotoByAlbumId(album.id.toString())
        lifecycleScope.launch {
            albumViewModel.albumState.collectLatest { albumState ->
                if (albumState.loading) {
                    binding.apply {
                        loadingPhotos.visibility = View.VISIBLE
                        loadingPhotos.setAnimation(R.raw.loading)
                        textField2.visibility = View.GONE
                    }
                } else {
                    binding.apply {
                        loadingPhotos.visibility = View.GONE
                        textField2.visibility = View.VISIBLE
                    }

                    photosAdapter.submitList(albumState.photoUiDto)
                    binding.tfSearch.doOnTextChanged { text, _, _, _ ->
                        albumViewModel.searchByTitle(text.toString())
                        photosAdapter.submitList(albumState.filteredPhotos)
                        if (text.toString().isBlank()) {
                            photosAdapter.submitList(albumState.photoUiDto)
                        }

                    }

                }
            }
            albumViewModel.errorState.collect {
                Toast.makeText(requireContext(), it.errorMsg, Toast.LENGTH_SHORT).show()
            }
        }
    }
}