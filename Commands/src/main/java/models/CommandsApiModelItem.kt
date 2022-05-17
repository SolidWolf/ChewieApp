package models

data class CommandsApiModelItem(
    val commandName: String,
    val content: String,
    val id: Int,
    val minUserLevel: Int,
    val type: Int,
    val useCooldown: Any,
    val useCount: Int
)