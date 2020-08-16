package com.astra.hackathon.asset.author.handler;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.astra.hackathon.asset.dao.AssetDao;
import com.astra.hackathon.asset.query.providers.AssetByBucketQueryProvider;
import com.astra.hackathon.asset.query.providers.AssetByNameQueryProvider;
import com.astra.hackathon.asset.query.providers.AssetByStatusQueryProvider;
import com.astra.hackathon.asset.query.providers.AssetByTypeQueryProvider;
import com.astra.hackathon.asset.query.providers.AssetQueryProvider;
import com.astra.hackathon.asset.query.providers.UserAssetCountsQueryProvider;
import com.astra.hackathon.asset.query.providers.UserBucketsQueryProvider;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;

public abstract class AbstractAuthoringHandler {

	@Autowired
	protected CqlSession session;
	@Autowired
	protected AssetDao assetDao;
	protected AssetQueryProvider assetQueryProvider;
	protected AssetByBucketQueryProvider assetByBucketQueryProvider;
	protected AssetByNameQueryProvider assetByNameQueryProvider;
	protected AssetByTypeQueryProvider assetByTypeQueryProvider;
	protected AssetByStatusQueryProvider assetByStatusQueryProvider;
	protected UserBucketsQueryProvider userBucketsQueryProvider;
	protected UserAssetCountsQueryProvider userAssetCountsQueryProvider;

	public AbstractAuthoringHandler() {
		super();
	}

	@PostConstruct
	public void init() {
		assetQueryProvider = new AssetQueryProvider();
		assetByBucketQueryProvider = new AssetByBucketQueryProvider();
		assetByNameQueryProvider = new AssetByNameQueryProvider();
		assetByTypeQueryProvider = new AssetByTypeQueryProvider();
		assetByStatusQueryProvider = new AssetByStatusQueryProvider();
		userAssetCountsQueryProvider = new UserAssetCountsQueryProvider();
		userBucketsQueryProvider = new UserBucketsQueryProvider();
	}

	protected PreparedStatement getPreparedStatement(final String query) {
		return session.prepare(query);
	}

}