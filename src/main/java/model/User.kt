package model

data class User(val id: Int, val name: String, val gender: String = "female") {
    override fun toString(): String {
        return "User(id=$id, name='$name', gender='$gender')"
    }
}
