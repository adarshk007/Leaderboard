package com.example.demo.controller;

import com.example.demo.constants.urlConstants;
import com.example.demo.exchanges.GetLeaderBoardResponse;
import com.example.demo.service.LeaderboardService;
import com.example.demo.service.Leaderboard;
import com.example.demo.exchanges.GetLeaderBoardRequest;
import com.example.demo.dto.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
public class LeaderboardController {

    @Autowired
    private LeaderboardService leaderBoardService;

    @GetMapping(urlConstants.LEADERBOARD_API_ENDPOINT+urlConstants.GLOBAL_LEADERBOARD_API)
    public ResponseEntity<GetLeaderBoardResponse> getLeaderboard() {

        return ResponseEntity.ok().body(leaderBoardService.getTopScores(5));

    }

    @GetMapping(urlConstants.LEADERBOARD_API_ENDPOINT+urlConstants.ADD_SCORE_API)
    public ResponseEntity<Player> pushNewData(){
        Integer score = (int)(Math.random() * 1000);
        Integer playerId = (int)(Math.random() * 10);
        Player newPlayer = new Player();
        newPlayer.setPlayerID(Long.parseLong(playerId.toString()));
        newPlayer.setScore(score);
        newPlayer.setPlayerName("random".concat(playerId.toString()));
        leaderBoardService.addPlayer(newPlayer,5);
        return ResponseEntity.ok().body(newPlayer);
    }


}
