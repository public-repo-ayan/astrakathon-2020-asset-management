package com.astra.hackathon.user.author.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astra.hackathon.asset.dao.UserAssetCountsDao;
import com.astra.hackathon.asset.dao.UserBucketsDao;
import com.astra.hackathon.asset.function.EntityToJsonMapper;
import com.astra.hackathon.asset.model.UserAssetCounts;
import com.astra.hackathon.asset.model.UserBuckets;
import com.astra.hackathon.user.client.User;
import com.datastax.oss.driver.api.core.PagingIterable;

@Service
public class UserSearchHandler {
	private static final Logger logger = LoggerFactory.getLogger(UserSearchHandler.class);

	@Autowired
	private UserBucketsDao userBucketsDao;

	@Autowired
	private UserAssetCountsDao userAssetCountsDao;

	public User fetchUser(final String userId) {
		logger.info("inside UserSearchHandler#fetchUser");
		PagingIterable<UserBuckets> dbResults = userBucketsDao.findByUserId(userId);
		List<UserBuckets> userBucektsDbResult = dbResults.all();
		logger.info("userBucektsDbResult:{}", userBucektsDbResult);
		User user = new User();
		user.setUserId(userId);
		userBucektsDbResult.forEach(b -> {
			UserAssetCounts bucketAssetCount = userAssetCountsDao.findByUserIdForBucket(userId, b.getBucket());
			user.getBuckets().add(EntityToJsonMapper.clientUserBucket(b, bucketAssetCount));
		});

		return user;
	}

	public List<User> fetchAllUsers() {
		logger.info("inside UserSearchHandler#fetchAllUsers");
		PagingIterable<UserBuckets> dbResults = userBucketsDao.findAll();
		List<UserBuckets> userBucektsDbResult = dbResults.all();
		logger.info("userBucektsDbResult:{}", userBucektsDbResult);
		Map<String, User> userMap = new HashMap<String, User>();
		userBucektsDbResult.forEach(b -> {
			UserAssetCounts bucketAssetCount = userAssetCountsDao.findByUserIdForBucket(b.getUserId(), b.getBucket());
			if (userMap.containsKey(b.getUserId())) {
				userMap.get(b.getUserId()).getBuckets().add(EntityToJsonMapper.clientUserBucket(b, bucketAssetCount));
			} else {
				User user = new User(b.getUserId());
				user.getBuckets().add(EntityToJsonMapper.clientUserBucket(b, bucketAssetCount));
				userMap.put(b.getUserId(), user);
			}
		});

		List<User> userList = new ArrayList<User>(userMap.values());
		logger.info("userList:{}", userList);
		return userList;
	}

}
