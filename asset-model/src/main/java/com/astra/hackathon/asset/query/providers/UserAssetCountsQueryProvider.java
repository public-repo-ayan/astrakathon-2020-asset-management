package com.astra.hackathon.asset.query.providers;

import static com.astra.hackathon.asset.util.ColumnNames.ADDED;
import static com.astra.hackathon.asset.util.ColumnNames.APPROVED;
import static com.astra.hackathon.asset.util.ColumnNames.PUBLISHED;
import static com.astra.hackathon.asset.util.ColumnNames.statusColumnName;
import static com.astra.hackathon.asset.util.ColumnNames.typeColumnName;
import static com.astra.hackathon.asset.util.QueryKeywords.AND;
import static com.astra.hackathon.asset.util.QueryKeywords.COLON;
import static com.astra.hackathon.asset.util.QueryKeywords.COMMA;
import static com.astra.hackathon.asset.util.QueryKeywords.EQUAL;
import static com.astra.hackathon.asset.util.QueryKeywords.MINUS;
import static com.astra.hackathon.asset.util.QueryKeywords.PLUS;
import static com.astra.hackathon.asset.util.QueryKeywords.SET;
import static com.astra.hackathon.asset.util.QueryKeywords.UPDATE;
import static com.astra.hackathon.asset.util.QueryKeywords.WHERE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.astra.hackathon.asset.client.Asset;
import com.astra.hackathon.asset.exceptions.NotSupportedException;
import com.astra.hackathon.asset.query.QueryProvider;
import com.astra.hackathon.asset.util.ColumnNames;

public class UserAssetCountsQueryProvider implements QueryProvider {

	private static final Logger logger = LoggerFactory.getLogger(UserAssetCountsQueryProvider.class);

	private static final String TABLE_NAME = "user_asset_counts";

	@Override
	public String insert(Asset asset) {
		StringBuilder queryString = new StringBuilder();
		queryString.append(UPDATE);
		queryString.append(TABLE_NAME);
		queryString.append(SET);
		queryString.append(ADDED);
		queryString.append(EQUAL);
		queryString.append(ADDED);
		queryString.append(PLUS);
		queryString.append(1);
		queryString.append(COMMA);
		queryString.append(typeColumnName(asset.getType()));
		queryString.append(EQUAL);
		queryString.append(typeColumnName(asset.getType()));
		queryString.append(PLUS);
		queryString.append(1);
		queryString.append(WHERE);
		queryString.append(ColumnNames.USER_ID);
		queryString.append(EQUAL);
		queryString.append(COLON);
		queryString.append(ColumnNames.USER_ID);
		queryString.append(AND);
		queryString.append(ColumnNames.BUCKET);
		queryString.append(EQUAL);
		queryString.append(COLON);
		queryString.append(ColumnNames.BUCKET);

		logger.info("insert query:{}", queryString);

		return queryString.toString();
	}

	@Override
	public String update(Asset asset) {
		throw new NotSupportedException("counter column not present");
	}

	@Override
	public String delete(Asset asset) {
		StringBuilder queryString = new StringBuilder();
		queryString.append(UPDATE);
		queryString.append(TABLE_NAME);
		queryString.append(SET);
		queryString.append(statusColumnName(asset.getStatus()));
		queryString.append(EQUAL);
		queryString.append(statusColumnName(asset.getStatus()));
		queryString.append(MINUS);
		queryString.append(1);
		queryString.append(COMMA);
		queryString.append(typeColumnName(asset.getType()));
		queryString.append(EQUAL);
		queryString.append(typeColumnName(asset.getType()));
		queryString.append(MINUS);
		queryString.append(1);
		queryString.append(WHERE);
		queryString.append(ColumnNames.USER_ID);
		queryString.append(EQUAL);
		queryString.append(COLON);
		queryString.append(ColumnNames.USER_ID);
		queryString.append(AND);
		queryString.append(ColumnNames.BUCKET);
		queryString.append(EQUAL);
		queryString.append(COLON);
		queryString.append(ColumnNames.BUCKET);

		logger.info("delete query:{}", queryString);

		return queryString.toString();
	}

	@Override
	public String approve(Asset asset) {
		StringBuilder queryString = new StringBuilder();
		queryString.append(UPDATE);
		queryString.append(TABLE_NAME);
		queryString.append(SET);
		queryString.append(APPROVED);
		queryString.append(EQUAL);
		queryString.append(APPROVED);
		queryString.append(PLUS);
		queryString.append(1);
		queryString.append(COMMA);
		queryString.append(ADDED);
		queryString.append(EQUAL);
		queryString.append(ADDED);
		queryString.append(MINUS);
		queryString.append(1);
		queryString.append(WHERE);
		queryString.append(ColumnNames.USER_ID);
		queryString.append(EQUAL);
		queryString.append(COLON);
		queryString.append(ColumnNames.USER_ID);
		queryString.append(AND);
		queryString.append(ColumnNames.BUCKET);
		queryString.append(EQUAL);
		queryString.append(COLON);
		queryString.append(ColumnNames.BUCKET);

		logger.info("approve query:{}", queryString);

		return queryString.toString();
	}

	@Override
	public String publish(Asset asset) {
		StringBuilder queryString = new StringBuilder();
		queryString.append(UPDATE);
		queryString.append(TABLE_NAME);
		queryString.append(SET);
		queryString.append(PUBLISHED);
		queryString.append(EQUAL);
		queryString.append(PUBLISHED);
		queryString.append(PLUS);
		queryString.append(1);
		queryString.append(COMMA);
		queryString.append(APPROVED);
		queryString.append(EQUAL);
		queryString.append(APPROVED);
		queryString.append(MINUS);
		queryString.append(1);
		queryString.append(WHERE);
		queryString.append(ColumnNames.USER_ID);
		queryString.append(EQUAL);
		queryString.append(COLON);
		queryString.append(ColumnNames.USER_ID);
		queryString.append(AND);
		queryString.append(ColumnNames.BUCKET);
		queryString.append(EQUAL);
		queryString.append(COLON);
		queryString.append(ColumnNames.BUCKET);

		logger.info("publish query:{}", queryString);

		return queryString.toString();
	}
}
