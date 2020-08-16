package com.astra.hackathon.asset.author.api;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.astra.hackathon.asset.author.handler.AssetPublishingHandler;

@RestController
public class AssetPublishingController {
	private static final Logger logger = LoggerFactory.getLogger(AssetPublishingController.class);

	@Autowired
	private AssetPublishingHandler assetPublishingController;

	@RequestMapping(path = "/asset/approve/{identifier}/{approver}", method = RequestMethod.GET)
	public ResponseEntity<String> approve(@PathVariable final String identifier, @PathVariable final String approver) {
		ResponseEntity<String> response = null;
		logger.info("inside AssetPublishingController#approve, received request for:{}, by:{}", identifier, approver);

		// TODO add validation only ADDED asset can be approved
		// TODO only added user can approve
		String approvedAssetIdentifier = assetPublishingController.approve(identifier, approver);
		if (Objects.nonNull(approvedAssetIdentifier)) {
			response = new ResponseEntity<String>(
					"Successfully approved the asset with identifier:" + approvedAssetIdentifier, HttpStatus.OK);
		} else {
			response = new ResponseEntity<String>("Failed to approve the asset.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@RequestMapping(path = "/asset/publish/{identifier}/{publisher}", method = RequestMethod.GET)
	public ResponseEntity<String> publish(@PathVariable final String identifier, @PathVariable final String publisher) {
		ResponseEntity<String> response = null;
		logger.info("inside AssetPublishingController#publish, received request for:{}, by:{}", identifier, publisher);
		// TODO add validation only APPROVED asset can be published
		// TODO only added user can publish
		String publishedAssetIdentifier = assetPublishingController.publish(identifier, publisher);
		if (Objects.nonNull(publishedAssetIdentifier)) {
			response = new ResponseEntity<String>(
					"Successfully published the asset with identifier:" + publishedAssetIdentifier, HttpStatus.OK);
		} else {
			response = new ResponseEntity<String>("Failed to publish the asset.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
}
