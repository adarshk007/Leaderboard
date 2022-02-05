package com.example.demo.service;

import com.example.demo.dto.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

//singleton should be required
public class TopFiveScore implements Leaderboard{

    private PriorityQueue<Player> players;
    private static TopFiveScore object;

    private TopFiveScore(){
        this.players = new PriorityQueue<Player>(5, (p,q) -> {
            if(p.getScore() < q.getScore()) {
                return -1;
            } else if(p.getScore() > q.getScore()) {
                return 1;
            }
            return 0;
        });
    }

    public static TopFiveScore getInstance()
    {
        if(object == null) {
            object = new TopFiveScore();
        }
        return object;
    }

    //DIP ISSUE
    @Override
    public List<Player> getTopScore() {
        List playerList = new ArrayList(this.players);
        return playerList;
    }

    //DIP ISSUE
    @Override
    public Player maintainTopScore(Player player) {
        if(player != null) {
            Player currentMin = this.players.peek();
            if(this.players.size() == 5) {
                if(currentMin.getScore() < player.getScore()) {
                    this.players.poll();
                    this.players.add(player);
                    player = currentMin;
                }
            } else {
                this.players.add(player);
            }
        }
        return player;
    }
}
