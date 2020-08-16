package com.astra.hackathon.asset.dao;

import java.util.UUID;

import com.astra.hackathon.asset.model.Asset;
import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;

@Dao
public interface AssetDao {
	
	@Select
	public PagingIterable<Asset> findAll();

	@Select
	public Asset findByIdentifier(UUID assetIdentifier);
	
	@Insert
	public void save(Asset asset);
}
