package com.example.menuapp.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.menuapp.APIData.RetrofitClient
import com.example.menuapp.Model.MenuModel
import com.example.menuapp.Model.OfferedBurger
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.Date

sealed class AppStateEnum {
    object idle: AppStateEnum()
    object loading: AppStateEnum()
    data class failure(val message: String): AppStateEnum()
    data class success(val menu: MenuModel): AppStateEnum()
}


class MenuViewModel : ViewModel() {
    private val _menuState = MutableLiveData<AppStateEnum>(AppStateEnum.idle)
    val menuState: LiveData<AppStateEnum?> get() = _menuState

    init {
        viewModelScope.launch {
            requestData()
        }
    }

    // Helfer-Methode für Mocking in der Preview
    fun setMockState(state: AppStateEnum) {
        _menuState.value = state
    }

    private suspend fun requestData() {
        _menuState.value = AppStateEnum.loading
        val result = runCatching {
            val response = RetrofitClient.menuAPIService.requestData()
            if (response.isSuccessful) {
                response.body() ?: throw IllegalStateException("Empty response body")
            } else {
                throw IOException("API Request failed: ${response.code()} ${response.message()}")
            }
        }

        result.onSuccess { menu ->
            _menuState.value = AppStateEnum.success(menu)
        }.onFailure { e ->
            _menuState.value = AppStateEnum.failure(e.localizedMessage ?: "Unknown error")
            Log.e("MenuViewModel", "Error during request", e)
        }
    }

    fun isAvailable(burger: OfferedBurger) : Boolean {
        if (burger.info.availableUntil != null) {
                return Date().before(burger.info.availableUntil)
        }
        return true
    }



}