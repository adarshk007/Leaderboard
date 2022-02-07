package com.example.demo.service;

import com.example.demo.comparators.PlayerScoreComparator;
import com.example.demo.dto.NewPlayer;
import com.example.demo.dto.Player;
import com.example.demo.exchanges.GetLeaderBoardResponse;
import com.example.demo.model.LeaderBoardEntity;
import com.example.demo.repository.LeaderBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class LeaderboardService {

    @Autowired
    LeaderBoardRepository leaderBoardRepository;
    @Qualifier("topKScore")
    @Autowired
    Leaderboard leaderboard;


    /**
     * add player to Db & Cache
     * @param player Player
     * @returns void
     */
    //Atomic - @Transactional on function required, can have exception & error class separate
    public String[] addPlayer(NewPlayer player) {
        if(player != null) {
            try {
                savePlayerToDb(player);
                leaderboard.maintainTopScore(player);
                return addMsg(player,"player added to database");
            } catch (Exception ex) {
                return addMsg(null, ex.getMessage());
            }
        } else {
            return addMsg(null, "500 player is NULL");
        }
    }


    /**
     * fetch and return TopScores in response
     * @return GetLeaderBoardResponse
     */
    public GetLeaderBoardResponse getTopScores() {
        List<Player> allScores= leaderboard.getTopScore();
        Collections.sort(allScores, new PlayerScoreComparator());
        GetLeaderBoardResponse response = new GetLeaderBoardResponse();
        response.setPlayerList(allScores);
        return response;
    }


    /**
     * Logic of saving data to dataBase -DIP issue
     * @param player Player
     */
    void savePlayerToDb(NewPlayer player) {
        LeaderBoardEntity newUser = new LeaderBoardEntity();
        newUser.setScore(player.getScore());
        newUser.setName(player.getPlayerName());
        newUser.setPlayerId(player.getPlayerID());
        newUser.setDayCode(player.getDate());
        leaderBoardRepository.save(newUser);
    }



    String[] addMsg(NewPlayer player,String msg) {
        String playerStatus[] = new String[2];
        if(player != null) {
            playerStatus[0] = player.getPlayerName()+" "+player.getScore();
            playerStatus[1] = msg;
        } else {
            playerStatus[0] = msg;
            playerStatus[1] = null;
        }
        return playerStatus;
    }


}































/*  Threading  */

/*
ExecutorService v = Executors.newCachedThreadPool();
        v.execute(new Runnable() {
@Override
public void run() {
        values.maintainTopScore(player);
        }
        });
        v.execute(new Runnable() {
@Override
public void run() {
        savePlayerToDb(player);
        }
        });
//        v.awaitTermination(1000, TimeUnit.MILLISECONDS);
        v.shutdown();
*/


/*
@Transactional on function
 */