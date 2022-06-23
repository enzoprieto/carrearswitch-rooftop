package com.challenge.careerswtich.DTOs;

import java.util.Arrays;
import java.util.List;

public class ResponseBloksDTO {

    private List<String> data;
    private Integer chunkSize;
    private Integer length;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public Integer getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(Integer chunkSize) {
        this.chunkSize = chunkSize;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public ResponseBloksDTO() {
    }

    public ResponseBloksDTO(List<String> data, Integer chunkSize, Integer length) {
        this.data = data;
        this.chunkSize = chunkSize;
        this.length = length;
    }

}