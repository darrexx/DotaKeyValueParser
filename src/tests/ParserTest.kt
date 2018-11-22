package tests

import keyValueParser.KeyValueParser
import org.junit.jupiter.api.Test
import java.nio.file.Paths
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ParserTest {

    @Test
    fun test(){
        val path = Paths.get("").toAbsolutePath().toString() + "\\npc_abilities.txt"
        val parser = KeyValueParser(path)
        val keyValue = parser.parseFile()
        val kv = keyValue.findChildByKey("AbilitySound")
        assertNotNull(kv)
        assertEquals("Hero_Antimage.ManaBreak", kv.value)
    }
}