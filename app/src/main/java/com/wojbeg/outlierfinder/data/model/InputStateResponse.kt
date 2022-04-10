package com.wojbeg.outlierfinder.data.model

sealed class InputStateResponse<T>(
    val data: T? = null,
    val message: String? = null,
) {

    class Normal<T>: InputStateResponse<T>()
    class Loading<T>: InputStateResponse<T>()
    class Success<T>(data: T): InputStateResponse<T>(data)
    open class Error<T>(message: String?=null, data: T? = null): InputStateResponse<T>(data, message)

    class NoOutlierError<T> : Error<T>()
    class UserCharacterInputError<T> : Error<T>()
    class SizeInputError<T> : Error<T>()
}