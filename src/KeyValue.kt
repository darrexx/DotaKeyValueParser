data class KeyValue(
        var key: String?,
        var value: String?,
        var parent: KeyValue?,
        var children: MutableList<KeyValue>
) {
    constructor(key: String) : this(key, null, null, mutableListOf())
}