package com.astra.hackathon.asset.dao;

import com.astra.hackathon.asset.model.AssetByName;
import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Select;

@Dao
public interface AssetByNameDao {

	@Select
	public PagingIterable<AssetByName> findByName(String name);

	@Select
	public AssetByName findByNameForBucket(String name, String bucket);
}
