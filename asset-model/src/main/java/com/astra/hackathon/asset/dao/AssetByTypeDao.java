package com.astra.hackathon.asset.dao;

import com.astra.hackathon.asset.model.AssetByType;
import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Select;

@Dao
public interface AssetByTypeDao {

	@Select
	public PagingIterable<AssetByType> findByType(String type);

	@Select
	public PagingIterable<AssetByType> findByTypeForBucket(String type, String bucket);

	@Select
	public AssetByType findByTypeForBucketForName(String type, String bucket, String name);
}
