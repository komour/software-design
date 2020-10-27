class Post(val id: Int, val date: Long, val text: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (this.javaClass != other?.javaClass) return false

        other as Post

        if (id != other.id || date != other.date || text != other.text) {
            return false
        }

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + date.hashCode()
        result = 31 * result + text.hashCode()
        return result
    }
}