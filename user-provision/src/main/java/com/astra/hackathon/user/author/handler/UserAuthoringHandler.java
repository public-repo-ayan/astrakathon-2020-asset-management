package com.astra.hackathon.user.author.handler;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astra.hackathon.asset.dao.UserBucketsDao;
import com.astra.hackathon.asset.exceptions.StorageException;
import com.astra.hackathon.asset.model.UserBuckets;

@Service
public class UserAuthoringHandler {
	private static final Logger logger = LoggerFactory.getLogger(UserAuthoringHandler.class);

	@Autowired
	private UserBucketsDao userBucketsDao;

	public String saveUserBucket(final String userId, final String bucket, final String description) {

		UserBuckets savingUserBuckets = new UserBuckets();
		savingUserBuckets.setUserId(userId);
		savingUserBuckets.setBucket(bucket);
		savingUserBuckets.setDescription(description);
		savingUserBuckets.setActive(true);
		savingUserBuckets.setCreateDate(Instant.now());
		savingUserBuckets.setLastUpdatedDate(Instant.now());

		try {
			userBucketsDao.save(savingUserBuckets);
			logger.info("successfully saved the bucket:{} under id:{}", bucket, userId);
			return userId;
		} catch (Exception e) {
			throw new StorageException("error in saving user bucket", e);
		}
	}

	public String inactivateUserBucket(final String userId, final String bucket) {

		try {
			UserBuckets savedUserBucket = userBucketsDao.findByUserIdForBucket(userId, bucket);
			// set active=false
			savedUserBucket.setActive(false);
			savedUserBucket.setLastUpdatedDate(Instant.now());

			userBucketsDao.update(savedUserBucket);
			logger.info("successfully updated the bucket:{} under id:{}", bucket, userId);
			return userId;
		} catch (Exception e) {
			throw new StorageException("error in updating user bucket", e);
		}
	}

}
