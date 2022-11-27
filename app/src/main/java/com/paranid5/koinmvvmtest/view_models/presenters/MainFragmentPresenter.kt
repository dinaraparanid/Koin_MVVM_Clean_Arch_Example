package com.paranid5.koinmvvmtest.view_models.presenters

import androidx.databinding.Bindable
import com.paranid5.koinmvvmtest.BR

private inline val CharSequence.isValidName
    get() = isNotEmpty() && all { it.isLetterOrDigit() || it == ' ' } && "  " !in this

class MainFragmentPresenter(nameContainerText: String) : BasePresenter() {
    @get:Bindable
    var nameContainerText = nameContainerText
        @JvmName("getNameContainerText") get
        @JvmName("setNameContainerText")
        set(value) {
            field = value
            notifyPropertyChanged(BR.nameContainerText)
        }

    @get:Bindable
    var isConfirmButtonActive = false
        @JvmName("isConfirmButtonActive") get
        @JvmName("setConfirmButtonActive")
        set(value) {
            field = value
            notifyPropertyChanged(BR.confirmButtonActive)
        }

    @JvmName("onInputNameTextChanged")
    fun onInputNameTextChanged(s: CharSequence?) {
        isConfirmButtonActive = s?.isValidName == true
    }
}