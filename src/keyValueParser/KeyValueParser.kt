package keyValueParser

import java.io.File

class KeyValueParser(path: String) {

    private val file: File = File(path)


    fun parseFile() : KeyValue{
        val lines = file.readLines()
        var parserState  = ParserState.NewKeyValue
        var kv = KeyValue(key = "")
        for(line in lines){
            val line = line.trim()
            if(line.startsWith("{") || line.startsWith("//")) continue
            if(line.startsWith("}")) {
                kv = kv.parent ?: kv
                continue
            }
            var key = StringBuilder()
            var value = StringBuilder()
            loop@ for (i in 0 until line.length){
                if(line[i] == '/' && line[i+1] == '/') break

                when(parserState){
                    ParserState.NewKeyValue -> if(line[i] == '"'){
                            parserState = ParserState.FoundStartOfKey
                        }
                    ParserState.FoundStartOfKey -> if(line[i] != '"'){
                            key.append(line[i])
                        }
                        else{
                            parserState = ParserState.FoundEndOfKey
                            continue@loop
                        }
                    ParserState.FoundEndOfKey -> if(line[i] == '"'){
                            parserState = ParserState.FoundStartOfValue
                        }
                    ParserState.FoundStartOfValue -> if(line[i] != '"'){
                            value.append(line[i])
                        }
                        else{
                            parserState = ParserState.FoundEndOfValue
                            continue@loop
                        }
                }
            }
            parserState = ParserState.NewKeyValue
            if(key.isEmpty() && value.isEmpty()) continue
            if(value.isEmpty()){
                if(kv?.key.isNullOrEmpty()){
                    kv = KeyValue(key.toString())
                }
                else{
                    var tmpKV = KeyValue(key.toString())
                    tmpKV.parent = kv
                    kv.children.add(tmpKV)
                    kv = tmpKV
                }
            }
            else{
                var tmpKv = KeyValue(key.toString())
                tmpKv.parent = kv
                kv.children.add(tmpKv)
            }
        }
        return kv
    }
}