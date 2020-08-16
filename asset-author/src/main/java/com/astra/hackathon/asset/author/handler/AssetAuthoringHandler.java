package com.astra.hackathon.asset.author.handler;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.astra.hackathon.asset.client.Asset;
import com.astra.hackathon.asset.exceptions.InvalidRequestException;
import com.astra.hackathon.asset.exceptions.StorageException;
import com.astra.hackathon.asset.function.EntityToJsonMapper;
import com.datastax.oss.driver.api.core.AllNodesFailedException;
import com.datastax.oss.driver.api.core.cql.BatchStatement;
import com.datastax.oss.driver.api.core.cql.BatchType;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.servererrors.QueryExecutionException;
import com.datastax.oss.driver.api.core.servererrors.QueryValidationException;
import com.datastax.oss.driver.api.core.uuid.Uuids;

@Service
public class AssetAuthoringHandler extends AbstractAuthoringHandler {

	private static final Logger logger = LoggerFactory.getLogger(AssetAuthoringHandler.class);

	public String saveAsset(final Asset assetRequest) {
		logger.info("saving asset with name:{}, bucket:{}", assetRequest.getName(), assetRequest.getBucket());
		ResultSet resultSet = null;
		UUID assetIdentifier = Uuids.random();
		Instant addedDate = Instant.now();

		try {
			BatchStatement batchStmt = BatchStatement.builder(BatchType.LOGGED)
					.addStatement(getPreparedStatement(assetQueryProvider.insert(assetRequest)).bind(assetIdentifier,
							assetRequest.getName(), assetRequest.getBucket(), assetRequest.getType().name(),
							assetRequest.getStatus().name(), addedDate, assetRequest.getAddedBy(),
							assetRequest.getMetadata()))
					.addStatement(getPreparedStatement(assetByBucketQueryProvider.insert(assetRequest)).bind(
							assetRequest.getBucket(), assetRequest.getName(), assetIdentifier,
							assetRequest.getType().name(), assetRequest.getStatus().name(), addedDate,
							assetRequest.getAddedBy(), assetRequest.getMetadata()))
					.addStatement(getPreparedStatement(assetByNameQueryProvider.insert(assetRequest)).bind(
							assetRequest.getName(), assetRequest.getBucket(), assetIdentifier,
							assetRequest.getType().name(), assetRequest.getStatus().name(), addedDate,
							assetRequest.getAddedBy(), assetRequest.getMetadata()))
					.addStatement(getPreparedStatement(assetByTypeQueryProvider.insert(assetRequest)).bind(
							assetRequest.getType().name(), assetRequest.getBucket(), assetRequest.getName(),
							assetIdentifier, assetRequest.getStatus().name(), addedDate, assetRequest.getAddedBy(),
							assetRequest.getMetadata()))
					.addStatement(getPreparedStatement(assetByStatusQueryProvider.insert(assetRequest)).bind(
							assetRequest.getStatus().name(), assetRequest.getBucket(), assetRequest.getName(),
							assetIdentifier, assetRequest.getType().name(), addedDate, assetRequest.getAddedBy(),
							assetRequest.getMetadata()))
					.addStatement(getPreparedStatement(userBucketsQueryProvider.update(assetRequest)).bind(addedDate,
							assetRequest.getAddedBy(), assetRequest.getBucket()))
					.build();

			resultSet = session.execute(batchStmt);
			if (Objects.nonNull(resultSet)) {
				logger.info("successfully saved the asset with identifier:{}", assetIdentifier.toString());

				// updating counts in separate statement as Counter and non-counter mutations
				// cannot exist in the same batch
				ResultSet counterResultSet = session
						.execute(getPreparedStatement(userAssetCountsQueryProvider.insert(assetRequest))
								.bind(assetRequest.getAddedBy(), assetRequest.getBucket()));
				if (Objects.isNull(counterResultSet)) {
					logger.warn("something wrong when updating asset counters");
				}

				return assetIdentifier.toString();
			}
		} catch (AllNodesFailedException | QueryExecutionException | QueryValidationException e) {
			logger.error("error while saving asset in database", e);
			throw new StorageException(e);
		}

		return null;
	}

	public String updateAsset(final Asset updateAssetRequest) {
		logger.info("update asset with body:{}", updateAssetRequest);
		ResultSet resultSet = null;
		ZonedDateTime modifiedDate = ZonedDateTime.now();
		UUID requestedUuid = UUID.fromString(updateAssetRequest.getIdentifier());

		try {
			BatchStatement batchStmt = BatchStatement.builder(BatchType.LOGGED)
					.addStatement(getPreparedStatement(assetQueryProvider.update(updateAssetRequest)).bind(modifiedDate,
							updateAssetRequest.getModifiedBy(), updateAssetRequest.getMetadata(), requestedUuid))
					.addStatement(getPreparedStatement(assetByBucketQueryProvider.update(updateAssetRequest)).bind(
							modifiedDate, updateAssetRequest.getModifiedBy(), updateAssetRequest.getMetadata(),
							updateAssetRequest.getBucket(), updateAssetRequest.getName()))
					.addStatement(getPreparedStatement(assetByNameQueryProvider.update(updateAssetRequest)).bind(
							modifiedDate, updateAssetRequest.getModifiedBy(), updateAssetRequest.getMetadata(),
							updateAssetRequest.getName(), updateAssetRequest.getBucket()))
					.addStatement(getPreparedStatement(assetByTypeQueryProvider.update(updateAssetRequest)).bind(
							modifiedDate, updateAssetRequest.getModifiedBy(), updateAssetRequest.getMetadata(),
							updateAssetRequest.getType().name(), updateAssetRequest.getBucket(),
							updateAssetRequest.getName()))
					.addStatement(getPreparedStatement(assetByStatusQueryProvider.update(updateAssetRequest)).bind(
							modifiedDate, updateAssetRequest.getModifiedBy(), updateAssetRequest.getMetadata(),
							updateAssetRequest.getStatus().name(), updateAssetRequest.getBucket(),
							updateAssetRequest.getName()))
					.addStatement(getPreparedStatement(userBucketsQueryProvider.update(updateAssetRequest))
							.bind(modifiedDate, updateAssetRequest.getModifiedBy(), updateAssetRequest.getBucket()))
					.build();

			resultSet = session.execute(batchStmt);
			if (Objects.nonNull(resultSet)) {
				logger.info("successfully updated the asset with identifier:{}", updateAssetRequest.getIdentifier());
				return updateAssetRequest.getIdentifier();
			}
		} catch (AllNodesFailedException | QueryExecutionException | QueryValidationException e) {
			logger.error("error while updating asset in database", e);
			throw new StorageException(e);
		}

		return null;
	}

	public String deleteAsset(final String identifier) {
		logger.info("delete asset with identifier:{}", identifier);
		ResultSet resultSet = null;
		ZonedDateTime modifiedDate = ZonedDateTime.now();
		UUID requestedUuid = UUID.fromString(identifier);

		try {

			com.astra.hackathon.asset.model.Asset requestedAsset = assetDao.findByIdentifier(requestedUuid);
			if (Objects.isNull(requestedAsset)) {
				logger.error("requested asset not found with identifier:{}", identifier);
				throw new InvalidRequestException("requested asset not found");
			}

			Asset requestedAssetClient = EntityToJsonMapper.clientAsset(requestedAsset);

			BatchStatement batchStmt = BatchStatement.builder(BatchType.LOGGED)
					.addStatement(
							getPreparedStatement(assetQueryProvider.delete(requestedAssetClient)).bind(requestedUuid))
					.addStatement(getPreparedStatement(assetByBucketQueryProvider.delete(requestedAssetClient))
							.bind(requestedAsset.getBucket(), requestedAsset.getName()))
					.addStatement(getPreparedStatement(assetByNameQueryProvider.delete(requestedAssetClient))
							.bind(requestedAsset.getName(), requestedAsset.getBucket()))
					.addStatement(getPreparedStatement(assetByTypeQueryProvider.delete(requestedAssetClient))
							.bind(requestedAsset.getType(), requestedAsset.getBucket(), requestedAsset.getName()))
					.addStatement(getPreparedStatement(assetByStatusQueryProvider.delete(requestedAssetClient))
							.bind(requestedAsset.getStatus(), requestedAsset.getBucket(), requestedAsset.getName()))
					.addStatement(getPreparedStatement(userBucketsQueryProvider.update(requestedAssetClient))
							.bind(modifiedDate,
							requestedAsset.getAddedBy(), requestedAsset.getBucket()))
					.build();

			resultSet = session.execute(batchStmt);
			if (Objects.nonNull(resultSet)) {
				logger.info("successfully deleted the asset with identifier:{}", identifier);

				// updating counts in separate statement as Counter and non-counter mutations
				// cannot exist in the same batch
				ResultSet counterResultSet = session
						.execute(getPreparedStatement(userAssetCountsQueryProvider.delete(requestedAssetClient))
								.bind(requestedAssetClient.getAddedBy(), requestedAssetClient.getBucket()));
				if (Objects.isNull(counterResultSet)) {
					logger.warn("something wrong when updating asset counters");
				}
				return identifier;
			}
		} catch (AllNodesFailedException | QueryExecutionException | QueryValidationException e) {
			logger.error("error while deleting asset in database", e);
			throw new StorageException(e);
		}

		return null;
	}
}
