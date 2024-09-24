package coroutine

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object CoroutineScopeExec {
    @JvmStatic
    /**
     * Hello
     * World 1
     * World 2
     * hello 1
     * hello 2
     * World!
     * Done
     */
    fun main(args: Array<String>) = runBlocking {
        coroutineScope {
            launch {
                delay(2000L)
                println("World 2")
            }
            launch {
                delay(1000L)
                println("World 1")
            }
            println("Hello")
        }
        println("hello 1")
        // res: 先打印hello, 程序开始运行的5s后打印world，

        val job = launch { // launch a new coroutine and keep a reference to its Job
            delay(1000L)
            println("World!")
        }
        println("hello 2")
        job.join() // wait until child coroutine completes
        println("Done")
    }

    /*
    A coroutineScope builder can be used inside any suspending function to perform multiple concurrent operations.
    挂起函数是一种特殊的函数，可以在协程（coroutine）中调用。
    它们允许协程挂起执行并在稍后恢复，而不会阻塞线程。这使得在执行 I/O 操作时可以更加高效地使用资源。
     */
    suspend fun doWorld() = coroutineScope { // this: CoroutineScope
        launch {
            delay(2000L)
            println("World 2")
        }
        launch {
            delay(1000L)
            println("World 1")
        }
        println("Hello")
    }
}

