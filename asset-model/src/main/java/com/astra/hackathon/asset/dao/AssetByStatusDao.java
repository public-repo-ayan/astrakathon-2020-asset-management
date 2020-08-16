package com.astra.hackathon.asset.dao;

import com.astra.hackathon.asset.model.AssetByStatus;
import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Select;

@Dao
public interface AssetByStatusDao {

	@Select
	public PagingIterable<AssetByStatus> findByStatus(String status);

	@Select
	public PagingIterable<AssetByStatus> findByStatusForBucket(String status, String bucket);

	@Select
	public AssetByStatus findByStatusForBucketForName(String status, String bucket, String name);
}
