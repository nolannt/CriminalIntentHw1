package com.bignerdranch.android.criminalintent

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import java.io.File

class ZoomedImageFragment : Fragment() {

    companion object {
        private const val ARG_PHOTO_FILE_NAME = "photoFileName"

        fun newInstance(photoFileName: String): ZoomedImageFragment {
            val fragment = ZoomedImageFragment()
            val args = Bundle()
            args.putString(ARG_PHOTO_FILE_NAME, photoFileName)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_zoomed_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val photoFileName = arguments?.getString(ARG_PHOTO_FILE_NAME)
        if (!photoFileName.isNullOrBlank()) {
            val photoFile = File(requireContext().applicationContext.filesDir, photoFileName)
            if (photoFile.exists()) {
                val imageView: ImageView = view.findViewById(R.id.imageView)
                imageView.setImageBitmap(BitmapFactory.decodeFile(photoFile.absolutePath))

                // Close the fragment when the user clicks on the zoomed-in image
                imageView.setOnClickListener {
                    parentFragmentManager.beginTransaction().remove(this).commit()
                }
            }
        }
    }
}