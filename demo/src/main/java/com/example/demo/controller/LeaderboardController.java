package com.example.demo.controller;

import com.example.demo.constants.urlConstants;
import com.example.demo.dto.NewPlayer;
import com.example.demo.exchanges.GetLeaderBoardResponse;
import com.example.demo.service.LeaderboardService;
import com.example.demo.dto.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;


@RequestMapping(urlConstants.LEADERBOARD_API_ENDPOINT)
@RestController
public class LeaderboardController {

    @Autowired
    private LeaderboardService leaderBoardService;


    @GetMapping(urlConstants.GLOBAL_LEADERBOARD_API)
    public ResponseEntity<GetLeaderBoardResponse> getLeaderboard() {
        try {
            return ResponseEntity.ok().body(leaderBoardService.getTopScores());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(null);
        }
    }





    // ---------------------------------------
    //        DATA PUSH
    // ----------------------------------------

    @GetMapping(urlConstants.ADD_SCORE_API)
    public ResponseEntity<Player> pushNewData() {
        Integer score = (int)(Math.random() * 1000);
        Integer playerId = (int)(Math.random() * 10);
        NewPlayer newPlayer = new NewPlayer();
        newPlayer.setPlayerID(Long.parseLong(playerId.toString()));
        newPlayer.setScore(score);
        newPlayer.setPlayerName("random".concat(playerId.toString()));
        java.util.Date d =new java.util.Date();
        newPlayer.setDate(new Date(d.getTime()));
        try {
            String playerStatus[] = leaderBoardService.addPlayer(newPlayer);
            if(playerStatus[1] != null) {
                return ResponseEntity.ok().body(newPlayer);
            }
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(null);
        }
        return ResponseEntity.ok().body(null);
    }



}
