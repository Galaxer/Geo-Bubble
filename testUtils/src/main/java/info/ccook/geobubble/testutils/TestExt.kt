package info.ccook.geobubble.testutils

import info.ccook.geobubble.ui.StateViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
fun <S, R> StateViewModel<S, R>.assertState(
    vararg expected: S,
    call: () -> Unit
): Unit = runTest {
    assertState(expected.toList(), call)
}

@OptIn(ExperimentalCoroutinesApi::class)
fun <S, R> StateViewModel<S, R>.assertState(
    expected: List<S>,
    call: () -> Unit
): Unit = runTest {
    val testObserver = state.test(this)
    call()
    advanceUntilIdle()
    assertEquals(expected.size, testObserver.getValueCount())
    testObserver.assertValues(expected).finish()
}
