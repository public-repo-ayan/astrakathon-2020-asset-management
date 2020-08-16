package com.astra.hackathon.asset.search.handler;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.astra.hackathon.asset.dao.AssetByBucketDao;
import com.astra.hackathon.asset.dao.AssetByNameDao;
import com.astra.hackathon.asset.dao.AssetByStatusDao;
import com.astra.hackathon.asset.dao.AssetByTypeDao;
import com.astra.hackathon.asset.dao.AssetDao;
import com.astra.hackathon.asset.function.EntityToJsonMapper;
import com.astra.hackathon.asset.model.Asset;
import com.astra.hackathon.asset.model.AssetByBucket;
import com.astra.hackathon.asset.model.AssetByName;
import com.astra.hackathon.asset.model.AssetByStatus;
import com.astra.hackathon.asset.model.AssetByType;
import com.datastax.oss.driver.api.core.PagingIterable;

@Component
public class AssetSearchHandler {
	private static final Logger logger = LoggerFactory.getLogger(AssetSearchHandler.class);

	@Autowired
	private AssetDao assetDao;

	@Autowired
	private AssetByBucketDao assetByBucketDao;

	@Autowired
	private AssetByNameDao assetByNameDao;

	@Autowired
	private AssetByStatusDao assetByStatusDao;

	@Autowired
	private AssetByTypeDao assetByTypeDao;

	public List<com.astra.hackathon.asset.client.Asset> fetchAll() {
		logger.info("inside AssetSearchHandler#fetchAll");
		PagingIterable<Asset> dbResults = assetDao.findAll();
		List<Asset> assetDbResult = dbResults.all();
		logger.info("assetDbResult:{}", assetDbResult);
		List<com.astra.hackathon.asset.client.Asset> assetList = assetDbResult.stream()
				.map(EntityToJsonMapper::clientAsset).collect(Collectors.toList());
		return assetList;
	}

	public com.astra.hackathon.asset.client.Asset fetchByIdentifier(final String identifier) {
		logger.info("inside AssetSearchHandler#fetchByIdentifier");
		Asset dbResult = assetDao.findByIdentifier(UUID.fromString(identifier));
		logger.info("dbResult:{}", dbResult);
		return EntityToJsonMapper.clientAsset(dbResult);
	}

	public List<com.astra.hackathon.asset.client.Asset> fetchByBucket(final String bucket) {
		logger.info("inside AssetSearchHandler#fetchByBucket");
		PagingIterable<AssetByBucket> dbResults = assetByBucketDao.findByBucket(bucket);
		List<AssetByBucket> assetDbResult = dbResults.all();
		logger.info("assetDbResult:{}", assetDbResult);
		List<com.astra.hackathon.asset.client.Asset> assetList = assetDbResult.stream()
				.map(EntityToJsonMapper::clientAsset).collect(Collectors.toList());
		return assetList;
	}

	public com.astra.hackathon.asset.client.Asset fetchByBucketForName(final String bucket, final String name) {
		logger.info("inside AssetSearchHandler#fetchByBucketForName");
		AssetByBucket dbResult = assetByBucketDao.findByBucketForName(bucket, name);
		logger.info("dbResult:{}", dbResult);
		return EntityToJsonMapper.clientAsset(dbResult);
	}

	public List<com.astra.hackathon.asset.client.Asset> fetchByName(final String name) {
		logger.info("inside AssetSearchHandler#fetchByName");
		PagingIterable<AssetByName> dbResults = assetByNameDao.findByName(name);
		List<AssetByName> assetDbResult = dbResults.all();
		logger.info("assetDbResult:{}", assetDbResult);
		List<com.astra.hackathon.asset.client.Asset> assetList = assetDbResult.stream()
				.map(EntityToJsonMapper::clientAsset).collect(Collectors.toList());
		return assetList;
	}

	public com.astra.hackathon.asset.client.Asset fetchByNameForBucket(final String name, final String bucket) {
		logger.info("inside AssetSearchHandler#fetchByNameForBucket");
		AssetByName dbResult = assetByNameDao.findByNameForBucket(name, bucket);
		logger.info("dbResult:{}", dbResult);
		return EntityToJsonMapper.clientAsset(dbResult);
	}

	public List<com.astra.hackathon.asset.client.Asset> fetchByStatus(final String status) {
		logger.info("inside AssetSearchHandler#fetchByStatus");
		PagingIterable<AssetByStatus> dbResults = assetByStatusDao.findByStatus(status);
		List<AssetByStatus> assetDbResult = dbResults.all();
		logger.info("assetDbResult:{}", assetDbResult);
		List<com.astra.hackathon.asset.client.Asset> assetList = assetDbResult.stream()
				.map(EntityToJsonMapper::clientAsset).collect(Collectors.toList());
		return assetList;
	}

	public List<com.astra.hackathon.asset.client.Asset> fetchByStatusForBucket(final String status,
			final String bucket) {
		logger.info("inside AssetSearchHandler#fetchByStatusForBucket");
		PagingIterable<AssetByStatus> dbResults = assetByStatusDao.findByStatusForBucket(status, bucket);
		List<AssetByStatus> assetDbResult = dbResults.all();
		logger.info("assetDbResult:{}", assetDbResult);
		List<com.astra.hackathon.asset.client.Asset> assetList = assetDbResult.stream()
				.map(EntityToJsonMapper::clientAsset).collect(Collectors.toList());
		return assetList;
	}

	public com.astra.hackathon.asset.client.Asset fetchAssetByStatusAndBucket(final String status, final String bucket,
			final String name) {
		logger.info("inside AssetSearchHandler#fetchAssetByStatusAndBucket");
		AssetByStatus dbResult = assetByStatusDao.findByStatusForBucketForName(status, bucket, name);
		logger.info("dbResult:{}", dbResult);
		return EntityToJsonMapper.clientAsset(dbResult);
	}

	public List<com.astra.hackathon.asset.client.Asset> fetchByType(final String type) {
		logger.info("inside AssetSearchHandler#fetchByType");
		PagingIterable<AssetByType> dbResults = assetByTypeDao.findByType(type);
		List<AssetByType> assetDbResult = dbResults.all();
		logger.info("assetDbResult:{}", assetDbResult);
		List<com.astra.hackathon.asset.client.Asset> assetList = assetDbResult.stream()
				.map(EntityToJsonMapper::clientAsset).collect(Collectors.toList());
		return assetList;
	}

	public List<com.astra.hackathon.asset.client.Asset> fetchByTypeForBucket(final String type, final String bucket) {
		logger.info("inside AssetSearchHandler#fetchByTypeForBucket");
		PagingIterable<AssetByType> dbResults = assetByTypeDao.findByTypeForBucket(type, bucket);
		List<AssetByType> assetDbResult = dbResults.all();
		logger.info("assetDbResult:{}", assetDbResult);
		List<com.astra.hackathon.asset.client.Asset> assetList = assetDbResult.stream()
				.map(EntityToJsonMapper::clientAsset).collect(Collectors.toList());
		return assetList;
	}

	public com.astra.hackathon.asset.client.Asset fetchAssetsByTypeAndBucket(final String type, final String bucket,
			final String name) {
		logger.info("inside AssetSearchHandler#fetchAssetsByTypeAndBucket");
		AssetByType dbResult = assetByTypeDao.findByTypeForBucketForName(type, bucket, name);
		logger.info("dbResult:{}", dbResult);
		return EntityToJsonMapper.clientAsset(dbResult);
	}
}
