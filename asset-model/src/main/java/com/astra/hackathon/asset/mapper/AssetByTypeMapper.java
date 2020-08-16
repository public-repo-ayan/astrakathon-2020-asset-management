package com.astra.hackathon.asset.mapper;

import com.astra.hackathon.asset.dao.AssetByTypeDao;
import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

@Mapper
public interface AssetByTypeMapper {

	@DaoFactory
	public AssetByTypeDao assetByTypeDao();
}
