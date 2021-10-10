package com.manhpd.webservice_webflux.controller;

import javax.validation.Valid;

import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.manhpd.webservice_webflux.model.Tweet;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITweetController {

	@GetMapping(value = "${tweets.all-tweets}")
	default Flux<Tweet> getAllTweetsAPI(ServerHttpRequest request) {
		return this.getAllTweets(request);
	}

	Flux<Tweet> getAllTweets(ServerHttpRequest request);

	@PostMapping(value = { "${tweets.all-tweets}" })
	default Mono<Tweet> createTweetsAPI(ServerHttpRequest request, @Valid @RequestBody Tweet tweet) {
		return this.createTweets(request, tweet);
	}

	Mono<Tweet> createTweets(ServerHttpRequest request, Tweet tweet);

	@GetMapping(value = { "${tweets.detail}" })
	default Mono<Tweet> getTweetByIdAPI(ServerHttpRequest request, @PathVariable String id) {
		return this.getTweetById(request, id);
	}

	Mono<Tweet> getTweetById(ServerHttpRequest request, String id);

	@PutMapping(value = { "${tweets.detail}" })
	default Mono<ResponseEntity<Tweet>> updateTweetAPI(ServerHttpRequest request, @PathVariable(value = "id") String tweetId, @Valid @RequestBody Tweet tweet) {
		return this.updateTweet(request, tweetId, tweet);
	}

	Mono<ResponseEntity<Tweet>> updateTweet(ServerHttpRequest request, String tweetId, Tweet tweet);

	@DeleteMapping(value = { "${tweets.detail}" })
	default Mono<ResponseEntity<Void>> deleteTweetAPI(ServerHttpRequest request, @PathVariable(value = "id") String tweetId) {
		return this.deleteTweet(request, tweetId);
	}

	Mono<ResponseEntity<Void>> deleteTweet(ServerHttpRequest request, String tweetId);

	@GetMapping(value = "${tweets.sse}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	default Flux<Tweet> streamAllTweetsAPI(ServerHttpRequest request) {
		return this.streamAllTweets(request);
	}

	Flux<Tweet> streamAllTweets(ServerHttpRequest request);
}
