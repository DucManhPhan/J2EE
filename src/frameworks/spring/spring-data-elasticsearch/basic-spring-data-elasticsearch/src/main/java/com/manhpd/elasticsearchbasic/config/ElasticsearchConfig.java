package com.manhpd.elasticsearchbasic.config;

import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * Configure the REST client to access Elasticsearch.
 *
 * @EnableElasticsearchRepositories activates the Respository support for all repositories interfaces defined in the repository package.
 *
 * The Java High Level REST Client is the default client of Elasticsearch,
 * it provides a straight forward replacement for the TransportClient as it accepts
 * and returns the very same request/response objects and therefore depends on the Elasticsearch core project.
 * Asynchronous calls are operated upon a client managed thread pool and require a callback to be notified when the request is done.
 * */
@Configuration
@EnableElasticsearchRepositories(
        basePackages = "com.manhpd.elasticsearchbasic.repository"
)
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {

    @Override
    public RestHighLevelClient elasticsearchClient() {
        // Connect to elastic search locally
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
