package model

// data class 是一个用于存储数据的类，自动提供了一些常用的方法和功能，如 equals()、hashCode()、toString() 和 copy()
data class Job(val id: JobId, val company: Company, val role: Role, val salary: Salary)

// @JvmInline（或 value class）用于创建内联类，它允许你封装一个单一的值，同时在编译时优化性能，减少对象创建的开销。
// 在这个例子中，JobId 是一个内联类，封装了一个 Long 类型的值。
// JobId 不会在运行时创建一个新的对象实例，而是将 value 直接嵌入到使用 JobId 的地方。
@JvmInline
value class JobId(val value: Long)

@JvmInline
value class Company(val name: String)

@JvmInline
value class Role(val name: String)

@JvmInline
value class Salary(val amount: Double)

// we don’t want to pollute our domain model
fun Job.toJson(): String = """
    {
        "id": ${id.value},
        "company": "${company.name}",
        "role": "${role.name}",
        "salary": ${salary.amount}
    }
""".trimIndent()