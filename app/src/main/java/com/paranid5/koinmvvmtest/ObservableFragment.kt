package com.paranid5.koinmvvmtest

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.paranid5.koinmvvmtest.ui_handlers.UIHandler
import com.paranid5.koinmvvmtest.view_models.ObservableViewModel
import com.paranid5.koinmvvmtest.view_models.presenters.BasePresenter
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.androidx.scope.ScopeFragment

abstract class ObservableFragment<P : BasePresenter, VM : ObservableViewModel<P>, H : UIHandler, B : ViewDataBinding> :
    ScopeFragment() {
    protected abstract val viewModel: VM
    protected abstract val uiHandler: H
    protected abstract var binding: B
    protected abstract val stateChangesCallbacks: Array<StateChangedCallback>

    protected inner class StateChangedCallback(
        val state: StateFlow<Boolean>,
        val callback: suspend H.() -> Unit
    ) {
        suspend operator fun invoke() {
            state.collect { isChanged -> if (isChanged) callback(uiHandler) }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleUIStatesChanges()
    }

    private fun handleUIStatesChanges() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                stateChangesCallbacks.forEach { stateChangedCallback -> stateChangedCallback() }
            }
        }
    }
}