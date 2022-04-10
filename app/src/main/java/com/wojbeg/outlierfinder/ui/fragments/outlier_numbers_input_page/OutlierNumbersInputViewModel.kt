package com.wojbeg.outlierfinder.ui.fragments.outlier_numbers_input_page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wojbeg.outlierfinder.data.model.InputStateResponse

import com.wojbeg.outlierfinder.utils.Constants
import com.wojbeg.outlierfinder.utils.OutlierMath.findOutlier
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OutlierNumbersInputViewModel @Inject constructor(
): ViewModel() {

    private var _inputStateLiveData = MutableLiveData<InputStateResponse<Int>>()
    val inputStateLiveData: LiveData<InputStateResponse<Int>> get() = _inputStateLiveData

    fun handleUserInput(numbersInput: String) = viewModelScope.launch {
        _inputStateLiveData.value = InputStateResponse.Loading()

        val numbersSeparated = numbersInput.split(Constants.NUMBER_SEPARATOR)

        try {
            val numbers = numbersSeparated.map { number ->
                number.replace(" ", "").toInt()
            }
            if(numbers.size >= 3) {
                _inputStateLiveData.value = findOutlier(numbers)
            }else{
                _inputStateLiveData.value = InputStateResponse.SizeInputError()
            }
        }catch (e: NumberFormatException){
            _inputStateLiveData.value = InputStateResponse.UserCharacterInputError()
        }
    }

    fun resetState(){
        _inputStateLiveData.value = InputStateResponse.Normal()
    }

}