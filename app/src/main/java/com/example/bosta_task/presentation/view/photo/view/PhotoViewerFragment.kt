package com.example.bosta_task.presentation.view.photo.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.GestureDetector
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.bosta_task.R
import com.example.bosta_task.databinding.FragmentPhotoViewerBinding

class PhotoViewerFragment : Fragment() {
    private lateinit var binding: FragmentPhotoViewerBinding
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private lateinit var gestureDetector: GestureDetector
    private var scaleFactor = 1.0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotoViewerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("QueryPermissionsNeeded", "ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUi()
        setGesturesListeners()
    }

    @SuppressLint("ClickableViewAccessibility", "QueryPermissionsNeeded")
    private fun initUi() {
        val photo = PhotoViewerFragmentArgs.fromBundle(requireArguments()).photo
        Glide.with(requireContext())
            .load(photo.url)
            .placeholder(R.drawable.reload)
            .error(R.drawable.reload)
            .into(binding.ivPhoto)

        binding.apply {
            tvPhotoName.text = photo.title
            backBtn.setOnClickListener {
                findNavController().popBackStack()
            }
            ivPhoto.setOnTouchListener { _, event ->
                scaleGestureDetector.onTouchEvent(event)
                gestureDetector.onTouchEvent(event)
                true
            }
            shareBtn.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, photo.thumbnailUrl)
                }
                val chooser = Intent.createChooser(intent, "Share My Image ${photo.title}")
                if (intent.resolveActivity(requireActivity().packageManager) != null) {
                    startActivity(chooser)
                }
            }
        }
    }

    private fun setGesturesListeners() {
        gestureDetector =
            GestureDetector(requireContext(), object : GestureDetector.SimpleOnGestureListener() {
                override fun onDoubleTap(e: MotionEvent): Boolean {
                    scaleFactor = if (scaleFactor == 1.0f) 2.0f else 1.0f

                    binding.ivPhoto.pivotX = e.x
                    binding.ivPhoto.pivotY = e.y

                    binding.ivPhoto.scaleX = scaleFactor
                    binding.ivPhoto.scaleY = scaleFactor
                    return true
                }
            })

        scaleGestureDetector = ScaleGestureDetector(
            requireContext(),
            object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
                override fun onScale(detector: ScaleGestureDetector): Boolean {
                    scaleFactor *= detector.scaleFactor
                    scaleFactor = 1.0f.coerceAtLeast(scaleFactor)
                    scaleFactor = 2.0f.coerceAtMost(scaleFactor)

                    binding.ivPhoto.pivotX = detector.focusX
                    binding.ivPhoto.pivotY = detector.focusY

                    binding.ivPhoto.scaleX = scaleFactor
                    binding.ivPhoto.scaleY = scaleFactor
                    return true
                }
            })
    }
}
