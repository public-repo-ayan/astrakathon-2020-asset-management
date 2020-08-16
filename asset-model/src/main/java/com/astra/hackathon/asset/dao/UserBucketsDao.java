package com.astra.hackathon.asset.dao;

import com.astra.hackathon.asset.model.UserBuckets;
import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import com.datastax.oss.driver.api.mapper.annotations.Update;

@Dao
public interface UserBucketsDao {
	
	@Select
	public PagingIterable<UserBuckets> findAll();

	@Select
	public PagingIterable<UserBuckets> findByUserId(String userId);
	
	@Select
	public UserBuckets findByUserIdForBucket(String userId, String bucket);

	@Insert
	public void save(UserBuckets userBucket);

	@Update
	public void update(UserBuckets userBucket);
}
