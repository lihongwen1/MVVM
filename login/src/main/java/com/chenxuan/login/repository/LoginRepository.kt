package com.chenxuan.login.repository

import com.chenxuan.bean.login.Chapters
import com.chenxuan.common.base.BaseRepository
import com.chenxuan.login.api.LoginApiService
import com.chenxuan.net.ApiServiceUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author cx
 */
class LoginRepository : BaseRepository() {
    suspend fun getChapters(): Chapters {
        val service = ApiServiceUtil.getApiService<LoginApiService>()
        return withContext(Dispatchers.IO) {
            service.getChapters()
        }
    }
}