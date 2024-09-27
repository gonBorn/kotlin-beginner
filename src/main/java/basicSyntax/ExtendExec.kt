package basicSyntax

// 默认final,需要继承需要open
open class Shape {
}

interface ShapeInterface {
    fun area(): Double
}

interface ShapeInterface2 {
    fun length(): Double
}

// Inheritance between classes is declared by a colon (:)
// val height 则height可以通过对象访问，不写val则访问不了height
class Rectangle(val height: Double, val width: Double): Shape(), ShapeInterface, ShapeInterface2 {
    override fun area(): Double {
        return height * width
    }

    override fun length(): Double {
        return 2 * (height + width)
    }

    companion object Rectangle {
        val name = "zeyan"
    }
}

// Extension functions
fun Rectangle.foo() {
    println("foo${height * width}")
}

object ExtendExec {
    @JvmStatic
    fun main(args: Array<String>) {
        println("Please enter the height: ")
        val height = readln()
        println("Please enter the width: ")
        val width = readln()
        val rectangle = Rectangle(height.toDouble(), width.toDouble())
        rectangle.foo()
        println("name" + Rectangle.name)
        println("The area of the rectangle is ${rectangle.height * rectangle.width}")
    }
}

fun sum(a: Int, b: Int) = a + b

// 无返回值Unit, unit也可以不写
fun printSum(a: Int, b: Int): Unit {
    println("sum of $a and $b is ${a + b}")
}

// A 'return' expression is required in a function with a block body ('{...}').
fun guessName(name: Any): String =
    when (name) {
        1 -> "one"
        "ze" -> "yan"
        is Long -> "Long"
        else -> "unknown"
    }