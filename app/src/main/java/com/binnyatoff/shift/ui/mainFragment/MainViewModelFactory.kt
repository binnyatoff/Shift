package com.binnyatoff.shift.ui.mainFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.binnyatoff.shift.data.network.Api
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(val api: Api) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == MainViewModel::class.java)
        return MainViewModel(api = api) as T
    }
}