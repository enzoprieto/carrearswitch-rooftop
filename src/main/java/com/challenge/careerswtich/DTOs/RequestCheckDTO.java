package com.challenge.careerswtich.DTOs;

import java.util.List;

public class RequestCheckDTO {

    private List<String>blocks;
    private String encoded;

    public List<String> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<String> blocks) {
        this.blocks = blocks;
    }

    public String getEncoded() {
        return encoded;
    }

    public void setEncoded(String encoded) {
        this.encoded = encoded;
    }

    public RequestCheckDTO() {
    }

    public RequestCheckDTO(List<String> blocks, String encoded) {
        this.blocks = blocks;
        this.encoded = encoded;
    }
    
}