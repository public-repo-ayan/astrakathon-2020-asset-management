package com.astra.hackathon.user.author.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.astra.hackathon.user.author.handler.UserSearchHandler;
import com.astra.hackathon.user.client.User;

@RestController
public class UserSearchController {

	private static final Logger logger = LoggerFactory.getLogger(UserSearchController.class);

	@Autowired
	private UserSearchHandler userSearchHandler;

	@RequestMapping(path = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAll() {
		logger.info("inside UserSearchController#getAll");
		return new ResponseEntity<List<User>>(userSearchHandler.fetchAllUsers(), HttpStatus.OK);
	}

	@RequestMapping(path = "/users/{userId}", method = RequestMethod.GET)
	public ResponseEntity<User> get(@PathVariable final String userId) {
		logger.info("inside UserSearchController#get, received userId:{},{},{}", userId);
		return new ResponseEntity<User>(userSearchHandler.fetchUser(userId), HttpStatus.OK);
	}

}
