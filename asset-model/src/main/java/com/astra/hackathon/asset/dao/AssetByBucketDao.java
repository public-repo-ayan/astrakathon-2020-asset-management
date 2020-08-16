package com.astra.hackathon.asset.dao;

import com.astra.hackathon.asset.model.AssetByBucket;
import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Select;

@Dao
public interface AssetByBucketDao {

	@Select
	public PagingIterable<AssetByBucket> findByBucket(String bucket);

	@Select
	public AssetByBucket findByBucketForName(String bucket, String name);
}
