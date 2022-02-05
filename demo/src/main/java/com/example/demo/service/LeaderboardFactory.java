package com.example.demo.service;

public class LeaderboardFactory {

    public Leaderboard getTopKValues(Integer topk){
        if(topk == null){
            return null;
        }

        if(topk == 5) {
            return TopFiveScore.getInstance();
        }

        return null;
    }
}
