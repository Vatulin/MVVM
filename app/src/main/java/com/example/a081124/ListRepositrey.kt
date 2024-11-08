package com.example.a081124

class ListRepositrey {
    val listGames = arrayListOf<Game>(Game("Tetris", 100, "Nintendo"),
        Game("Clash Royale", 600, "SuperCell"),
        Game("PUBG", 1000, "Tencent"),
        Game("CS2", 1500, "Valve"),
        Game("Mario", 200, "Nintendo"),
        Game("Atomic Heart", 2000, "Mud fish"),
        Game("Plants vs Zombie", 400, "EA"),
        Game("Batmen", 300, "Rocksteady"))

    fun getList(): ArrayList<Game> {
        return listGames
    }
}