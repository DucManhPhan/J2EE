package com.manhpd.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KafkaConnection {

    private String connection;

    private String group;

    private List<String> topics;

    @Override
    public String toString() {
        return "connection: " + this.connection + ", group: " + this.group + ", topics: " + this.topics.toString();
    }

}