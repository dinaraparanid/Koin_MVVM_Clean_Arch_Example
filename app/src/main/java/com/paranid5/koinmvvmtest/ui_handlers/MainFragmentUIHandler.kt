package com.paranid5.koinmvvmtest.ui_handlers

import com.paranid5.koinmvvmtest.view_models.presenters.MainFragmentPresenter

class MainFragmentUIHandler : UIHandler {
    fun onConfirmNameButtonPressed(name: String, presenter: MainFragmentPresenter) {
        presenter.nameContainerText = "Hello, $name!"
    }
}