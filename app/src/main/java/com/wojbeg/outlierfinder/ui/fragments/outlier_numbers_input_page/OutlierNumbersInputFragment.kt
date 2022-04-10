package com.wojbeg.outlierfinder.ui.fragments.outlier_numbers_input_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wojbeg.outlierfinder.R
import com.wojbeg.outlierfinder.data.model.InputStateResponse
import com.wojbeg.outlierfinder.databinding.OutlierNumbersInputFragmentBinding
import com.wojbeg.outlierfinder.ui.dialogs.AboutDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OutlierNumbersInputFragment : Fragment() {

    private lateinit var binding: OutlierNumbersInputFragmentBinding
    private val viewModel: OutlierNumbersInputViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = OutlierNumbersInputFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {

        binding.apply {

            searchBtn.setOnClickListener {
                onCheckOutlierButtonClick()
            }

            infoBtn.setOnClickListener {
                onInfoButtonClick()
            }

            userNumberInputET.addTextChangedListener {
                numbersTextInputLayout.error = null
            }

        }

        observeState()

    }



    private fun observeState(){

        viewModel.inputStateLiveData.observe(viewLifecycleOwner){ response ->
            hideLoading()

            when(response){

                is InputStateResponse.Normal -> {
                    //Do nothing
                }

                is InputStateResponse.Loading -> {
                    showLoading()
                }

                is InputStateResponse.Success -> {
                    val action =
                        OutlierNumbersInputFragmentDirections.actionOutlierNumbersInputFragmentToOutlierResultFragment(response.data!!)
                    findNavController().navigate(action)
                    viewModel.resetState()
                }

                is InputStateResponse.UserCharacterInputError -> {
                    binding.numbersTextInputLayout.error = getString(R.string.wrong_character_error)
                }

                is InputStateResponse.NoOutlierError -> {
                    binding.numbersTextInputLayout.error = getString(R.string.no_outlier_error)
                }

                is InputStateResponse.SizeInputError -> {
                    binding.numbersTextInputLayout.error = getString(R.string.not_enough_numbers_error)
                }

                is InputStateResponse.Error -> {
                    //for further implementation, other errors
                }
            }
        }

    }

    //we are preventing user from clicking
    private fun showLoading(){
        binding.loadingProgressBar.visibility = View.VISIBLE
        searchButton(false)
    }

    private fun hideLoading(){
        binding.loadingProgressBar.visibility = View.GONE
        searchButton(true)
    }

    private fun searchButton(newState: Boolean) {
        binding.searchBtn.isClickable = newState
    }

    private fun onCheckOutlierButtonClick(){
        viewModel.handleUserInput(binding.userNumberInputET.text.toString())
    }

    private fun onInfoButtonClick(){
        AboutDialog().show(
            childFragmentManager, AboutDialog.TAG)
    }


}