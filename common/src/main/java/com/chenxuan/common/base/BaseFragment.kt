package com.chenxuan.common.base

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.chenxuan.common.utils.dialog.DialogHelper
import kotlinx.coroutines.launch

/**
 * @author cx
 */
abstract class BaseFragment<V : BaseRepository, T : BaseViewModel<V>> : BaseSimpleFragment() {
    protected val viewModel: T by lazy {
        createViewModel()
    }

    override fun initViewModel() {
        super.initViewModel()
        initViewModelActions()
    }

    private fun initViewModelActions() {
        viewModel.statusLiveData.observe(this, Observer { status ->
            status?.run {
                when (this) {
                    CoroutineState.START -> {//协程开始
                        Log.d("coroutine", "开始")
                    }
                    CoroutineState.REFRESH -> {//协程开始&&进度菊花圈
                        Log.d("coroutine", "刷新")
                        activity?.run {
                            DialogHelper.getInstance().showProgress(this)
                        }
                    }
                    CoroutineState.FINISH -> {//协程结束
                        Log.d("coroutine", "结束")
                        DialogHelper.getInstance().dismissProgress()
                    }
                    CoroutineState.ERROR -> {//协程异常
                        Log.d("coroutine", "异常")
                        DialogHelper.getInstance().dismissProgress()
                    }
                }
            }
        })
    }

    abstract fun createViewModel(): T

    fun launch(block: Block) {
        lifecycleScope.launch {
            try {
                block()
            } catch (e: Exception) {
                //异常处理
            }
        }

    }
}