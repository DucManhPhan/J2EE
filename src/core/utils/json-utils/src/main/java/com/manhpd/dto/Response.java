package com.manhpd.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private String type;

    private Content content;

    private Object charts;

    public Response(String type, Content content) {
        this.type = type;
        this.content = content;
    }

    public Response(String type, Object charts) {
        this.type = type;
        this.charts = charts;
    }

}
