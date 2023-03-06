package info.ccook.geobubble.testutils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
fun <T> Flow<T>.test(scope: TestScope): TestObserver<T> {
    return TestObserver(scope, this)
}

@OptIn(ExperimentalCoroutinesApi::class)
class TestObserver<T>(
    scope: TestScope,
    flow: Flow<T>
) {
    private val values = mutableListOf<T>()

    private val job: Job = scope.launch(UnconfinedTestDispatcher(scope.testScheduler)) {
        flow.onEach { values.add(it) }.launchIn(this)
    }

    fun getValueCount(): Int {
        return values.size
    }

    fun assertNoValues(): TestObserver<T> {
        assertEquals(emptyList<T>(), this.values)
        return this
    }

    fun assertValues(vararg values: T): TestObserver<T> {
        assertValues(values.toList())
        return this
    }

    fun assertValues(values: List<T>): TestObserver<T> {
        assertEquals(values, this.values)
        return this
    }

    fun finish() {
        job.cancel()
    }
}
