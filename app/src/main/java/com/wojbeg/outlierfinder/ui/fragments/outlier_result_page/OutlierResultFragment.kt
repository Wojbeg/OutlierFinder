package com.wojbeg.outlierfinder.ui.fragments.outlier_result_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wojbeg.outlierfinder.databinding.OutlierResultFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OutlierResultFragment : Fragment() {

    private lateinit var binding: OutlierResultFragmentBinding
    private val args: OutlierResultFragmentArgs by navArgs()
    private val viewModel: OutlierResultViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = OutlierResultFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView(){

        binding.apply {
            outlierNumberTv.text = args.outlierNumber.toString()

            backBtn.setOnClickListener {
                findNavController().navigateUp()
            }
        }

    }

}