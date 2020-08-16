package com.astra.hackathon.user.client;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class UserBucket {
	private String name;
	private String description;
	private Boolean active;
	private LocalDateTime createDate;
	private LocalDateTime lastUpdatedDate;

	// counts
	private Long added = 0L;
	private Long approved = 0L;
	private Long published = 0L;
	private Long images = 0L;
	private Long videos = 0L;
	private Long documents = 0L;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
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
		return "UserBucket [name=" + name + ", description=" + description + ", active=" + active + ", createDate="
				+ createDate + ", lastUpdatedDate=" + lastUpdatedDate + ", added=" + added + ", approved=" + approved
				+ ", published=" + published + ", images=" + images + ", videos=" + videos + ", documents=" + documents
				+ "]";
	}

}
