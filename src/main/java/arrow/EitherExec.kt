package arrow

import arrow.core.*
import arrow.core.raise.either

fun main() {
    val maybeTwo: Either<Exception, Int> = either { 2 }
    val maybeThree: Either<Exception, Int> = either { raise(Exception("three")) }

    val maybeFive: Either<Exception, Int> = either {
        maybeTwo.bind() + maybeThree.bind()
    }

    when (maybeFive) {
        is Either.Left -> println("Error: ${maybeFive.value}")
        is Either.Right -> println("Success: ${maybeFive.value}")
    }

    //OPTION -> EITHER
//    input Option<A>
//    traverse A->Either<Exception, B>
//    return Either<Exception, Option<B>>
    val either: Either<Exception, Option<Int>> =
        2.some().traverse { if (it > 2) Exception("too big").left() else it.right() }
}