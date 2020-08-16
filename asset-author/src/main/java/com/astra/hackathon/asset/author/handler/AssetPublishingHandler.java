package com.astra.hackathon.asset.author.handler;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.astra.hackathon.asset.client.Asset;
import com.astra.hackathon.asset.client.AssetStatus;
import com.astra.hackathon.asset.exceptions.InvalidRequestException;
import com.astra.hackathon.asset.exceptions.StorageException;
import com.astra.hackathon.asset.function.EntityToJsonMapper;
import com.datastax.oss.driver.api.core.AllNodesFailedException;
import com.datastax.oss.driver.api.core.cql.BatchStatement;
import com.datastax.oss.driver.api.core.cql.BatchType;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.servererrors.QueryExecutionException;
import com.datastax.oss.driver.api.core.servererrors.QueryValidationException;

@Service
public class AssetPublishingHandler extends AbstractAuthoringHandler {
	private static final Logger logger = LoggerFactory.getLogger(AssetAuthoringHandler.class);

	public String approve(final String identifier, final String approvedBy) {
		logger.info("approve asset with identifier:{}", identifier);
		ResultSet resultSet = null;
		ZonedDateTime approvedDate = ZonedDateTime.now();
		UUID requestedUuid = UUID.fromString(identifier);

		try {
			com.astra.hackathon.asset.model.Asset requestedAsset = assetDao.findByIdentifier(requestedUuid);
			if (Objects.isNull(requestedAsset)) {
				logger.error("requested asset not found with identifier:{}", identifier);
				throw new InvalidRequestException("requested asset not found");
			}

			Asset requestedAssetClient = EntityToJsonMapper.clientAsset(requestedAsset);

			BatchStatement batchStmt = BatchStatement.builder(BatchType.LOGGED)
					.addStatement(getPreparedStatement(assetQueryProvider.approve(requestedAssetClient))
							.bind(AssetStatus.Approved.name(), approvedDate, approvedBy, requestedUuid))
					.addStatement(getPreparedStatement(assetByBucketQueryProvider.approve(requestedAssetClient)).bind(
							AssetStatus.Approved.name(), approvedDate, approvedBy, requestedAsset.getBucket(),
							requestedAsset.getName()))
					.addStatement(getPreparedStatement(assetByNameQueryProvider.approve(requestedAssetClient)).bind(
							AssetStatus.Approved.name(), approvedDate, approvedBy, requestedAsset.getName(),
							requestedAsset.getBucket()))
					.addStatement(getPreparedStatement(assetByTypeQueryProvider.approve(requestedAssetClient)).bind(
							AssetStatus.Approved.name(), approvedDate, approvedBy, requestedAsset.getType(),
							requestedAsset.getBucket(), requestedAsset.getName()))
					.addStatement(getPreparedStatement(assetByStatusQueryProvider.approve(requestedAssetClient)).bind(
							AssetStatus.Approved.name(), requestedAsset.getBucket(), requestedAsset.getName(),
							requestedAsset.getIdentifier(), requestedAsset.getType(), requestedAsset.getAddedDate(),
							requestedAsset.getAddedBy(), requestedAsset.getMetadata(), approvedDate, approvedBy))
					.addStatement(getPreparedStatement(assetByStatusQueryProvider.delete(requestedAssetClient))
							.bind(AssetStatus.Added.name(), requestedAsset.getBucket(), requestedAsset.getName()))
					.build();

			resultSet = session.execute(batchStmt);
			if (Objects.nonNull(resultSet)) {
				logger.info("successfully approved the asset with identifier:{}", identifier);

				// updating counts in separate statement as Counter and non-counter mutations
				// cannot exist in the same batch
				ResultSet counterResultSet = session
						.execute(getPreparedStatement(userAssetCountsQueryProvider.approve(requestedAssetClient))
								.bind(approvedBy, requestedAssetClient.getBucket()));
				if (Objects.isNull(counterResultSet)) {
					logger.warn("something wrong when updating asset counters");
				}

				return identifier;
			}
		} catch (AllNodesFailedException | QueryExecutionException | QueryValidationException e) {
			logger.error("error while approving asset in database", e);
			throw new StorageException(e);
		}

		return null;
	}

	public String publish(final String identifier, final String publishBy) {
		logger.info("publish asset with identifier:{}", identifier);
		ResultSet resultSet = null;
		ZonedDateTime publishedDate = ZonedDateTime.now();
		UUID requestedUuid = UUID.fromString(identifier);

		try {
			com.astra.hackathon.asset.model.Asset requestedAsset = assetDao.findByIdentifier(requestedUuid);
			if (Objects.isNull(requestedAsset)) {
				logger.error("requested asset not found with identifier:{}", identifier);
				throw new InvalidRequestException("requested asset not found");
			}

			Asset requestedAssetClient = EntityToJsonMapper.clientAsset(requestedAsset);

			BatchStatement batchStmt = BatchStatement.builder(BatchType.LOGGED)
					.addStatement(getPreparedStatement(assetQueryProvider.publish(requestedAssetClient))
							.bind(AssetStatus.Published.name(), publishedDate, publishBy, requestedUuid))
					.addStatement(getPreparedStatement(assetByBucketQueryProvider.publish(requestedAssetClient)).bind(
							AssetStatus.Published.name(), publishedDate, publishBy, requestedAsset.getBucket(),
							requestedAsset.getName()))
					.addStatement(getPreparedStatement(assetByNameQueryProvider.publish(requestedAssetClient)).bind(
							AssetStatus.Published.name(), publishedDate, publishBy, requestedAsset.getName(),
							requestedAsset.getBucket()))
					.addStatement(getPreparedStatement(assetByTypeQueryProvider.publish(requestedAssetClient)).bind(
							AssetStatus.Published.name(), publishedDate, publishBy, requestedAsset.getType(),
							requestedAsset.getBucket(), requestedAsset.getName()))
					.addStatement(getPreparedStatement(assetByStatusQueryProvider.publish(requestedAssetClient)).bind(
							AssetStatus.Published.name(), requestedAsset.getBucket(), requestedAsset.getName(),
							requestedAsset.getIdentifier(), requestedAsset.getType(), requestedAsset.getAddedDate(),
							requestedAsset.getAddedBy(), requestedAsset.getMetadata(), requestedAsset.getApproveDate(),
							requestedAsset.getApprovedBy(), publishedDate, publishBy))
					.addStatement(getPreparedStatement(assetByStatusQueryProvider.delete(requestedAssetClient))
							.bind(AssetStatus.Approved.name(), requestedAsset.getBucket(), requestedAsset.getName()))
					.build();

			resultSet = session.execute(batchStmt);
			if (Objects.nonNull(resultSet)) {
				logger.info("successfully published the asset with identifier:{}", identifier);

				// updating counts in separate statement as Counter and non-counter mutations
				// cannot exist in the same batch
				ResultSet counterResultSet = session
						.execute(getPreparedStatement(userAssetCountsQueryProvider.publish(requestedAssetClient))
								.bind(publishBy, requestedAssetClient.getBucket()));
				if (Objects.isNull(counterResultSet)) {
					logger.warn("something wrong when updating asset counters");
				}

				return identifier;
			}
		} catch (AllNodesFailedException | QueryExecutionException | QueryValidationException e) {
			logger.error("error while publishing asset in database", e);
			throw new StorageException(e);
		}

		return null;
	}
}
