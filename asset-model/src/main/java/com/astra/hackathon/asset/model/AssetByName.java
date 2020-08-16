package com.astra.hackathon.asset.model;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.astra.hackathon.asset.client.Asset;
import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.datastax.oss.driver.api.mapper.annotations.ClusteringColumn;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;

@Entity
public class AssetByName {

	@PartitionKey
	private String name;

	@ClusteringColumn
	private String bucket;

	private UUID identifier;

	// Image, Video, Document
	private String type;

	// added, approved, published
	private String status;

	private Instant addedDate;
	private String addedBy;

	private Instant modifiedDate;
	private String modifiedBy;

	private Instant approveDate;
	private String approvedBy;

	private Instant publishDate;
	private String publishedBy;

	private Map<String, String> metadata;

	public AssetByName() {
	}

	public AssetByName(UUID identifier, String name, String bucket, String type, String status,
			String addedBy) {
		this.identifier = identifier;
		this.name = name;
		this.bucket = bucket;
		this.type = type;
		this.status = status;
		this.addedBy = addedBy;
	}

	public AssetByName(Asset asset) {
		this.identifier = Uuids.random();

		if (Objects.nonNull(asset)) {
			this.name = asset.getName();
			this.bucket = asset.getBucket();
			this.type = asset.getType().name();
			this.status = asset.getStatus().name();
			this.addedBy = asset.getAddedBy();
			this.addedDate = Instant.now();
		}
	}

	public UUID getIdentifier() {
		return identifier;
	}

	public void setIdentifier(UUID identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Instant getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Instant addedDate) {
		this.addedDate = addedDate;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public Instant getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Instant modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Instant getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Instant approveDate) {
		this.approveDate = approveDate;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Instant getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Instant publishDate) {
		this.publishDate = publishDate;
	}

	public String getPublishedBy() {
		return publishedBy;
	}

	public void setPublishedBy(String publishedBy) {
		this.publishedBy = publishedBy;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String, String> metadata) {
		this.metadata = metadata;
	}

	@Override
	public String toString() {
		return "Asset [identifier=" + identifier + ", name=" + name + ", bucket=" + bucket + ", type=" + type
				+ ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssetByName other = (AssetByName) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;
	}
}
