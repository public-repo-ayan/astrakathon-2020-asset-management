package com.astra.hackathon.asset.search.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.astra.hackathon.asset.client.Asset;
import com.astra.hackathon.asset.search.handler.AssetSearchHandler;

@RestController
public class AssetSearchController {

	private static final Logger logger = LoggerFactory.getLogger(AssetSearchController.class);

	@Autowired
	private AssetSearchHandler assetSearchHandler;

	@RequestMapping(path = "/assets", method = RequestMethod.GET)
	public ResponseEntity<List<Asset>> listAll() {
		logger.info("inside AssetSearchController#searchAll");
		return new ResponseEntity<List<Asset>>(assetSearchHandler.fetchAll(), HttpStatus.OK);
	}

	@RequestMapping(path = "/assets/{identifier}", method = RequestMethod.GET)
	public ResponseEntity<Asset> getAsset(@PathVariable final String identifier) {
		logger.info("inside AssetSearchController#getAsset with id:{}", identifier);
		return new ResponseEntity<Asset>(assetSearchHandler.fetchByIdentifier(identifier), HttpStatus.OK);
	}

	@RequestMapping(path = "/assets/buckets/{bucketName}", method = RequestMethod.GET)
	public ResponseEntity<List<Asset>> getAssetsByBucket(@PathVariable final String bucketName) {
		logger.info("inside AssetSearchController#getAssetsByBucket with bucket:{}", bucketName);
		return new ResponseEntity<List<Asset>>(assetSearchHandler.fetchByBucket(bucketName), HttpStatus.OK);
	}

	@RequestMapping(path = "/assets/buckets/{bucketName}/{name}", method = RequestMethod.GET)
	public ResponseEntity<Asset> getAssetByBucket(@PathVariable final String bucketName,
			@PathVariable final String name) {
		logger.info("inside AssetSearchController#getAssetByBucket with bucket:{}, name:{}", bucketName, name);
		return new ResponseEntity<Asset>(assetSearchHandler.fetchByBucketForName(bucketName, name), HttpStatus.OK);
	}

	@RequestMapping(path = "/assets/{name}/buckets", method = RequestMethod.GET)
	public ResponseEntity<List<Asset>> getAssetsByName(@PathVariable final String name) {
		logger.info("inside AssetSearchController#getAssetsByName with name:{}", name);
		return new ResponseEntity<List<Asset>>(assetSearchHandler.fetchByName(name), HttpStatus.OK);
	}

	@RequestMapping(path = "/assets/{name}/buckets/{bucketName}", method = RequestMethod.GET)
	public ResponseEntity<Asset> getBucketsByAsset(@PathVariable final String name,
			@PathVariable final String bucketName) {
		logger.info("inside AssetSearchController#getBucketsByAsset with name:{}, bucket:{}", name, bucketName);
		return new ResponseEntity<Asset>(assetSearchHandler.fetchByNameForBucket(name, bucketName), HttpStatus.OK);
	}

	@RequestMapping(path = "/assets/status/{status}", method = RequestMethod.GET)
	public ResponseEntity<List<Asset>> getAssetsByStatus(@PathVariable final String status) {
		logger.info("inside AssetSearchController#getAssetsByStatus with status:{}", status);
		return new ResponseEntity<List<Asset>>(assetSearchHandler.fetchByStatus(status), HttpStatus.OK);
	}

	@RequestMapping(path = "/assets/status/{status}/{bucketName}", method = RequestMethod.GET)
	public ResponseEntity<List<Asset>> getBucketBasedAssetsByStatus(@PathVariable final String status,
			@PathVariable final String bucketName) {
		logger.info("inside AssetSearchController#getBucketBasedAssetsByStatus with status:{}", status);
		return new ResponseEntity<List<Asset>>(assetSearchHandler.fetchByStatusForBucket(status, bucketName),
				HttpStatus.OK);
	}

	@RequestMapping(path = "/assets/status/{status}/{bucketName}/{name}", method = RequestMethod.GET)
	public ResponseEntity<Asset> getAssetBasedOnStatusAndBucket(@PathVariable final String status,
			@PathVariable final String bucketName, @PathVariable final String name) {
		logger.info("inside AssetSearchController#getAssetBasedOnStatusAndBucket with status:{}, bucket:{}, name:{}",
				status, bucketName, name);
		return new ResponseEntity<Asset>(assetSearchHandler.fetchAssetByStatusAndBucket(status, bucketName, name),
				HttpStatus.OK);
	}

	@RequestMapping(path = "/assets/type/{type}", method = RequestMethod.GET)
	public ResponseEntity<List<Asset>> getAssetsByType(@PathVariable final String type) {
		logger.info("inside AssetSearchController#getAssetsByType with type:{}", type);
		return new ResponseEntity<List<Asset>>(assetSearchHandler.fetchByType(type), HttpStatus.OK);
	}

	@RequestMapping(path = "/assets/type/{type}/{bucketName}", method = RequestMethod.GET)
	public ResponseEntity<List<Asset>> getBucketBasedAssetsByType(@PathVariable final String type,
			@PathVariable final String bucketName) {
		logger.info("inside AssetSearchController#getBucketBasedAssetsByType with type:{}, bucket:{}", type,
				bucketName);
		return new ResponseEntity<List<Asset>>(assetSearchHandler.fetchByTypeForBucket(type, bucketName),
				HttpStatus.OK);
	}

	@RequestMapping(path = "/assets/type/{type}/{bucketName}/{name}", method = RequestMethod.GET)
	public ResponseEntity<Asset> getAssetBasedOnTypeAndBucket(@PathVariable final String type,
			@PathVariable final String bucketName, @PathVariable final String name) {
		logger.info("inside AssetSearchController#getAssetBasedOnTypeAndBucket with type:{}, bucket:{}, name:{}", type,
				bucketName, name);
		return new ResponseEntity<Asset>(assetSearchHandler.fetchAssetsByTypeAndBucket(type, bucketName, name),
				HttpStatus.OK);
	}
}
