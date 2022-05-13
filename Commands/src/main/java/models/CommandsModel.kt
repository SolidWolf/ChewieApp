package models

class CommandsModel(
    val commandName: String,
    val content: String,
    val useCount: Int?,
    val hasCooldown: Boolean,
    val commandType: String,
    val permissionType: String
)