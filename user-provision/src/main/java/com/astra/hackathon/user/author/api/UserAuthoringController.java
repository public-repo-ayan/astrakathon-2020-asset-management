package com.astra.hackathon.user.author.api;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.astra.hackathon.user.author.handler.UserAuthoringHandler;

@RestController
public class UserAuthoringController {

	private static final Logger logger = LoggerFactory.getLogger(UserAuthoringController.class);

	@Autowired
	private UserAuthoringHandler userAuthoringHandler;

	@RequestMapping(path = "/user", method = RequestMethod.GET)
	public ResponseEntity<String> save(@RequestParam(required = true) final String userId,
			@RequestParam(required = true) final String bucket,
			@RequestParam(required = false) final String description) {
		ResponseEntity<String> response = null;
		logger.info("inside UserAuthoringController#save, received request:{},{},{}", userId, bucket, description);
		String savedUserId = userAuthoringHandler.saveUserBucket(userId, bucket, description);
		if (Objects.nonNull(savedUserId)) {
			response = new ResponseEntity<String>("Successfully saved the user with id:" + savedUserId, HttpStatus.OK);
		} else {
			response = new ResponseEntity<String>("Failed to save the user.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@RequestMapping(path = "/inactive", method = RequestMethod.GET)
	public ResponseEntity<String> inactive(@RequestParam(required = true) final String userId,
			@RequestParam(required = true) final String bucket) {
		ResponseEntity<String> response = null;
		logger.info("inside UserAuthoringController#inactive, received request:{},{}", userId, bucket);
		String savedUserId = userAuthoringHandler.inactivateUserBucket(userId, bucket);
		if (Objects.nonNull(savedUserId)) {
			response = new ResponseEntity<String>("Successfully inactivated the bucket with id:" + savedUserId,
					HttpStatus.OK);
		} else {
			response = new ResponseEntity<String>("Failed to inactivate the user.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

}
