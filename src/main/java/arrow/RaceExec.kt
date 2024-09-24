package arrow

import arrow.fx.coroutines.raceN
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

object RaceExec {
    @JvmStatic
    fun main(args: Array<String>) {
        runBlocking {
            raceN(
                { delay(1000); "Task 1" },
                { delay(2000); "Task 2" },
                { delay(3000); "Task 3" },
            ).let { println(it) }
        }
    }
}