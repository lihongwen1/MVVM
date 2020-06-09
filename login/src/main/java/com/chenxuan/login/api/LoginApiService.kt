package com.chenxuan.login.api

import com.chenxuan.bean.login.Chapters
import com.chenxuan.common.base.BaseService
import retrofit2.http.GET

/**
 * @author cx
 * login module api
 */
interface LoginApiService : BaseService {
    @GET("/wxarticle/chapters/json")
    suspend fun getChapters(): Chapters
}