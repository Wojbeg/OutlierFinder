package com.wojbeg.outlierfinder.utils

import com.wojbeg.outlierfinder.data.model.InputStateResponse

object OutlierMath {

    /*
    * If only one element is outlier, so in first three numbers
    * there has to be at least 2 even or 2 odd numbers, so we can
    * decide if we want to filter by odd or even giving us
    * less complexity compared to filtering two arrays. For very big arrays
    * difference can be noticeable.
    * We can easily check if there are more than two outliers, by changing
    * find to filter and then check it's size, (Cn be used for further improvement)
    * but it's best answer, when it comes to checking big number arrays.
    *
    */

    fun findOutlier(numbers: List<Int>): InputStateResponse<Int> {

        val number = if (numbers.slice(0..2).filter { even(it) }.size >= 2) {
            numbers.find { odd(it) }
        } else {
            numbers.find { even(it) }
        }

        return if (number != null){
            InputStateResponse.Success<Int>(number)
        }else{
            InputStateResponse.NoOutlierError()
        }

    }

    fun even(number: Int): Boolean{
        return (number % 2 == 0)
    }

    fun odd(number: Int): Boolean{
        return !even(number)
    }

}