package com.paranid5.koinmvvmtest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.paranid5.koinmvvmtest.databinding.FragmentMainBinding
import com.paranid5.koinmvvmtest.ui_handlers.MainFragmentUIHandler
import com.paranid5.koinmvvmtest.view_models.MainFragmentViewModel
import com.paranid5.koinmvvmtest.view_models.presenters.MainFragmentPresenter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : ObservableFragment<
        MainFragmentPresenter,
        MainFragmentViewModel,
        MainFragmentUIHandler,
        FragmentMainBinding
>() {
    override val viewModel by viewModel<MainFragmentViewModel>()
    override val uiHandler by inject<MainFragmentUIHandler>()
    override lateinit var binding: FragmentMainBinding

    override val stateChangesCallbacks by lazy {
        arrayOf(
            StateChangedCallback(state = viewModel.isConfirmNameButtonPressedState) {
                onConfirmNameButtonPressed(
                    name = binding.nameInput.text.toString(),
                    presenter = viewModel.presenter
                )

                viewModel.finishNameSetting()
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
            .inflate<FragmentMainBinding>(
                inflater,
                R.layout.fragment_main,
                container,
                false
            )
            .apply { viewModel = this@MainFragment.viewModel }

        return binding.root
    }
}