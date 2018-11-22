package keyValueParser

fun main(args: Array<String>){
    val path = args[0]
    val parser = KeyValueParser(path)
    val keyValueRoot = parser.parseFile()
    println(keyValueRoot.key)
}