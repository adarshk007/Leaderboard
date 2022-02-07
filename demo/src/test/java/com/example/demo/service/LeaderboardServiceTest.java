package com.example.demo.service;

import com.example.demo.dto.NewPlayer;
import com.example.demo.dto.Player;
import com.example.demo.repository.LeaderBoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LeaderboardServiceTest {

    @Autowired
    LeaderboardService leaderboardService;
    @Autowired
    LeaderBoardRepository leaderBoardRepository;

    @Test
    void addPlayer() {
        Integer score = (int)(Math.random() * 10);
        Integer playerId = (int)(Math.random() * 10);
        NewPlayer newPlayer = new NewPlayer();
        newPlayer.setPlayerID(Long.parseLong(playerId.toString()));
        newPlayer.setScore(score);
        newPlayer.setPlayerName("randomtest".concat(playerId.toString()));
        leaderboardService.addPlayer(newPlayer);

        assertTrue(leaderBoardRepository.findByName("randomtest".concat(playerId.toString())).size() > 0);

    }

    @Test
    void addPlayerNull() {
        NewPlayer player = new NewPlayer();
        leaderboardService.addPlayer(player);
    }

    @Test
    void savePlayerToDb() {

    }
}
