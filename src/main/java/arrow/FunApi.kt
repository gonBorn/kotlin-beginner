package arrow

import io.kotest.matchers.shouldBe
import model.User

object FunApi {
    @JvmStatic
    fun main(args: Array<String>) {
        val users = listOf(User(1, "zeyan"), User(2, "aaa"))

        // list -> map
        users.associateBy { it.id } shouldBe mapOf(1 to User(1, "zeyan"), 2 to User(2, "aaa"))
        users.associateBy({it.id}, {it.name}) shouldBe mapOf(1 to "zeyan", 2 to "aaa")
        // T -> pair
        users.associate { it.id to it.name } shouldBe mapOf(1 to "zeyan", 2 to "aaa")
        // key 不变
        users.associateWith { it.name } shouldBe mapOf(User(1, "zeyan") to "zeyan", User(2, "aaa") to "aaa")

        val users1 = listOf(User(1, "zeyan"), User(2, "aaa"), User(2, "bbb"))
        users1.groupBy { it.id } shouldBe mapOf(
            1 to listOf(User(1, "zeyan")),
            2 to listOf(User(2, "aaa"), User(2, "bbb")))
        users1.groupBy ({ it.id }, {it.name}) shouldBe mapOf(
            1 to listOf("zeyan"),
            2 to listOf("aaa", "bbb"))
    }
}