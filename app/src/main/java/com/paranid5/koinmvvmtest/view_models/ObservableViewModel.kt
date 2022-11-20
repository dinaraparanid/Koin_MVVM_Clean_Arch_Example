package com.paranid5.koinmvvmtest.view_models

import androidx.lifecycle.ViewModel
import com.paranid5.koinmvvmtest.view_models.presenters.BasePresenter
import org.koin.android.scope.AndroidScopeComponent
import org.koin.core.component.KoinComponent
import org.koin.core.scope.Scope

abstract class ObservableViewModel<P : BasePresenter> : ViewModel(), KoinComponent {
    abstract val presenter: P
}