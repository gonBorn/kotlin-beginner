package receiver

import model.Constant
import model.Job

interface JsonScope<T> {
    fun T.toJson(): String
}

// implement JsonScope interface as an anonymous object

val jobJsonScope = object : JsonScope<Job> {
    override fun Job.toJson(): String = """
            {
                "id": ${id.value},
                "company": "${company.name}",
                "role": "${role.name}",
                "salary": $salary.value}
            }
        """.trimIndent()
}

object GenericDemo {
    // we define the printAsJson function as an extension function on the JsonScope interface
    fun <T> JsonScope<T>.printAsJson(objs: List<T>) =
        objs.joinToString(",", "[", "]") { it.toJson() }

    @JvmStatic
    fun main(args: Array<String>) {
        with(jobJsonScope) {
            println(printAsJson(Constant.JOBS_DATABASE.values.toList()))
        }
    }
}

