package com.example.demo.exchanges;

public class GetLeaderBoardRequest {
    private String region;
    private Integer topK = 5;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getTopK() {
        return topK;
    }

    public void setTopK(Integer topK) {
        this.topK = topK;
    }


}
