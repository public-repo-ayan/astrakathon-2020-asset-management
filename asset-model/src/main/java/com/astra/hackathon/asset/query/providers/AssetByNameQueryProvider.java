package com.astra.hackathon.asset.query.providers;

import static com.astra.hackathon.asset.util.QueryKeywords.AND;
import static com.astra.hackathon.asset.util.QueryKeywords.CLOSE_PARENTHESIS;
import static com.astra.hackathon.asset.util.QueryKeywords.COLON;
import static com.astra.hackathon.asset.util.QueryKeywords.COMMA;
import static com.astra.hackathon.asset.util.QueryKeywords.DELETE_FROM;
import static com.astra.hackathon.asset.util.QueryKeywords.EQUAL;
import static com.astra.hackathon.asset.util.QueryKeywords.INSERT_INTO;
import static com.astra.hackathon.asset.util.QueryKeywords.OPEN_PARENTHESIS;
import static com.astra.hackathon.asset.util.QueryKeywords.SET;
import static com.astra.hackathon.asset.util.QueryKeywords.UPDATE;
import static com.astra.hackathon.asset.util.QueryKeywords.VALUES;
import static com.astra.hackathon.asset.util.QueryKeywords.WHERE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.astra.hackathon.asset.client.Asset;
import com.astra.hackathon.asset.query.QueryProvider;
import com.astra.hackathon.asset.util.ColumnNames;

public class AssetByNameQueryProvider implements QueryProvider {

	private static final Logger logger = LoggerFactory.getLogger(AssetByNameQueryProvider.class);

	private static final String TABLE_NAME = "asset_by_name";

	@Override
	public String insert(Asset asset) {
		StringBuilder queryString = new StringBuilder();
		queryString.append(INSERT_INTO);
		queryString.append(TABLE_NAME);
		queryString.append(OPEN_PARENTHESIS);
		queryString.append(ColumnNames.NAME);
		queryString.append(COMMA);
		queryString.append(ColumnNames.BUCKET);
		queryString.append(COMMA);
		queryString.append(ColumnNames.IDENTIFIER);
		queryString.append(COMMA);
		queryString.append(ColumnNames.TYPE);
		queryString.append(COMMA);
		queryString.append(ColumnNames.STATUS);
		queryString.append(COMMA);
		queryString.append(ColumnNames.ADDED_DATE);
		queryString.append(COMMA);
		queryString.append(ColumnNames.ADDED_BY);
		queryString.append(COMMA);
		queryString.append(ColumnNames.METADATA);
		queryString.append(CLOSE_PARENTHESIS);
		queryString.append(VALUES);
		queryString.append(OPEN_PARENTHESIS);
		queryString.append(COLON);
		queryString.append(ColumnNames.NAME);
		queryString.append(COMMA);
		queryString.append(COLON);
		queryString.append(ColumnNames.BUCKET);
		queryString.append(COMMA);
		queryString.append(COLON);
		queryString.append(ColumnNames.IDENTIFIER);
		queryString.append(COMMA);
		queryString.append(COLON);
		queryString.append(ColumnNames.TYPE);
		queryString.append(COMMA);
		queryString.append(COLON);
		queryString.append(ColumnNames.STATUS);
		queryString.append(COMMA);
		queryString.append(COLON);
		queryString.append(ColumnNames.ADDED_DATE);
		queryString.append(COMMA);
		queryString.append(COLON);
		queryString.append(ColumnNames.ADDED_BY);
		queryString.append(COMMA);
		queryString.append(COLON);
		queryString.append(ColumnNames.METADATA);
		queryString.append(CLOSE_PARENTHESIS);

		logger.info("insert query:{}", queryString);

		return queryString.toString();
	}

	@Override
	public String update(Asset asset) {
		StringBuilder queryString = new StringBuilder();
		queryString.append(UPDATE);
		queryString.append(TABLE_NAME);
		queryString.append(SET);
		queryString.append(ColumnNames.MODIFIED_DATE);
		queryString.append(EQUAL);
		queryString.append(COLON);
		queryString.append(ColumnNames.MODIFIED_DATE);
		queryString.append(COMMA);
		queryString.append(ColumnNames.MODIFIED_BY);
		queryString.append(EQUAL);
		queryString.append(COLON);
		queryString.append(ColumnNames.MODIFIED_BY);
		queryString.append(COMMA);
		queryString.append(ColumnNames.METADATA);
		queryString.append(EQUAL);
		queryString.append(COLON);
		queryString.append(ColumnNames.METADATA);
		queryString.append(WHERE);
		queryString.append(ColumnNames.NAME);
		queryString.append(EQUAL);
		queryString.append(COLON);
		queryString.append(ColumnNames.NAME);
		queryString.append(AND);
		queryString.append(ColumnNames.BUCKET);
		queryString.append(EQUAL);
		queryString.append(COLON);
		queryString.append(ColumnNames.BUCKET);

		logger.info("update query:{}", queryString);

		return queryString.toString();
	}

	@Override
	public String delete(Asset asset) {
		StringBuilder queryString = new StringBuilder();
		queryString.append(DELETE_FROM);
		queryString.append(TABLE_NAME);
		queryString.append(WHERE);
		queryString.append(ColumnNames.NAME);
		queryString.append(EQUAL);
		queryString.append(COLON);
		queryString.append(ColumnNames.NAME);
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
		queryString.append(ColumnNames.STATUS);
		queryString.append(EQUAL);
		queryString.append(COLON);
		queryString.append(ColumnNames.STATUS);
		queryString.append(COMMA);
		queryString.append(ColumnNames.APPROVE_DATE);
		queryString.append(EQUAL);
		queryString.append(COLON);
		queryString.append(ColumnNames.APPROVE_DATE);
		queryString.append(COMMA);
		queryString.append(ColumnNames.APPROVED_BY);
		queryString.append(EQUAL);
		queryString.append(COLON);
		queryString.append(ColumnNames.APPROVED_BY);
		queryString.append(WHERE);
		queryString.append(ColumnNames.NAME);
		queryString.append(EQUAL);
		queryString.append(COLON);
		queryString.append(ColumnNames.NAME);
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
		queryString.append(ColumnNames.STATUS);
		queryString.append(EQUAL);
		queryString.append(COLON);
		queryString.append(ColumnNames.STATUS);
		queryString.append(COMMA);
		queryString.append(ColumnNames.PUBLISH_DATE);
		queryString.append(EQUAL);
		queryString.append(COLON);
		queryString.append(ColumnNames.PUBLISH_DATE);
		queryString.append(COMMA);
		queryString.append(ColumnNames.PUBLISHED_BY);
		queryString.append(EQUAL);
		queryString.append(COLON);
		queryString.append(ColumnNames.PUBLISHED_BY);
		queryString.append(WHERE);
		queryString.append(ColumnNames.NAME);
		queryString.append(EQUAL);
		queryString.append(COLON);
		queryString.append(ColumnNames.NAME);
		queryString.append(AND);
		queryString.append(ColumnNames.BUCKET);
		queryString.append(EQUAL);
		queryString.append(COLON);
		queryString.append(ColumnNames.BUCKET);

		logger.info("publish query:{}", queryString);

		return queryString.toString();
	}
}
