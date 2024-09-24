package arrow

import arrow.core.Either.*
import arrow.core.NonEmptyList
import arrow.core.raise.*
import io.kotest.matchers.shouldBe
import model.User

/**
 * 在context里定义error
 * having Raise<E> be part of the context or scope of the function
 */
object EitherErrorExec {
    @JvmStatic
    fun main(args: Array<String>) {
        /**
         *
         *  如果调用链中的某一行返回了 Left，则会立即终止后续执行，并且返回整个 either 块的结果为 Left。
         * 	如果每个调用都返回 Right，则表示操作成功，整个 either 块会返回最后一个计算的 Right 结果。
         */
        val either = either {
            getId(2)
            getName("name")
            check(2, "name", "male")
        }.mapLeft { println("Error: ${it.message}") }
        either shouldBe Right(User(2, "name", "male"))

        val either2 = either {
            getId(1)
            getId(3)
            getName(null)
            check(1, null, "")
        }.mapLeft { println("Error: ${it.message}") }
        either2 shouldBe Left(Unit)

        // fold(ifLeft: (left: A) -> C, ifRight: (right: B) -> C): C
        val either3 = either {
            getId(3)
            getName(null)
            check(1, null, "")
        }.fold(
            { println("Error: ${it.message}") },
            { println("Success: $it") }
        )
        either3 shouldBe Unit
    }
}

data class UserNotFoundException(val check: NonEmptyList<Any>) :
    Exception("${check.joinToString { it.toString() }} is invalid")

data class InvalidUserException(val msg: String) : Exception("$msg is invalid")

context(Raise<InvalidUserException>)
fun getId(id: Int) =
    // also 返回原始值，但是可以做一些操作，如print
    id.also { ensure(id % 2 == 0) { raise(InvalidUserException("id $id")) } }

context(Raise<InvalidUserException>)
fun getName(name: String?): String {
    // 这里不能用also简写，string?不能also
    ensureNotNull(name) { raise(InvalidUserException("name")) }
    return name
}

context(Raise<UserNotFoundException>)
fun check(id: Int, name: String?, gender: String?): User =
    /**
     * 一参是一个方法，怎么构造新error
     * 二参是可能raise error的部分
     *
     * 是把一个error变成另一个error
     *
     */
    withError(
        { UserNotFoundException(it) },
        {
            zipOrAccumulate(
                { getId(id) },
                { getName(name) },
                {
                    ensure(gender == "male" || gender == "female") { raise(InvalidUserException("gender")) }
                    // 非空断言
                    gender!!
                }
            ) { id1: Int, name2: String, gender2: String -> User(id1, name2, gender2) }
        })
