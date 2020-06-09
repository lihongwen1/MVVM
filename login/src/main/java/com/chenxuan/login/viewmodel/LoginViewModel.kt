package com.chenxuan.login.viewmodel

import androidx.lifecycle.MutableLiveData
import com.chenxuan.common.base.BaseViewModel
import com.chenxuan.login.api.LoginApiService
import com.chenxuan.net.ApiServiceUtil
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginViewModel : BaseViewModel() {
    val contentLiveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getChapters() {
        launch {
            val service = ApiServiceUtil.getApiService<LoginApiService>()
            val chapters = withContext(Dispatchers.IO) {
                service.getChapters()
            }
            val json = Gson().toJson(chapters)
            contentLiveData.value = json
        }
    }
}