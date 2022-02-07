package com.example.demo.service;

import  com.example.demo.dto.Player;
import java.util.List;

public interface Leaderboard {

    /**
     *  Function will return all the top scores
     *  @return List
     */
    List<Player> getTopScore();

    /**
     * Function will take the player as parameter
     * Logic of maintaining the Top Score Player in the leaderboard
     * @param player
     * @return Player
     */
    Player maintainTopScore(Player player);

}
