package com.astra.hackathon.user.author.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.astra.hackathon.asset.dao.UserAssetCountsDao;
import com.astra.hackathon.asset.dao.UserBucketsDao;
import com.astra.hackathon.asset.mapper.UserAssetCountsMapper;
import com.astra.hackathon.asset.mapper.UserAssetCountsMapperBuilder;
import com.astra.hackathon.asset.mapper.UserBucektsMapper;
import com.astra.hackathon.asset.mapper.UserBucektsMapperBuilder;
import com.datastax.oss.driver.api.core.CqlSession;

@Configuration
public class CassandraModelConfiguration {

	@Bean
	@Autowired
	public UserBucektsMapper userBucektsMapperBuilder(CqlSession session) {
		return new UserBucektsMapperBuilder(session).build();
	}

	@Bean
	@Autowired
	public UserBucketsDao userBucketsDao(UserBucektsMapper userBucektsMapper) {
		return userBucektsMapper.userBucketsDao();
	}

	@Bean
	@Autowired
	public UserAssetCountsMapper userAssetCountsMapperBuilder(CqlSession session) {
		return new UserAssetCountsMapperBuilder(session).build();
	}

	@Bean
	@Autowired
	public UserAssetCountsDao userAssetCountsDao(UserAssetCountsMapper userAssetCountsMapper) {
		return userAssetCountsMapper.userAssetCountsDao();
	}
}
