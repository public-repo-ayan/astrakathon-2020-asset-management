package com.astra.hackathon.asset.query;

import com.astra.hackathon.asset.client.Asset;

public interface QueryProvider {
	public String insert(Asset asset);

	public String update(Asset asset);

	public String delete(Asset asset);

	public String approve(Asset asset);

	public String publish(Asset asset);
}
