package com.challenge.careerswtich.DTOs;

public class ResponseCheckDTO {

    private Boolean message;

    public Boolean getMessage() {
        return message;
    }

    public void setMessage(Boolean message) {
        this.message = message;
    }

    public ResponseCheckDTO() {
    }

    public ResponseCheckDTO(Boolean message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseCheckDTO [message=" + message + "]";
    }

    
    
}