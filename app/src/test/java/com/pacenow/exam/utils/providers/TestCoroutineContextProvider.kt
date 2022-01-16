package com.pacenow.exam.utils.providers

import com.pacenow.exam.core.providers.CoroutineContextProvider
import com.pacenow.exam.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class TestCoroutineContextProvider(testCoroutineRule: TestCoroutineRule) :
    CoroutineContextProvider() {
    override var Main: CoroutineContext = testCoroutineRule.testCoroutineDispatcher
    override var IO: CoroutineContext = testCoroutineRule.testCoroutineDispatcher
}