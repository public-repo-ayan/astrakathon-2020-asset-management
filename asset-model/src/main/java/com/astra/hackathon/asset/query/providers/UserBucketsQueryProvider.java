package com.astra.hackathon.asset.query.providers;

import static com.astra.hackathon.asset.util.ColumnNames.LAST_UPDATED_DATE;
import static com.astra.hackathon.asset.util.QueryKeywords.AND;
import static com.astra.hackathon.asset.util.QueryKeywords.COLON;
import static com.astra.hackathon.asset.util.QueryKeywords.EQUAL;
import static com.astra.hackathon.asset.util.QueryKeywords.SET;
import static com.astra.hackathon.asset.util.QueryKeywords.UPDATE;
import static com.astra.hackathon.asset.util.QueryKeywords.WHERE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.astra.hackathon.asset.client.Asset;
import com.astra.hackathon.asset.exceptions.NotSupportedException;
import com.astra.hackathon.asset.query.QueryProvider;
import com.astra.hackathon.asset.util.ColumnNames;

public class UserBucketsQueryProvider implements QueryProvider {

	private static final Logger logger = LoggerFactory.getLogger(UserBucketsQueryProvider.class);

	private static final String TABLE_NAME = "user_buckets";

	@Override
	public String insert(Asset asset) {
		throw new NotSupportedException("counter column not present");
	}

	@Override
	public String update(Asset asset) {
		StringBuilder queryString = new StringBuilder();
		queryString.append(UPDATE);
		queryString.append(TABLE_NAME);
		queryString.append(SET);
		queryString.append(LAST_UPDATED_DATE);
		queryString.append(EQUAL);
		queryString.append(COLON);
		queryString.append(LAST_UPDATED_DATE);
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

		logger.info("update query:{}", queryString);

		return queryString.toString();
	}

	@Override
	public String delete(Asset asset) {
		throw new NotSupportedException("counter column not present");
	}

	@Override
	public String approve(Asset asset) {
		throw new NotSupportedException("counter column not present");
	}

	@Override
	public String publish(Asset asset) {
		throw new NotSupportedException("counter column not present");
	}
}
