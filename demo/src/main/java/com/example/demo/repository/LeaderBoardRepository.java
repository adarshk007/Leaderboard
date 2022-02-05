package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.model.LeaderBoardEntity;


public interface LeaderBoardRepository extends CrudRepository<LeaderBoardEntity, Long>{
}
