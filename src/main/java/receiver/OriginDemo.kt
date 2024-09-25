package receiver

import model.Constant
import model.Job
import model.toJson

object OriginDemo {
    private fun printAsJson(objs: List<Job>) =
        objs.joinToString(",", "[", "]") { it.toJson() }

    @JvmStatic
    fun main(args: Array<String>) {
        Constant.JOBS_DATABASE.values.toList().let(::printAsJson)
    }
}