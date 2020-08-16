package com.astra.hackathon.asset.author.api;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.astra.hackathon.asset.author.handler.AssetAuthoringHandler;
import com.astra.hackathon.asset.client.Asset;
import com.astra.hackathon.asset.exceptions.InvalidRequestException;

@RestController
public class AssetAuthoringController {
	private static final Logger logger = LoggerFactory.getLogger(AssetAuthoringController.class);

	@Autowired
	private AssetAuthoringHandler assetAuthoringHandler;

	@RequestMapping(path = "/asset", method = RequestMethod.POST)
	public ResponseEntity<String> save(@RequestBody final Asset assetRequest) {
		ResponseEntity<String> response = null;
		logger.info("inside AssetAuthoringController#save, received request:{}", assetRequest);
		if (Objects.isNull(assetRequest.getName()) || assetRequest.getName().isEmpty()) {
			throw new InvalidRequestException("name is mandatory for asset");
		}

		if (Objects.isNull(assetRequest.getBucket()) || assetRequest.getBucket().isEmpty()) {
			throw new InvalidRequestException("bucket is mandatory for asset");
		}

		if (Objects.isNull(assetRequest.getType())) {
			throw new InvalidRequestException("type is mandatory for asset");
		}

		// TODO add more validations

		String savedAssetIdentifier = assetAuthoringHandler.saveAsset(assetRequest);
		if (Objects.nonNull(savedAssetIdentifier)) {
			response = new ResponseEntity<String>(
					"Successfully saved the asset with identifier:" + savedAssetIdentifier, HttpStatus.OK);
		} else {
			response = new ResponseEntity<String>("Failed to save the asset.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@RequestMapping(path = "/asset/{identifier}", method = RequestMethod.PUT)
	public ResponseEntity<String> update(@PathVariable final String identifier, @RequestBody final Asset assetRequest) {
		ResponseEntity<String> response = null;
		logger.info("inside AssetAuthoringController#update, received request:{}", assetRequest);
		if ((Objects.isNull(identifier) || identifier.isEmpty())
				|| (Objects.isNull(assetRequest.getIdentifier()) && assetRequest.getIdentifier().isEmpty())) {
			throw new InvalidRequestException("identifier is mandatory for updating asset");
		}

		if (!identifier.equals(assetRequest.getIdentifier())) {
			throw new InvalidRequestException("identifier did not match with request body");
		}

		String updatedAssetIdentifier = assetAuthoringHandler.updateAsset(assetRequest);
		if (Objects.nonNull(updatedAssetIdentifier)) {
			response = new ResponseEntity<String>(
					"Successfully updated the asset with identifier:" + updatedAssetIdentifier, HttpStatus.OK);
		} else {
			response = new ResponseEntity<String>("Failed to update the asset.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@RequestMapping(path = "/asset/{identifier}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable final String identifier) {
		ResponseEntity<String> response = null;
		logger.info("inside AssetAuthoringController#delete, received request for:{}", identifier);

		String deletedAssetIdentifier = assetAuthoringHandler.deleteAsset(identifier);
		if (Objects.nonNull(deletedAssetIdentifier)) {
			response = new ResponseEntity<String>(
					"Successfully deleted the asset with identifier:" + deletedAssetIdentifier, HttpStatus.OK);
		} else {
			response = new ResponseEntity<String>("Failed to delete the asset.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

}
