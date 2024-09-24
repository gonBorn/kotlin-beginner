package arrow

import arrow.fx.coroutines.parMap
import arrow.fx.coroutines.parZip
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import model.User

// parX展示了我们对每个计算结果感兴趣
// raceN展示了我们对第一个计算结果感兴趣
object ParExec {
    @JvmStatic
    fun main(args: Array<String>) {
        runBlocking {
            listOf(1, 2, 3, 4, 5).parMap {
                getUser(it)
            }
        }
    }

    private suspend fun getUser(id: Int) {
        parZip(
            {
                getUserName(id)
            },
            {
                getGender(id)
            }
        ) { name, gender -> User(id, name, gender).also { println(it.toString()) } }
    }

    private suspend fun getUserName(id: Int): String {
        delay(5000)
        println("getUserName$id")
        return "User$id"
    }

    private suspend fun getGender(id: Int): String {
        delay(1000)
        println("getGender$id")
        return "Gender$id"
    }
}
