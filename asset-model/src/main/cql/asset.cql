-- Asset Main table --

CREATE KEYSPACE ks_asset WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 3};

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
