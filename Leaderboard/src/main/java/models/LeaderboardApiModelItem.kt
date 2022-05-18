package models

data class LeaderboardApiModelItem(
    val points: Int,
    val rank: Int,
    val username: String
)