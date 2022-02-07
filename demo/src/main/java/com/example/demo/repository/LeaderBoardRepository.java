package com.example.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.demo.model.LeaderBoardEntity;

import java.util.List;


public interface LeaderBoardRepository extends CrudRepository<LeaderBoardEntity, Long>{

    @Query(value = "select * from leader_board_entity order by score desc limit ?1 ;" , nativeQuery = true)
    public List<LeaderBoardEntity>  getTopKScores(Integer limit);

    public List<LeaderBoardEntity> findByName(String name);
}
