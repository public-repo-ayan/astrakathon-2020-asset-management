package com.astra.hackathon.asset.mapper;

import com.astra.hackathon.asset.dao.AssetByBucketDao;
import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

@Mapper
public interface AssetByBucketMapper {

	@DaoFactory
	public AssetByBucketDao assetByBucketDao();
}
