package com.astra.hackathon.asset.search.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.astra.hackathon.asset.dao.AssetByBucketDao;
import com.astra.hackathon.asset.dao.AssetByNameDao;
import com.astra.hackathon.asset.dao.AssetByStatusDao;
import com.astra.hackathon.asset.dao.AssetByTypeDao;
import com.astra.hackathon.asset.dao.AssetDao;
import com.astra.hackathon.asset.mapper.AssetByBucketMapper;
import com.astra.hackathon.asset.mapper.AssetByBucketMapperBuilder;
import com.astra.hackathon.asset.mapper.AssetByNameMapper;
import com.astra.hackathon.asset.mapper.AssetByNameMapperBuilder;
import com.astra.hackathon.asset.mapper.AssetByStatusMapper;
import com.astra.hackathon.asset.mapper.AssetByStatusMapperBuilder;
import com.astra.hackathon.asset.mapper.AssetByTypeMapper;
import com.astra.hackathon.asset.mapper.AssetByTypeMapperBuilder;
import com.astra.hackathon.asset.mapper.AssetMapper;
import com.astra.hackathon.asset.mapper.AssetMapperBuilder;
import com.datastax.oss.driver.api.core.CqlSession;

@Configuration
public class CassandraModelConfiguration {

	@Bean
	@Autowired
	public AssetMapper assetMapperBuilder(CqlSession session) {
		return new AssetMapperBuilder(session).build();
	}

	@Bean
	@Autowired
	public AssetDao assetDao(AssetMapper assetMapper) {
		return assetMapper.assetDao();
	}

	@Bean
	@Autowired
	public AssetByBucketMapper assetByBucketMapperBuilder(CqlSession session) {
		return new AssetByBucketMapperBuilder(session).build();
	}

	@Bean
	@Autowired
	public AssetByBucketDao assetByBucketDao(AssetByBucketMapper assetByBucketMapper) {
		return assetByBucketMapper.assetByBucketDao();
	}

	@Bean
	@Autowired
	public AssetByNameMapper assetByNameMapperBuilder(CqlSession session) {
		return new AssetByNameMapperBuilder(session).build();
	}

	@Bean
	@Autowired
	public AssetByNameDao assetByNameDao(AssetByNameMapper assetByNameMapper) {
		return assetByNameMapper.assetByNameDao();
	}

	@Bean
	@Autowired
	public AssetByStatusMapper assetByStatusMapperBuilder(CqlSession session) {
		return new AssetByStatusMapperBuilder(session).build();
	}

	@Bean
	@Autowired
	public AssetByStatusDao assetByStatusDao(AssetByStatusMapper assetByStatusMapper) {
		return assetByStatusMapper.assetByStatusDao();
	}

	@Bean
	@Autowired
	public AssetByTypeMapper assetByTypeMapperBuilder(CqlSession session) {
		return new AssetByTypeMapperBuilder(session).build();
	}

	@Bean
	@Autowired
	public AssetByTypeDao assetByTypeDao(AssetByTypeMapper assetByTypeMapper) {
		return assetByTypeMapper.assetByTypeDao();
	}
}
