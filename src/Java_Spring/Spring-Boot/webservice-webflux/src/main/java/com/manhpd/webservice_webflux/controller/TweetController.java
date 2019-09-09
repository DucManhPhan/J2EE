package com.manhpd.webservice_webflux.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.manhpd.webservice_webflux.exception.TweetNotFoundException;
import com.manhpd.webservice_webflux.model.Tweet;
import com.manhpd.webservice_webflux.payload.ErrorResponse;
import com.manhpd.webservice_webflux.repository.TweetRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@PropertySource(value = "classpath:/application-webservice.properties", ignoreResourceNotFound = true)
public class TweetController implements ITweetController{

	@Autowired
	private TweetRepository tweetRepository;

	public Flux<Tweet> getAllTweets(ServerHttpRequest request) {
		return tweetRepository.findAll();
	}

	public Mono<Tweet> createTweets(ServerHttpRequest request, @Valid @RequestBody Tweet tweet) {
		return tweetRepository.save(tweet);
	}

	public Mono<Tweet> getTweetById(ServerHttpRequest request, @PathVariable String id) {
		return tweetRepository.findById(id);
	}

	public Mono<ResponseEntity<Tweet>> updateTweet(ServerHttpRequest request, @PathVariable(value = "id") String tweetId,
			@Valid @RequestBody Tweet tweet) {
		return tweetRepository.findById(tweetId)
							  .flatMap(existingTweet -> {
										existingTweet.setText(tweet.getText());
										return tweetRepository.save(existingTweet);
							  })
							  .map(updateTweet -> new ResponseEntity<>(updateTweet, HttpStatus.OK))
							  .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	public Mono<ResponseEntity<Void>> deleteTweet(ServerHttpRequest request, @PathVariable(value = "id") String tweetId) {
		return tweetRepository.findById(tweetId)
							  .flatMap(existingTweet -> tweetRepository.delete(existingTweet)
							  .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
							  .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	// Tweets are Sent to the client as Server Sent Events
	public Flux<Tweet> streamAllTweets(ServerHttpRequest request) {
		return tweetRepository.findAll();
	}

	/*
	 * Exception Handling Examples (These can be put into a @ControllerAdvice to
	 * handle exceptions globally)
	 */
	@ExceptionHandler(DuplicateKeyException.class)
	public ResponseEntity handleDuplicateKeyException(DuplicateKeyException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ErrorResponse("A Tweet with the same text already exists"));
	}

	@ExceptionHandler(TweetNotFoundException.class)
	public ResponseEntity handleTweetNotFoundException(TweetNotFoundException ex) {
		return ResponseEntity.notFound().build();
	}
}
