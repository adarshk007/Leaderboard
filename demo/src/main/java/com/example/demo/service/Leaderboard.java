package com.example.demo.service;

import  com.example.demo.dto.Player;
import java.util.List;

public interface Leaderboard {
    List<Player> getTopScore();
    Player maintainTopScore(Player player);
}
