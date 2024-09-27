package basicSyntax

import model.User

object OperationOnList {
    @JvmStatic
    fun main(args: Array<String>) {
        val tenX: (Int) -> Int = fun(x: Int): Int { return x * 10 }
        val tenX_v2: (Int) -> Int = fun(x: Int) = x * 10 // same as above
        val tenXFun: (Int) -> Int = { x: Int -> x * 10 }
        val tenXYFun: (Int, Int) -> Int = { x: Int, y: Int -> x * y * 10 }

        listOf(1, 2, 3).map(tenX).forEach { println(it) }
        listOf(1, 2, 3).map { x -> x * 10 }.forEach { println(it) }
        listOf(1, 2, 3).map { it * 10 }.forEach { println(it) }
        listOf(1, 2, 3).takeWhile { it < 3 }.forEach { println("takewihle" + it) }
        listOf(1, 2, 3).count { it < 3 }.also { println(it) }

        val strings = listOf("ze", "yan")

        // check out of range
        // strings.indices 是0到size-1的IntRange
        if (strings.size !in strings.indices) {
            println("size not in indices")
        }

        for (i in 1..3) {
            println("range$i")
        }

        println("down step range")
        for (i in 6 downTo 0 step 2) {
            println(i)
        }
        println("-----------------------------------")

        for (s in strings) {
            println(s)
        }
        println("-----------------------------------")

        for (index in strings.indices) {
            println(strings[index])
        }
        println("-----------------------------------")

        // 类型检查
        // 在if里写is判断类型，通过判断会自动对该对象进行类型转换
        // 如果方法返回可能是null,需要显式的在返回类型后写？
        println(1 is Int)
        println(1 !is Int)
        println("-----------------------------------")

        println("fold string list")
        strings.fold("") { acc: String, s: String ->
            "$acc$s ".also { println(it) }
        }
        println("-----------------------------------")

        // fold接收两个参数，第二个参数是lambda,kotlin允许我们把lambda放在括号外
        (1 .. 10).fold(1) { acc, i -> acc * i }.also { println(it) }
        // ..< 和 until都是exclusive
        (1 ..<10).filter { it % 2 == 0 }.forEach { println(it) }
        (1 until 10).filter { it % 2 == 0 }.forEach { println(it) }

        val also = User(1, "ze").also { println(it) }
        // deconstruction
        val (id, age) = also
        also.copy(name = "yan").also { println(it) }
        println("id is $id, name is $age")

        val list = mutableListOf(1, 2, 3)
        list[2] = 4
        list.set(2, 4)
        list.also { println(it) } // [1, 2, 4]

        val intSet = setOf(1, 2, 4)
        intSet.plus(2) // new set
        intSet.plus(3) // new set
        println(1 in intSet) // true

        val map = mapOf("zeyan" to 1)
        println("zeyan" in map)
        println(map.get("zeyan"))
        println(map["zeyan"])
    }
}