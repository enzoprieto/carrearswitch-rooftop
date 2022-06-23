package com.challenge.careerswtich.DTOs;

import java.util.Date;

public class ResponseStatsDTO {
    
    private String result;
    private Integer requests;
    private String start;
    private String end;
    private String error;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getRequests() {
        return requests;
    }

    public void setRequests(Integer requests) {
        this.requests = requests;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String  start) {
        this.start = start;
    }

    public String  getEnd() {
        return end;
    }

    public void setEnd(String  end) {
        this.end = end ;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ResponseStatsDTO() {
         this.result = "";
         this.requests = 0;
         this.start = null;
         this.end = null;
         this.error = "";
    }

    public ResponseStatsDTO(String result, Integer requests, String  start, String  end, String error) {
        this.result = result;
        this.requests = requests;
        this.start = start;
        this.end = end;
        this.error = error;
    }

    

}