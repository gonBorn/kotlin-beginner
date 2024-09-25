package receiver

import model.Constant
import receiver.GenericDemo.printAsJson

object ContextDemo {
    context(JsonScope<T>)
    fun <T> printAsJson(objs: List<T>) =
        objs.joinToString(",", "[", "]") { it.toJson() }

//    原来是这样写的：fun <T> JsonScope<T>.printAsJson(objs: List<T>) =

    @JvmStatic
    fun main(args: Array<String>) {
        with(jobJsonScope) {
            println(printAsJson(Constant.JOBS_DATABASE.values.toList()))
        }
    }
}