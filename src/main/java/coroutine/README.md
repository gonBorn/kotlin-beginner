## Coroutine

A coroutine is an instance of a suspendable computation.
A coroutine is not bound to any particular thread.
It may suspend its execution in one thread and resume in another one.

## runBlocking

runBlocking 是一个用于启动协程的函数，它会**阻塞**当前线程，直到其内部的协程执行完毕。
它会返回内部代码块的结果

```kotlin
fun main() {
    val result = runBlocking {
        delay(1000L)
        "Result from runBlocking"
    }
    println(result)
}
```

通常在主线程或单元测试中使用，用于桥接阻塞代码和协程代码。

## launch

launch在协程中启动一个新的协程，非阻塞的，返回一个Job对象，可以用来取消协程。

## coroutineScope

在协程中管理并发时，优先考虑使用 coroutineScope，而 runBlocking 通常只在非协程环境中使用。


