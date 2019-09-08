package com.manhpd.webservice_webflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.manhpd.webservice_webflux.model.Tweet;

@Repository
public interface TweetRepository extends ReactiveMongoRepository<Tweet, String> {
	// nothing to do
}
