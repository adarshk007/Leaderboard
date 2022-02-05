package com.example.demo.service;

import com.example.demo.dto.Player;
import com.example.demo.exchanges.GetLeaderBoardResponse;
import com.example.demo.model.LeaderBoardEntity;
import com.example.demo.model.UserEntity;
import com.example.demo.repository.LeaderBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LeaderboardService {
    // wrong here
    @Autowired
    LeaderBoardRepository leaderBoardRepository;
    public GetLeaderBoardResponse getTopScores(Integer param) {

        //wrong factory
        LeaderboardFactory factory = new LeaderboardFactory();
        Leaderboard values= factory.getTopKValues(param);
        List<Player> allScores= values.getTopScore();
        //improvement
        Collections.sort(allScores,(p,q) -> {
            if(p.getScore() < q.getScore()) {
                return 1;
            } else if(p.getScore() > q.getScore()) {
                return -1;
            }
            return 0;
        });
        GetLeaderBoardResponse response = new GetLeaderBoardResponse();
        response.setPlayerList(allScores);
        return response;
    }

    public void addPlayer(Player player,Integer param) {
        LeaderboardFactory factory = new LeaderboardFactory();
        Leaderboard values= factory.getTopKValues(param);
        values.maintainTopScore(player);
        savePlayerToDb(player);

    }

    //sequential which is wrong
    void savePlayerToDb(Player player) {
        LeaderBoardEntity newUser = new LeaderBoardEntity();
        newUser.setScore(player.getScore());
        newUser.setName(player.getPlayerName());
        newUser.setPlayerId(player.getPlayerID());
        System.out.println(newUser);
        System.out.println(leaderBoardRepository);
        leaderBoardRepository.save(newUser);
    }
}
