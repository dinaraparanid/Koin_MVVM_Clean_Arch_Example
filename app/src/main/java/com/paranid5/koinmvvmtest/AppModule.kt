package com.paranid5.koinmvvmtest

import com.paranid5.koinmvvmtest.ui_handlers.MainFragmentUIHandler
import com.paranid5.koinmvvmtest.view_models.MainFragmentViewModel
import com.paranid5.koinmvvmtest.view_models.presenters.BasePresenter
import com.paranid5.koinmvvmtest.view_models.presenters.MainFragmentPresenter
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.fragment.dsl.fragmentOf
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::MainFragmentViewModel)

    factory { (nameContainerText: String?) ->
        MainFragmentPresenter(
            nameContainerText = nameContainerText ?: androidApplication()
                .resources
                .getString(R.string.print_your_name)
        )
    }

    scope<MainActivity> { fragmentOf(::MainFragment) }
    singleOf(::MainFragmentUIHandler)
}