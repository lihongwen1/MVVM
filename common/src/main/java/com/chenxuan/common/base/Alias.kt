package com.chenxuan.common.base

import kotlinx.coroutines.CoroutineScope

internal typealias Block = suspend CoroutineScope.() -> Unit