package com.astra.hackathon.asset.dao;

import com.astra.hackathon.asset.model.UserAssetCounts;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Select;

@Dao
public interface UserAssetCountsDao {

	@Select
	public UserAssetCounts findByUserIdForBucket(String userId, String bucket);
}
