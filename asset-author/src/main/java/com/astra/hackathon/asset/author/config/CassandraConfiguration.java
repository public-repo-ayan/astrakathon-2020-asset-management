package com.astra.hackathon.asset.author.config;

import java.net.InetSocketAddress;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.type.codec.TypeCodecs;

@Configuration
public class CassandraConfiguration {

	@Value("${cassandra.contact-points}")
	private String contactPoints;

	@Value("${cassandra.keyspace}")
	private String keyspace;

	@Value("${cassandra.port}")
	private int port;

	@Value("${cassandra.datacenter}")
	private String datacenter;

	@Bean
	public CqlSession session() {
		return CqlSession.builder().addContactPoint(new InetSocketAddress(contactPoints, port)).withKeyspace(keyspace)
				.withLocalDatacenter(datacenter).addTypeCodecs(TypeCodecs.ZONED_TIMESTAMP_UTC).build();
	}
}
