package com.example.domain.core

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

interface CoroutineDispatchers {
    val io: CoroutineContext
    val main: CoroutineContext
    val compute: CoroutineContext
}

class AppCoroutineDispatchers : CoroutineDispatchers {
    override val io: CoroutineContext = Dispatchers.IO
    override val main: CoroutineContext = Dispatchers.Main
    override val compute: CoroutineContext = Dispatchers.Default
}
