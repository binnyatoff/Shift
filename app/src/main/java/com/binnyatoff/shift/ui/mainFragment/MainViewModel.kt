package com.binnyatoff.shift.ui.mainFragment

import android.text.Editable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binnyatoff.shift.data.network.Api
import com.binnyatoff.shift.data.network.models.Binlist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface EventHandler<T> {
    fun obtainEvent(event: T)
}

sealed class MainFragmentState {
    object Init : MainFragmentState()
    object Loading : MainFragmentState()
    data class Loaded(val binList: Binlist) : MainFragmentState()
    data class Error(val errorMessage: String) : MainFragmentState()
}

sealed class MainFragmentEvent {
    data class GetBinInformation(val binNumber: Editable) : MainFragmentEvent()
}

class MainViewModel(private val api: Api) : ViewModel(), EventHandler<MainFragmentEvent> {
    private val _mainViewState = MutableLiveData<MainFragmentState>()
    val mainViewState: LiveData<MainFragmentState> = _mainViewState

    private fun getBinlist(binNumber: Editable) {
        var binNumber = binNumber.toString()
        binNumber = binNumber.filter { it != ' ' }
        _mainViewState.value = MainFragmentState.Loading
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val response = api.getBinlist(binNumber.toInt())
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null) {
                            _mainViewState.postValue(MainFragmentState.Loaded(body))
                            Log.e("TAG", body.toString())
                        }
                    }
                } catch (e: Exception) {
                    Log.e("TAG", e.toString())
                }
            }
        }
    }

    override fun obtainEvent(event: MainFragmentEvent) {
        when (event) {
            is MainFragmentEvent.GetBinInformation -> getBinlist(binNumber = event.binNumber)
        }
    }
}