package com.example.demo.model;


import javax.persistence.*;
import java.sql.Date;


@Entity(name="leader_board_entity")
@Table(name="leader_board_entity",indexes = @Index(columnList = "score,dayCode"))
public class LeaderBoardEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Long playerId;
    private String name;
    private Integer score;
    private Date dayCode;

    public Date getDayCode() {
        return dayCode;
    }

    public void setDayCode(Date dayCode) {
        this.dayCode = dayCode;
    }



    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}