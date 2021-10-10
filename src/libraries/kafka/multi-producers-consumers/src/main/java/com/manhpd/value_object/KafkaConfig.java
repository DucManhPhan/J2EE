package com.manhpd.value_object;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class KafkaConfig {

    // Producer config
    /**
     * Kafka server
     */
    private String bootstrapServers;

    private String acks;

    private String retries;

    private String batchSize;

    private String lingerMs;

    /**
     * How many memory did it use to provide for Kafka?
     */
    private String bufferMemory;

    // Consumer config
    private String consumerGroupId;

    private String maxPollRecords;

    private String autoCommitIntervalMs;

    private String sessionTimeoutMs;

    private String hearbeatInternalMs;

    private String maxPollInternalMs;

    private String autoOffsetReset;

    private String consumerMaxTopic;

    private String consumerMediumTopic;

    private String consumerMinTopic;

//    @Override
//    public String toString() {
//        return "Bootstrap server: " + this.bootstrapServers + ", batch size: " + this.batchSize + ", consumer group id: " + this.consumerGroupId
//                + ", max topic: " +this.consumerMaxTopic + ", medidum topic: " + this.consumerMediumTopic + ", minTopic: " + this.consumerMinTopic;
//    }

}
