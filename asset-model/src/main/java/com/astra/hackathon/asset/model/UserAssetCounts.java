package com.astra.hackathon.asset.model;

import com.datastax.oss.driver.api.mapper.annotations.ClusteringColumn;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;

@Entity
public class UserAssetCounts {
	@PartitionKey
	private String userId;

	@ClusteringColumn
	private String bucket;

	private Long added;
	private Long approved;
	private Long published;

	private Long images;
	private Long videos;
	private Long documents;

	public UserAssetCounts() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public Long getAdded() {
		return added;
	}

	public void setAdded(Long added) {
		this.added = added;
	}

	public Long getApproved() {
		return approved;
	}

	public void setApproved(Long approved) {
		this.approved = approved;
	}

	public Long getPublished() {
		return published;
	}

	public void setPublished(Long published) {
		this.published = published;
	}

	public Long getImages() {
		return images;
	}

	public void setImages(Long images) {
		this.images = images;
	}

	public Long getVideos() {
		return videos;
	}

	public void setVideos(Long videos) {
		this.videos = videos;
	}

	public Long getDocuments() {
		return documents;
	}

	public void setDocuments(Long documents) {
		this.documents = documents;
	}

	@Override
	public String toString() {
		return "UserAssetCounts [userId=" + userId + ", bucket=" + bucket + ", added=" + added + ", approved="
				+ approved + ", published=" + published + ", images=" + images + ", videos=" + videos + ", documents="
				+ documents + "]";
	}

}
