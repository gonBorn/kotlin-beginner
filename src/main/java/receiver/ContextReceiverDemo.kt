package receiver

import model.User

// Kotlin 允许在一个函数中使用多个上下文接收器，它们可以通过逗号分隔。
object ContextReceiverDemo {
    context(User)
    private fun greet() {
        println("Hello, $id, $name, $gender")
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val user = User(1, "zeyan", "avator")
        with(user) {
            greet()
        }
    }
}