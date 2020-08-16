# ✨ Asset Store Management ✨

Bucket based asset storing and authoring by users. User needs to create their own bucket, then use those buckets to keep the assets, approve and publish those for external use. Users will be able to see the bucket based asset counts, by type of asset and status of the asset.

Published assets can be used for different websites, for published asset delivery completely different external interfaces/microservices will be used. This concept is to build the building block of managing those published assets and their states from Added to Approved, then Published.

As of now, supported asset types are - Image, Video, Document and for each type of asset, possible metadata could be 

- Image
  - format e.g. png, jpeg, svg etc.
  - width e.g. 500px
  - height e.g. 300px
  - unit e.g. px, inch etc.
- Video
  - format e.g. mp4, mpeg etc.
  - frameRate e.g. 30
  - duration e.g. 60 mins
- Document
  - format e.g. doc, ppt, keynote, page etc. 

## Table of Contents

| Section | Content
|---|---|
| Features | [Features](#features) |
| Scope of Improvement | [Scope of Improvement](#scope-of-improvement) |
| Out of scope | [Not Included](#not-included) |
| Author | [Author](#concept-by) |
| Prerequistics | [Prerequistics](#Prerequistics) |
| Setup | [Setup](#setup) |
| Setup/Using Archive File | [Cassandra Setup Using Zip File](#using-archive-file) |
| Setup/Using Docker(Recommended) | [Cassandra Setup Using Docker](#use-docker-to-configure-cassandra) |
| Configuration | [Configuration](#configuration) |
| Run | [Run](#run) |
| Run Using IDE | [Run Using Eclipse](#using-ide-eclipse) |
| Run Using Scripts | [Run Using Script](#using-scripts) |
| API Hands On | [Playing with APIs](#lets-do-deep-dive) |
| Code Walkthrough | [Understanding APIs](#code-walkthrough) |
| Swagger Documentation | [Swagger URLs](#swagger) |
| Conclusion | [Conclusion](#ending-note) |

## Features

 * Spring boot application 
 * Spring based microservices
 * Integrated with Swagger
 * Cassandra enabled (4.7.2)
   * Using Datastax driver
   * Datastax driver mapper functionality
   * Batch Statements based commits for better consistency
 * One-click script to run all services
 * Quick & easy Cassandra Setup
 
## Scope of Improvement

 * UX design of Asset store 
 * Version controlled publishing of assets and release
 * Principal based User Authentication
 * TLS enabling
 * Batch support
   * Saving assets
   * Updating assets
   * Delete assets
   * Approving assets
   * Publishing assets
 * Multi-thread support  
 * Test data generator using Python/Bash
 * Integration test
 
## Not Included

Application handles metadata of assets, not on actual binary assets.
 
## Concept by

  * Ayan Chakraborty (ayanit84@gmail.com)
    * GitHub https://github.com/ayanit84
    * Linkedin https://www.linkedin.com/in/ayan-chakraborty-33181516/

## Prerequistics 

- Java 1.8 or later
- Maven 3.5 or later
- Bash Terminal (only for running using shell scripts)
- Docker (optional, only for easy setup of Cassandra and tables)

## Setup

Schema is defined here https://github.com/spring-boot-fun/asset-management/blob/develop/asset-model/src/main/cql/asset.cql, please apply before proceeding.

```
USE ks_asset;

CREATE TABLE ks_asset.asset (
  identifier uuid PRIMARY KEY,
  name text,
  bucket text,
  type text,
  status text,
  added_date timestamp,
  added_by text,
  modified_date timestamp,
  modified_by text,
  approve_date timestamp,
  approved_by text,
  publish_date timestamp,
  published_by text,
  metadata map<text, text>
);

CREATE TABLE ks_asset.asset_by_bucket (
  bucket text,
  name text,
  identifier uuid,
  type text,
  status text,
  added_date timestamp,
  added_by text,
  modified_date timestamp,
  modified_by text,
  approve_date timestamp,
  approved_by text,
  publish_date timestamp,
  published_by text,
  metadata map<text, text>,
  PRIMARY KEY (bucket, name)
) WITH CLUSTERING ORDER BY (name ASC);

CREATE TABLE ks_asset.asset_by_name (
  name text,
  bucket text,
  identifier uuid,
  type text,
  status text,
  added_date timestamp,
  added_by text,
  modified_date timestamp,
  modified_by text,
  approve_date timestamp,
  approved_by text,
  publish_date timestamp,
  published_by text,
  metadata map<text, text>,
  PRIMARY KEY (name, bucket)
) WITH CLUSTERING ORDER BY (bucket ASC);

CREATE TABLE ks_asset.asset_by_type (
  type text,
  bucket text,
  name text,
  identifier uuid,
  status text,
  added_date timestamp,
  added_by text,
  modified_date timestamp,
  modified_by text,
  approve_date timestamp,
  approved_by text,
  publish_date timestamp,
  published_by text,
  metadata map<text, text>,
  PRIMARY KEY (type, bucket, name)
) WITH CLUSTERING ORDER BY (bucket ASC, name ASC);

CREATE TABLE ks_asset.asset_by_status (
  status text,
  bucket text,
  name text,
  identifier uuid,
  type text,
  added_date timestamp,
  added_by text,
  modified_date timestamp,
  modified_by text,
  approve_date timestamp,
  approved_by text,
  publish_date timestamp,
  published_by text,
  metadata map<text, text>,
  PRIMARY KEY (status, bucket, name)
) WITH CLUSTERING ORDER BY (bucket ASC, name ASC);

CREATE TABLE ks_asset.user_buckets (
  user_id text,
  bucket text,
  description text,
  create_date timestamp,
  last_updated_date timestamp,
  active boolean,
  PRIMARY KEY (user_id, bucket)
) WITH CLUSTERING ORDER BY (bucket ASC);

CREATE TABLE ks_asset.user_asset_counts (
  user_id text,
  bucket text,
  added counter,
  approved counter,
  published counter,
  images counter,
  videos counter,
  documents counter,
  PRIMARY KEY (user_id, bucket)
) WITH CLUSTERING ORDER BY (bucket ASC);
```
### Using Archive file

1. Download the zip/tar.gz from here - https://cassandra.apache.org/download/
2. Extract it and go to bin/ folder and use the below to start Cassandra
```
./cassandra -f (use -f for running as foreground process)
```

### Use Docker to Configure Cassandra

Please run the below commands by going inside the asset-management main folder.

1. It run a local cassandra container with name "localCassandra" and do the port forwarding for access outside container
```
docker run --name localCassandra -d -p 9042:9042 cassandra:3.11
```
2. This will copy the schema definition file into newly created Cassandra container
```
docker cp asset-model/src/main/cql/asset.cql localCassandra:/asset.cql 
```
3. It will execute the schema file using *cqlsh* to create the keyspace and tables
```
Docker exec -it localCassandra cqlsh -f /asset.cql 
```

Now *ks_asset* keyspace is ready for use by application.

## Configuration

Each runnable module has below database configuration for connecting to database in `application.yml`

```
cassandra:
  contact-points: 127.0.0.1
  keyspace: ks_asset
  port: 9042
  datacenter: datacenter1
```

For simplicity, it does not have any username/password.

## Run

### Using IDE (Eclipse)
*This is applicable only for Eclipse*

Total 3 runnable microservice-enabled modules are present

1. user-author (for doing any CRUD operations on assets including approve, publish), port: `8081`
2. user-search (for searching assets based on different criterias), port: `8082`
3. user-provision (for user, bucket creation and bbucket based asset count APIs), port: `8083`

Eclipse Launch Configurations are under `external/run` 

1. [asset-author](https://github.com/spring-boot-fun/asset-management/raw/master/external/run/asset-author.launch)
2. [asset-search](https://github.com/spring-boot-fun/asset-management/raw/master/external/run/asset-search.launch)
3. [user-provision](https://github.com/spring-boot-fun/asset-management/raw/master/external/run/user-provision.launch)

Once code is imported in Eclipse, Right click on each launch configuration and choose `Run -> asset-author` or `Run -> asset-search` or `Run -> user-provision`.

### Using Scripts

Individual run scripts are present for three services under `run` folder
1. run-author-service.sh
2. run-search-service.sh
3. run-user-service.sh

One-click script is also present to run all 3 process together.
1. run-all.sh

Additionally it will print the processIds for 3 services which can be used to stop/kill (kill -9 <processId>) later when done.

```
sh run/run-all.sh
starting 3 processes of asset management
starting author service
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
author service started on port 8081.
starting search service
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
search service started on port 8082.
starting user service
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
user service started on port 8083.
########################################
PID of Author Service:1791
PID of Search Service:1819
PID of User Service:1852
########################################
Save the PIDs for killing those later.
```

## Let's do deep dive
First we will create bucket for specific user.

### User based bucket creation

Request: GET
http://localhost:8083/user?userId=demouser&bucket=demo-bucket1&description=demo
1. userId = Id of specific user [Mandatory]
2. bucket = name of the bucket [Mandatory]
3. description = description of the bucket [Optional]

Response:
`Successfully saved the user with id:demouser`

### Let's see the user

Request: GET
http://localhost:8083/user/demouser

1. demouser = Id of specific user [Mandatory]

Response:
```
{
    "userId": "demouser",
    "buckets": [
        {
            "name": "demo-bucket1",
            "description": "demo",
            "active": true,
            "createDate": "2020-08-07T18:16:55.53",
            "lastUpdatedDate": "2020-08-07T18:16:55.53",
            "added": 0,
            "approved": 0,
            "published": 0,
            "images": 0,
            "videos": 0,
            "documents": 0
        }
    ]
}
```
### Let's store an asset under the newly created bucket

Request: POST
http://localhost:8081/asset

```
{
    "name": "demo-asset1",
    "bucket": "demo-bucket1",
    "type": "Image",
    "status": "Added",
    "addedBy": "demouser",
    "metadata": {
        "format": "png",
        "width": "300",
        "height": "150",
        "unit": "px"
    }
}
```

Response:
`Successfully saved the asset with identifier:0f729074-9b8c-4a67-bd60-de712c124342`

### Newly created asset can be fetched

Request: GET
http://localhost:8082/assets/0f729074-9b8c-4a67-bd60-de712c124342

1. 0f729074-9b8c-4a67-bd60-de712c124342 = Id of specific asset [Mandatory]

Response:
```
{
    "identifier": "0f729074-9b8c-4a67-bd60-de712c124342",
    "name": "demo-asset1",
    "bucket": "demo-bucket1",
    "type": "Image",
    "status": "Added",
    "addedDate": "2020-08-07T19:00:45.33",
    "addedBy": "demouser",
    "metadata": {
        "format": "png",
        "height": "150",
        "unit": "px",
        "width": "300"
    }
}
```

### Alternately below API can be invoked to see all assets currently present in system

Request: GET
http://localhost:8082/assets/

Response:

```
[
    {
        "identifier": "0f729074-9b8c-4a67-bd60-de712c124342",
        "name": "demo-asset1",
        "bucket": "demo-bucket1",
        "type": "Image",
        "status": "Added",
        "addedDate": "2020-08-07T19:00:45.33",
        "addedBy": "demouser",
        "metadata": {
            "format": "png",
            "height": "150",
            "unit": "px",
            "width": "300"
        }
    }
]
```

### Different ways to get assets

**By Bucket**
Request: GET
http://localhost:8082/assets/buckets/demo-bucket1/

Response:
```
[
    {
        "identifier": "0f729074-9b8c-4a67-bd60-de712c124342",
        "name": "demo-asset1",
        "bucket": "demo-bucket1",
        "type": "Image",
        "status": "Added",
        "addedDate": "2020-08-07T19:00:45.33",
        "addedBy": "demouser",
        "metadata": {
            "format": "png",
            "height": "150",
            "unit": "px",
            "width": "300"
        }
    }
]
```

**By Name**
Request: GET
http://localhost:8082/assets/demo-asset1/buckets/

Response:
```
[
    {
        "identifier": "0f729074-9b8c-4a67-bd60-de712c124342",
        "name": "demo-asset1",
        "bucket": "demo-bucket1",
        "type": "Image",
        "status": "Added",
        "addedDate": "2020-08-07T19:00:45.33",
        "addedBy": "demouser",
        "metadata": {
            "format": "png",
            "height": "150",
            "unit": "px",
            "width": "300"
        }
    }
]
```

**By Type**
Request: GET
http://localhost:8082/assets/type/Image

Response:
```
[
    {
        "identifier": "0f729074-9b8c-4a67-bd60-de712c124342",
        "name": "demo-asset1",
        "bucket": "demo-bucket1",
        "type": "Image",
        "status": "Added",
        "addedDate": "2020-08-07T19:00:45.33",
        "addedBy": "demouser",
        "metadata": {
            "format": "png",
            "height": "150",
            "unit": "px",
            "width": "300"
        }
    }
]
```

**By Status**
Request: GET
http://localhost:8082/assets/status/Added

Response:
```
[
    {
        "identifier": "0f729074-9b8c-4a67-bd60-de712c124342",
        "name": "demo-asset1",
        "bucket": "demo-bucket1",
        "type": "Image",
        "status": "Added",
        "addedDate": "2020-08-07T19:00:45.33",
        "addedBy": "demouser",
        "metadata": {
            "format": "png",
            "height": "150",
            "unit": "px",
            "width": "300"
        }
    }
]
```
### Let's add couple of more assets of type Video & Document under same bucket: demo-bucket1

**Video**
```
{
    "name": "demo-asset2",
    "bucket": "demo-bucket1",
    "type": "Video",
    "status": "Added",
    "addedBy": "demouser",
    "metadata": {
        "format": "mp4",
        "duration": "60mins",
        "frameRate": "30"
    }
}
```
```
{
    "name": "demo-asset3",
    "bucket": "demo-bucket1",
    "type": "Video",
    "status": "Added",
    "addedBy": "demouser",
    "metadata": {
        "format": "mp4",
        "duration": "160mins",
        "frameRate": "30"
    }
}
```

**Document**
```
{
    "name": "demo-asset4",
    "bucket": "demo-bucket1",
    "type": "Document",
    "status": "Added",
    "addedBy": "demouser",
    "metadata": {
        "format": "doc"
    }
}
```
```
{
    "name": "demo-asset5",
    "bucket": "demo-bucket1",
    "type": "Document",
    "status": "Added",
    "addedBy": "demouser",
    "metadata": {
        "format": "doc"
    }
}
```

### Let's invoke the bucket based asset count api to see how many type of assets are present for specific user

Request: GET
http://localhost:8083/users/demouser

Response:
```
{
    "userId": "demouser",
    "buckets": [
        {
            "name": "demo-bucket1",
            "description": "demo",
            "active": true,
            "createDate": "2020-08-07T18:16:55.53",
            "lastUpdatedDate": "2020-08-08T05:55:50.813",
            "added": 6,
            "images": 2,
            "videos": 2,
            "documents": 2
        }
    ]
}
```

- Total 6 assets of Added status
- 2 image type of asset
- 2 video type of asset
- 2 document type of asset

### Publishing flow
Once assets are added, user can approve those. For simplicity, system is having only one role - the same user who has added the asset, will approve the asset and publish too.

**Asset approval**
Request: GET
http://localhost:8081/asset/approve/d453e2f5-5907-46df-9607-de9a61982138/demouser

Response:
`Successfully approved the asset with identifier:d453e2f5-5907-46df-9607-de9a61982138`

**Asset publish**
Request: GET
http://localhost:8081/asset/publish/d453e2f5-5907-46df-9607-de9a61982138/demouser

Response:
`Successfully approved the asset with identifier:d453e2f5-5907-46df-9607-de9a61982138`

After couple of approve & publish, let's check the count per bucket for specific user
**Asset Count**
Request: GET
http://localhost:8083/users/demouser

Response:
```
{
    "userId": "demouser",
    "buckets": [
        {
            "name": "demo-bucket1",
            "description": "demo",
            "active": true,
            "createDate": "2020-08-07T18:16:55.53",
            "lastUpdatedDate": "2020-08-08T05:55:50.813",
            "added": 3,
            "approved": 2,
            "published": 1,
            "images": 2,
            "videos": 2,
            "documents": 2
        }
    ]
}
```
## Code Walkthrough

The project is Maven based and it has 5 sub-modules

1. asset-author [REST handlers related asset save/edit/delete/approve/publish]
2. asset-search [REST handlers related asset searching based on different criterias]
3. asset-model [Classes related to database integration including Entity, Dao, Mapper etc.]
4. asset-client [JSON equivalent classes for third-party REST service integration using RESTTemplate or HTTPClient]
5. user-provision [REST handlers related to user/bucket creation, bucket dashboard API etc.]

### Models

Concept appplication using total 7 tables under keyspace *ks_asset*. 
1. asset
2. asset_by_bucket
3. asset_by_name
4. asset_by_type
5. asset_by_status
6. user_buckets
7. user_asset_counts

Integration with Cassandra is fully done using datastax core driver and mapper library. Driver version `4.7.2`. Cassandra is initialized using

```
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
```

And all entity classes are defined under package *com.astra.hackathon.asset.model*

```
ls -l asset-model/src/main/java/com/astra/hackathon/asset/model 
total 88
-rw-r--r--  1 ayanchakraborty  staff  4081 Aug  9 23:35 Asset.java
-rw-r--r--  1 ayanchakraborty  staff  4237 Aug  9 23:35 AssetByBucket.java
-rw-r--r--  1 ayanchakraborty  staff  4225 Aug  9 23:35 AssetByName.java
-rw-r--r--  1 ayanchakraborty  staff  4278 Aug  9 23:35 AssetByStatus.java
-rw-r--r--  1 ayanchakraborty  staff  4263 Aug  9 23:35 AssetByType.java
-rw-r--r--  1 ayanchakraborty  staff  1783 Aug  9 23:35 UserAssetCounts.java
-rw-r--r--  1 ayanchakraborty  staff  2343 Aug  9 23:35 UserBuckets.java
```

And respective mapper and dao classes are configured using

```
<dependency>
			<groupId>com.datastax.oss</groupId>
			<artifactId>java-driver-mapper-runtime</artifactId>
			<version>${driver.version}</version>
</dependency>
```

```
<plugin>
		<artifactId>maven-compiler-plugin</artifactId>
		<configuration>
				<source>1.8</source> <!-- (or higher) -->
				<target>1.8</target> <!-- (or higher) -->
				<annotationProcessorPaths>
						<path>
							<groupId>com.datastax.oss</groupId>
							<artifactId>java-driver-mapper-processor</artifactId>
							<version>${driver.version}</version>
						</path>
				</annotationProcessorPaths>
		</configuration>
</plugin>
```      

And using below plugin to add the generated MapperBuilder classes in classpath for compilation

```
<plugin>
		<groupId>org.codehaus.mojo</groupId>
		<artifactId>build-helper-maven-plugin</artifactId>
		<executions>
				<execution>
					<id>add-source</id>
					<phase>generate-sources</phase>
					<goals>
							<goal>add-source</goal>
					</goals>
					<configuration>
							<sources>
								<source>${project.build.directory}/generated-sources/annotations</source>
							</sources>
					</configuration>
			  </execution>
		</executions>
</plugin>
```      

Sample Mapper & Dao class

```
@Dao
public interface AssetDao {
	
	@Select
	public PagingIterable<Asset> findAll();

	@Select
	public Asset findByIdentifier(UUID assetIdentifier);
	
	@Insert
	public void save(Asset asset);
}
```

```
@Mapper
public interface AssetMapper {

	@DaoFactory
	public AssetDao assetDao();
}
```

As User authoring flow deals with updating multiple tables for supporting different criteria based search, *BatchStatement* is being used to update multiple table duinge any CRUD operations. For each table, *PreparedStatement* is being created with respective query and bind values. For separate CRUD operations, need separate queries like *INSERT INTO*, *UPDATE*, *DELETE* etc. Below interface is defined for each type of query

```
public interface QueryProvider {
	public String insert(Asset asset);

	public String update(Asset asset);

	public String delete(Asset asset);

	public String approve(Asset asset);

	public String publish(Asset asset);
}
```
and each table implements above interface and provides respective queries, implementations are defined under package *com.astra.hackathon.asset.query.providers*.

Additionally, asset-models define some exception classes, model-to-json utility class.

### User

User module interacts with only two tables
- user_buckets
- user_asset_counts

And it registers the respective mapper and dao classes using the Configuration class

```
@Configuration
public class CassandraModelConfiguration {

	@Bean
	@Autowired
	public UserBucektsMapper userBucektsMapperBuilder(CqlSession session) {
		return new UserBucektsMapperBuilder(session).build();
	}

	@Bean
	@Autowired
	public UserBucketsDao userBucketsDao(UserBucektsMapper userBucektsMapper) {
		return userBucektsMapper.userBucketsDao();
	}

	@Bean
	@Autowired
	public UserAssetCountsMapper userAssetCountsMapperBuilder(CqlSession session) {
		return new UserAssetCountsMapperBuilder(session).build();
	}

	@Bean
	@Autowired
	public UserAssetCountsDao userAssetCountsDao(UserAssetCountsMapper userAssetCountsMapper) {
		return userAssetCountsMapper.userAssetCountsDao();
	}
}
```

It exposes total 4 REST handlers using two RestController classes - *UserAuthoringController* & *UserSearchController*

1. /user, POST
2. /inactive, GET
3. /users, GET
4. /users/{userId} GET

For database interaction using Mapper classes is being done through @Service classes - *UserAuthoringHandler* & *UserSearchHandler*

### Authoring

This module deals with 5 tables 

1. asset
2. asset_by_bucket
3. asset_by_name
4. asset_by_type
5. asset_by_status

It exposes total 5 REST handlers using two RestController classes - *AssetAuthoringController* & *AssetPublishingController*

1. /asset, POST
2. /asset/{identifier}, PUT
3. /asset/{identifier}, DELETE
4. /asset/approve/{identifier}/{approver} GET
5. /asset/publish/{identifier}/{publisher} GET

For database interaction using Mapper classes is being done through @Service classes - *AssetAuthoringHandler* & *AssetPublishingHandler*

### Search

This module deals with 5 tables 

1. asset
2. asset_by_bucket
3. asset_by_name
4. asset_by_type
5. asset_by_status

It exposes total 5 REST handlers using two RestController classes - *AssetSearchController*

1. /assets, GET
2. /assets/{identifier}, GET
3. /assets/buckets/{bucketName}, GET
4. /assets/buckets/{bucketName}/{name} GET
5. /assets/{name}/buckets GET
6. /assets/{name}/buckets/{bucketName} GET
7. /assets/status/{status} GET
8. /assets/status/{status}/{bucketName} GET
9. /assets/status/{status}/{bucketName}/{name} GET
10. /assets/type/{type} GET
11. /assets/type/{type}/{bucketName} GET
12. /assets/type/{type}/{bucketName}/{name} GET

For database interaction using Mapper classes is being done through @Service class - *AssetSearchHandler*

## Swagger

Swagger API documentation is integrated with each module which is exposing REST handlers and steps are 

Add below dependencies in specific module
```
<dependency>
				<groupId>io.springfox</groupId>
				<artifactId>springfox-swagger2</artifactId>
				<version>${swagger2.version}</version>
			</dependency>
			<dependency>
				<groupId>io.springfox</groupId>
				<artifactId>springfox-swagger-ui</artifactId>
				<version>${swagger2.version}</version>
				<scope>compile</scope>
			</dependency>
```

and add Configuration class

```
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}
}
```

and urls are (please open in new tab or window)

 - [asset-author](http://localhost:8081/swagger-ui.html#)
 - [asset-author](http://localhost:8082/swagger-ui.html#)
 - [user-provision](http://localhost:8083/swagger-ui.html#)

## Ending Note

Hope you like the concept of exploring different features of Cassandra. Defnitely, tis is not an end-to-end application, it is created to demostrate different features and capabilities of Cassandra Driver and modelling. I tried my best to conceptualize that and explain using the above documentation. For more information and follow up, please reach out to me.
