package com.manhpd.dto;

import lombok.Data;

@Data
public class DataResponse {

    private Response[] responses;

    public DataResponse() {
        this.responses = new Response[2];
    }

    public void addResponse(Response res, int index) {
        this.responses[index] = res;
    }

}
