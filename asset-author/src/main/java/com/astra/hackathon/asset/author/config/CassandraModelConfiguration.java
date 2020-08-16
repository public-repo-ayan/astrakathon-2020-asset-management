package com.astra.hackathon.asset.author.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.astra.hackathon.asset.dao.AssetDao;
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
}
