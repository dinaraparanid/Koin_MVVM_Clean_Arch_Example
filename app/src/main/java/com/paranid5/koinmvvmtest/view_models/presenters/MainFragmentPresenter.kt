package com.paranid5.koinmvvmtest.view_models.presenters

import androidx.databinding.Bindable
import com.paranid5.koinmvvmtest.BR

class MainFragmentPresenter(nameContainerText: String) : BasePresenter() {
    @get:Bindable
    var nameContainerText = nameContainerText
        @JvmName("getNameContainerText") get
        @JvmName("setNameContainerText")
        set(value) {
            field = value
            notifyPropertyChanged(BR.nameContainerText)
        }
}