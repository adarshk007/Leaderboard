package com.example.demo.service;

import com.example.demo.comparators.PlayerHeapComparator;
import com.example.demo.dto.Player;
import com.example.demo.model.LeaderBoardEntity;
import com.example.demo.repository.LeaderBoardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


@Service
public class TopKScore implements Leaderboard{

    @Autowired
    private CacheSize cacheSize;
    @Autowired
    private LeaderBoardRepository leaderBoardRepository;
    private PriorityQueue<Player> players;


    @PostConstruct
    public void createCache() {
        this.players = new PriorityQueue<>(cacheSize.getCacheSize(), new PlayerHeapComparator());
        this.constructCache();

    }

    public void constructCache() {
        List<LeaderBoardEntity> topKData = leaderBoardRepository.getTopKScores(cacheSize.getCacheSize());
        for (LeaderBoardEntity i: topKData) {
            this.players.add(constructData(i));
        }
    }


    @Override
    public List<Player> getTopScore() {
        List playerList = new ArrayList(this.players);
        return playerList;
    }

    @Override
    public Player maintainTopScore(Player player) {
        if(player != null) {
            Player currentMin = this.players.peek();
            if(this.players.size() == cacheSize.getCacheSize()) {
                if (currentMin.getScore() < player.getScore()) {
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


    public Player constructData(LeaderBoardEntity leaderBoardEntity) {
        Player player = new Player();
        player.setId(leaderBoardEntity.getId());
        player.setPlayerID(leaderBoardEntity.getPlayerId());
        player.setPlayerName(leaderBoardEntity.getName());
        player.setScore(leaderBoardEntity.getScore());
        return player;
    }

}
