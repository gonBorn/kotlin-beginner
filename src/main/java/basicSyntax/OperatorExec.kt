package basicSyntax

interface OperatorExec {
    fun getRealName(): String
    operator fun plus(other: OperatorExec): OperatorExec
}

class OperatorExecImpl(val name: String) : OperatorExec {
    override fun getRealName(): String {
        return name + "Impl"
    }

    override fun plus(other: OperatorExec): OperatorExec {
        return OperatorExecImpl( getRealName() + " " + other.getRealName())
    }
}

object OperatorDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        val operatorExec1 = OperatorExecImpl("ze")
        val operatorExec2 = OperatorExecImpl("yan")
        val operatorExec3 = operatorExec1 + operatorExec2
        println(operatorExec3.getRealName())
    }
}
