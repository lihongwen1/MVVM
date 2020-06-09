package com.chenxuan.common.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * @author cx
 */
abstract class BaseViewModel : ViewModel() {
    /**
     * 协程状态管理
     *
     * 开始 CoroutineState.START
     * 刷新 CoroutineState.START
     * 结束 CoroutineState.FINISH
     * 异常 CoroutineState.ERROR
     */
    val statusLiveData: MutableLiveData<CoroutineState> by lazy {
        MutableLiveData<CoroutineState>()
    }

    fun launch(refresh: Boolean = true, block: Block) {
        viewModelScope.launch {
            try {
                if (refresh) {
                    statusLiveData.value = CoroutineState.REFRESH
                } else {
                    statusLiveData.value = CoroutineState.START
                }
                block()
                statusLiveData.value = CoroutineState.FINISH
            } catch (e: Exception) {
                statusLiveData.value = CoroutineState.ERROR
                //处理协程异常
            }
        }
    }
}