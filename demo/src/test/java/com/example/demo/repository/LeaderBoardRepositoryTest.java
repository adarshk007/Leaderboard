package com.example.demo.repository;

import com.example.demo.model.LeaderBoardEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LeaderBoardRepositoryTest {

    @Autowired
    LeaderBoardRepository leaderBoardRepository;

    @Test
    void getTop0Scores() {
        List<LeaderBoardEntity> val= leaderBoardRepository.getTopKScores(0);
        assertEquals(0, val.size());
    }

    @Test
    void getTop5Scores() {
        List<LeaderBoardEntity> val= leaderBoardRepository.getTopKScores(5);
        assertEquals(5, val.size());
    }

    @Test
    void getTop100Scores() {
        List<LeaderBoardEntity> val= leaderBoardRepository.getTopKScores(100);
        assertEquals(8, val.size());
    }

    @Test
    void getTopScoreValue() {
        List<LeaderBoardEntity> val= leaderBoardRepository.getTopKScores(5);
        Integer[] arr = new Integer[5];
        Integer j = 0;
        for(LeaderBoardEntity i: val) {
            arr[j] = i.getScore();
            j += 1;
        }
        Integer expected[] = {873,862,751,662,556};

        assertThat(arr).isEqualTo(expected);
    }

}