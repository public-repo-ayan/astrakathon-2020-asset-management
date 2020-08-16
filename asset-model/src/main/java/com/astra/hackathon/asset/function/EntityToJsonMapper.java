package com.astra.hackathon.asset.function;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

import com.astra.hackathon.asset.client.AssetStatus;
import com.astra.hackathon.asset.client.AssetType;
import com.astra.hackathon.asset.model.Asset;
import com.astra.hackathon.asset.model.AssetByBucket;
import com.astra.hackathon.asset.model.AssetByName;
import com.astra.hackathon.asset.model.AssetByStatus;
import com.astra.hackathon.asset.model.AssetByType;
import com.astra.hackathon.asset.model.UserAssetCounts;
import com.astra.hackathon.asset.model.UserBuckets;
import com.astra.hackathon.user.client.UserBucket;

public class EntityToJsonMapper {

	public static com.astra.hackathon.asset.client.Asset clientAsset(Asset assetModel) {
		com.astra.hackathon.asset.client.Asset asset = new com.astra.hackathon.asset.client.Asset();
		if (Objects.nonNull(assetModel)) {
			asset.setIdentifier(assetModel.getIdentifier().toString());
			asset.setName(assetModel.getName());
			asset.setBucket(assetModel.getBucket());
			asset.setType(AssetType.valueOf(assetModel.getType()));
			asset.setStatus(AssetStatus.valueOf(assetModel.getStatus()));
			if (Objects.nonNull(assetModel.getAddedDate())) {
				asset.setAddedDate(LocalDateTime.ofInstant(assetModel.getAddedDate(), ZoneOffset.UTC));
			}
			asset.setAddedBy(assetModel.getAddedBy());
			if (Objects.nonNull(assetModel.getModifiedDate())) {
				asset.setModifiedDate(LocalDateTime.ofInstant(assetModel.getModifiedDate(), ZoneOffset.UTC));
			}
			asset.setModifiedBy(assetModel.getModifiedBy());
			if (Objects.nonNull(assetModel.getApproveDate())) {
				asset.setApproveDate(LocalDateTime.ofInstant(assetModel.getApproveDate(), ZoneOffset.UTC));
			}
			asset.setApprovedBy(assetModel.getApprovedBy());
			if (Objects.nonNull(assetModel.getPublishDate())) {
				asset.setPublishDate(LocalDateTime.ofInstant(assetModel.getPublishDate(), ZoneOffset.UTC));
			}
			asset.setPublishedBy(assetModel.getPublishedBy());
			asset.setMetadata(assetModel.getMetadata());
		}
		return asset;
	}

	public static com.astra.hackathon.asset.client.Asset clientAsset(AssetByBucket assetByBucketModel) {
		com.astra.hackathon.asset.client.Asset asset = new com.astra.hackathon.asset.client.Asset();
		if (Objects.nonNull(assetByBucketModel)) {
			asset.setIdentifier(assetByBucketModel.getIdentifier().toString());
			asset.setName(assetByBucketModel.getName());
			asset.setBucket(assetByBucketModel.getBucket());
			asset.setType(AssetType.valueOf(assetByBucketModel.getType()));
			asset.setStatus(AssetStatus.valueOf(assetByBucketModel.getStatus()));
			if (Objects.nonNull(assetByBucketModel.getAddedDate())) {
				asset.setAddedDate(LocalDateTime.ofInstant(assetByBucketModel.getAddedDate(), ZoneOffset.UTC));
			}
			asset.setAddedBy(assetByBucketModel.getAddedBy());
			if (Objects.nonNull(assetByBucketModel.getModifiedDate())) {
				asset.setModifiedDate(LocalDateTime.ofInstant(assetByBucketModel.getModifiedDate(), ZoneOffset.UTC));
			}
			asset.setModifiedBy(assetByBucketModel.getModifiedBy());
			if (Objects.nonNull(assetByBucketModel.getApproveDate())) {
				asset.setApproveDate(LocalDateTime.ofInstant(assetByBucketModel.getApproveDate(), ZoneOffset.UTC));
			}
			asset.setApprovedBy(assetByBucketModel.getApprovedBy());
			if (Objects.nonNull(assetByBucketModel.getPublishDate())) {
				asset.setPublishDate(LocalDateTime.ofInstant(assetByBucketModel.getPublishDate(), ZoneOffset.UTC));
			}
			asset.setPublishedBy(assetByBucketModel.getPublishedBy());
			asset.setMetadata(assetByBucketModel.getMetadata());
		}
		return asset;
	}

	public static com.astra.hackathon.asset.client.Asset clientAsset(AssetByName assetByNameModel) {
		com.astra.hackathon.asset.client.Asset asset = new com.astra.hackathon.asset.client.Asset();
		if (Objects.nonNull(assetByNameModel)) {
			asset.setIdentifier(assetByNameModel.getIdentifier().toString());
			asset.setName(assetByNameModel.getName());
			asset.setBucket(assetByNameModel.getBucket());
			asset.setType(AssetType.valueOf(assetByNameModel.getType()));
			asset.setStatus(AssetStatus.valueOf(assetByNameModel.getStatus()));
			if (Objects.nonNull(assetByNameModel.getAddedDate())) {
				asset.setAddedDate(LocalDateTime.ofInstant(assetByNameModel.getAddedDate(), ZoneOffset.UTC));
			}
			asset.setAddedBy(assetByNameModel.getAddedBy());
			if (Objects.nonNull(assetByNameModel.getModifiedDate())) {
				asset.setModifiedDate(LocalDateTime.ofInstant(assetByNameModel.getModifiedDate(), ZoneOffset.UTC));
			}
			asset.setModifiedBy(assetByNameModel.getModifiedBy());
			if (Objects.nonNull(assetByNameModel.getApproveDate())) {
				asset.setApproveDate(LocalDateTime.ofInstant(assetByNameModel.getApproveDate(), ZoneOffset.UTC));
			}
			asset.setApprovedBy(assetByNameModel.getApprovedBy());
			if (Objects.nonNull(assetByNameModel.getPublishDate())) {
				asset.setPublishDate(LocalDateTime.ofInstant(assetByNameModel.getPublishDate(), ZoneOffset.UTC));
			}
			asset.setPublishedBy(assetByNameModel.getPublishedBy());
			asset.setMetadata(assetByNameModel.getMetadata());
		}
		return asset;
	}

	public static com.astra.hackathon.asset.client.Asset clientAsset(AssetByStatus assetByStatusModel) {
		com.astra.hackathon.asset.client.Asset asset = new com.astra.hackathon.asset.client.Asset();
		if (Objects.nonNull(assetByStatusModel)) {
			asset.setIdentifier(assetByStatusModel.getIdentifier().toString());
			asset.setName(assetByStatusModel.getName());
			asset.setBucket(assetByStatusModel.getBucket());
			asset.setType(AssetType.valueOf(assetByStatusModel.getType()));
			asset.setStatus(AssetStatus.valueOf(assetByStatusModel.getStatus()));
			if (Objects.nonNull(assetByStatusModel.getAddedDate())) {
				asset.setAddedDate(LocalDateTime.ofInstant(assetByStatusModel.getAddedDate(), ZoneOffset.UTC));
			}
			asset.setAddedBy(assetByStatusModel.getAddedBy());
			if (Objects.nonNull(assetByStatusModel.getModifiedDate())) {
				asset.setModifiedDate(LocalDateTime.ofInstant(assetByStatusModel.getModifiedDate(), ZoneOffset.UTC));
			}
			asset.setModifiedBy(assetByStatusModel.getModifiedBy());
			if (Objects.nonNull(assetByStatusModel.getApproveDate())) {
				asset.setApproveDate(LocalDateTime.ofInstant(assetByStatusModel.getApproveDate(), ZoneOffset.UTC));
			}
			asset.setApprovedBy(assetByStatusModel.getApprovedBy());
			if (Objects.nonNull(assetByStatusModel.getPublishDate())) {
				asset.setPublishDate(LocalDateTime.ofInstant(assetByStatusModel.getPublishDate(), ZoneOffset.UTC));
			}
			asset.setPublishedBy(assetByStatusModel.getPublishedBy());
			asset.setMetadata(assetByStatusModel.getMetadata());
		}
		return asset;
	}

	public static com.astra.hackathon.asset.client.Asset clientAsset(AssetByType assetByTypeModel) {
		com.astra.hackathon.asset.client.Asset asset = new com.astra.hackathon.asset.client.Asset();
		if (Objects.nonNull(assetByTypeModel)) {
			asset.setIdentifier(assetByTypeModel.getIdentifier().toString());
			asset.setName(assetByTypeModel.getName());
			asset.setBucket(assetByTypeModel.getBucket());
			asset.setType(AssetType.valueOf(assetByTypeModel.getType()));
			asset.setStatus(AssetStatus.valueOf(assetByTypeModel.getStatus()));
			if (Objects.nonNull(assetByTypeModel.getAddedDate())) {
				asset.setAddedDate(LocalDateTime.ofInstant(assetByTypeModel.getAddedDate(), ZoneOffset.UTC));
			}
			asset.setAddedBy(assetByTypeModel.getAddedBy());
			if (Objects.nonNull(assetByTypeModel.getModifiedDate())) {
				asset.setModifiedDate(LocalDateTime.ofInstant(assetByTypeModel.getModifiedDate(), ZoneOffset.UTC));
			}
			asset.setModifiedBy(assetByTypeModel.getModifiedBy());
			if (Objects.nonNull(assetByTypeModel.getApproveDate())) {
				asset.setApproveDate(LocalDateTime.ofInstant(assetByTypeModel.getApproveDate(), ZoneOffset.UTC));
			}
			asset.setApprovedBy(assetByTypeModel.getApprovedBy());
			if (Objects.nonNull(assetByTypeModel.getPublishDate())) {
				asset.setPublishDate(LocalDateTime.ofInstant(assetByTypeModel.getPublishDate(), ZoneOffset.UTC));
			}
			asset.setPublishedBy(assetByTypeModel.getPublishedBy());
			asset.setMetadata(assetByTypeModel.getMetadata());
		}
		return asset;
	}

	public static UserBucket clientUserBucket(UserBuckets bucket, UserAssetCounts bucketAssetCounts) {
		UserBucket userBucket = new UserBucket();
		userBucket.setName(bucket.getBucket());
		userBucket.setDescription(bucket.getDescription());
		userBucket.setActive(bucket.getActive());
		if (Objects.nonNull(bucket.getCreateDate())) {
			userBucket.setCreateDate(LocalDateTime.ofInstant(bucket.getCreateDate(), ZoneOffset.UTC));
		}
		if (Objects.nonNull(bucket.getLastUpdatedDate())) {
			userBucket.setLastUpdatedDate(LocalDateTime.ofInstant(bucket.getLastUpdatedDate(), ZoneOffset.UTC));
		}

		if (Objects.nonNull(bucketAssetCounts)) {
			userBucket.setAdded(bucketAssetCounts.getAdded());
			userBucket.setApproved(bucketAssetCounts.getApproved());
			userBucket.setPublished(bucketAssetCounts.getPublished());
			userBucket.setImages(bucketAssetCounts.getImages());
			userBucket.setVideos(bucketAssetCounts.getVideos());
			userBucket.setDocuments(bucketAssetCounts.getDocuments());
		}
		return userBucket;
	}

}
