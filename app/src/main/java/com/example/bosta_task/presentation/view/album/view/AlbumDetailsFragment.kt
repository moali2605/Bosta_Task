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
        val album = AlbumDetailsFragmentArgs.fromBundle(requireArguments()).album
        albumViewModel.getPhotoByAlbumId(album.id.toString())
        binding.tvAlbumDetailsName.text = album.title
        bindViews()
        initListeners()
        initObserver()
    }

    private fun bindViews() {
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
    }

    private fun initListeners() {

        binding.tfSearch.doOnTextChanged { text, _, _, _ ->
            albumViewModel.searchByTitle(text.toString())
            photosAdapter.submitList(albumViewModel.albumState.value.filteredPhotos)
            if (text.toString().isBlank()) {
                photosAdapter.submitList(albumViewModel.albumState.value.photoUiDto)
            }
        }
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            albumViewModel.albumState.collectLatest { albumState ->
                if (albumState.loading) {
                    showLoadingState()
                } else {
                    showDataState(albumState)
                }
            }
            albumViewModel.errorState.collect {
                showErrorState(it)
            }
        }
    }

    private fun showLoadingState() {
        binding.apply {
            loadingPhotos.visibility = View.VISIBLE
            loadingPhotos.setAnimation(R.raw.loading)
            textField2.visibility = View.GONE
        }
    }

    private fun showDataState(albumState: AlbumState.Display) {
        binding.apply {
            loadingPhotos.visibility = View.GONE
            textField2.visibility = View.VISIBLE
        }

        photosAdapter.submitList(albumState.filteredPhotos)
    }

    private fun showErrorState(errorState: AlbumState.Failure) {
        Toast.makeText(requireContext(), errorState.errorMsg, Toast.LENGTH_SHORT).show()
    }
}
