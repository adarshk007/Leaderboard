package com.example.demo.comparators;

import com.example.demo.dto.Player;

import java.util.Comparator;


public class PlayerHeapComparator implements Comparator<Player>{

        @Override
        public int compare(Player p, Player q) {
            if(p.getScore() < q.getScore()) {
                return -1;
            } else if(p.getScore() > q.getScore()) {
                return 1;
            }
            return 0;
        }
}

