enum class ParserState {
    FoundStartOfKey,
    FoundEndOfKey,
    FoundStartOfValue,
    FoundEndOfValue,
    NewKeyValue
}