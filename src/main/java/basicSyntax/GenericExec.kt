package basicSyntax

object GenericExec {
    interface MyList<T> {
        fun get(index: Int): T
    }
}