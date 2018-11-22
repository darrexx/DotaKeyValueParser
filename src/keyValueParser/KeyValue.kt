package keyValueParser

data class KeyValue(
        var key: String?,
        var value: String?,
        var parent: KeyValue?,
        var children: MutableList<KeyValue>
) {
    constructor(key: String) : this(key, null, null, mutableListOf())

    fun findChildByKey(key: String) : KeyValue?{
        for (child in children){
            if(child.key == key){
                return child
            }
            val foundChild = child.findChildByKey(key)
            if(foundChild != null){
                return foundChild
            }
        }
        return null
    }
}