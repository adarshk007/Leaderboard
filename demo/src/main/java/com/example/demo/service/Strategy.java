package com.example.demo.service;

import com.example.demo.dto.Player;

import java.util.List;

public class Strategy {
    private Leaderboard strategy;

    public Strategy(Leaderboard strategy){
        this.strategy = strategy;
    }

    public Player executeStrategy(Player player){
        return strategy.maintainTopScore(player);
    }
    public List<Player> excuteStrategy(){
        return strategy.getTopScore();
    }
}
