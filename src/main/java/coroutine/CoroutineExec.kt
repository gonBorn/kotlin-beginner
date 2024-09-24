package coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object CoroutineExec {
    @JvmStatic
    fun main(args: Array<String>) {
        // runBlocking开启一个阻塞当前线程的协程，它会返回内部代码块的结果
        val result = runBlocking {
            delay(1000L)
            "Result from runBlocking"
        }
        println(result)

        // 上面的协程结束了运行下面的
        runBlocking {
            // launch在协程中启动一个新的协程，非阻塞的，返回一个Job对象，可以用来取消协程。
            launch {
                bathTime(2000)
                println("sleeping")
            }
            launch {
                bathTime(5000)
                println("sleeping")
            }
        }

        /**
         * Result from runBlocking
         * Going to the bathroom
         * Going to the bathroom
         * Exiting the bathroom
         * sleeping
         * Exiting the bathroom
         * sleeping
         */
    }

    private suspend fun bathTime(time: Long) {
        println("Going to the bathroom")
        delay(time)
        println("Exiting the bathroom")
    }
}