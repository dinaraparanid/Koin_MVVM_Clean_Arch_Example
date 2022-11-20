package com.paranid5.koinmvvmtest.view_models

import androidx.lifecycle.SavedStateHandle
import com.paranid5.koinmvvmtest.view_models.presenters.MainFragmentPresenter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class MainFragmentViewModel(savedStateHandle: SavedStateHandle) : ObservableViewModel<MainFragmentPresenter>() {
    companion object {
        const val NAME_CONTAINER_TEXT = "nameContainerText"
    }

    override val presenter by inject<MainFragmentPresenter> {
        parametersOf(savedStateHandle.getStateFlow<String?>(NAME_CONTAINER_TEXT, null).value)
    }

    private val _isConfirmNameButtonPressedState = MutableStateFlow(false)
    val isConfirmNameButtonPressedState = _isConfirmNameButtonPressedState.asStateFlow()

    @JvmName("onConfirmNameButtonPressed")
    fun onConfirmNameButtonPressed() {
        _isConfirmNameButtonPressedState.value = true
    }

    fun finishNameSetting() {
        _isConfirmNameButtonPressedState.value = false
    }
}