package com.example.demo.exchanges;

import com.example.demo.dto.Player;
import  java.util.List;

public class GetLeaderBoardResponse {
    private List<Player> playerList;

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }


}
