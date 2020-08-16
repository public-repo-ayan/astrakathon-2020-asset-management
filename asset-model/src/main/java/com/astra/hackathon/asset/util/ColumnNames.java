package com.astra.hackathon.asset.util;

import com.astra.hackathon.asset.client.AssetStatus;
import com.astra.hackathon.asset.client.AssetType;

public class ColumnNames {
	public static String IDENTIFIER = "identifier";
	public static String NAME = "name";
	public static String BUCKET = "bucket";
	public static String TYPE = "type";
	public static String STATUS = "status";
	public static String ADDED_DATE = "added_date";
	public static String ADDED_BY = "added_by";
	public static String MODIFIED_DATE = "modified_date";
	public static String MODIFIED_BY = "modified_by";
	public static String APPROVE_DATE = "approve_date";
	public static String APPROVED_BY = "approved_by";
	public static String PUBLISH_DATE = "publish_date";
	public static String PUBLISHED_BY = "published_by";
	public static String METADATA = "metadata";
	public static String USER_ID = "user_id";
	public static String ADDED = "added";
	public static String APPROVED = "approved";
	public static String PUBLISHED = "published";
	public static String IMAGES = "images";
	public static String VIDEOS = "videos";
	public static String DOCUMENTS = "documents";
	public static String LAST_UPDATED_DATE = "last_updated_date";

	public static String typeColumnName(AssetType assetType) {
		switch (assetType) {
		case Image:
			return IMAGES;
		case Video:
			return VIDEOS;
		case Document:
			return DOCUMENTS;
		default:
			return "undefined";
		}
	}

	public static String statusColumnName(AssetStatus assetStatus) {
		switch (assetStatus) {
		case Added:
			return ADDED;
		case Approved:
			return APPROVED;
		case Published:
			return PUBLISHED;
		default:
			return "undefined";
		}
	}
}
