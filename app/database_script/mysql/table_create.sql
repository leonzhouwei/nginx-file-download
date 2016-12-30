CREATE TABLE account
(
  id bigint PRIMARY KEY AUTO_INCREMENT,
  enabled bool NOT NULL,
  created_at datetime NOT NULL,
  updated_at datetime NOT NULL,
  name VARCHAR(256) UNIQUE NOT NULL,
  password VARCHAR(256) NOT NULL,
  role_id bigint NOT NULL
);
create index idx_account_name_pswd on account (name, password);

CREATE TABLE download_task
(
  id bigint PRIMARY KEY AUTO_INCREMENT,
  enabled bool NOT NULL,
  created_at datetime NOT NULL,
  updated_at datetime NOT NULL,
  client_ip VARCHAR(256) NOT NULL,
  file_id bigint NOT NULL,
  expired_at datetime NOT NULL,
  time_cost_millis bigint NOT NULL,
  last_dlded_at datetime NOT NULL,
  user_id bigint NOT NULL,
  uuid VARCHAR(256) UNIQUE NOT NULL
);

CREATE TABLE file
(
  id bigint PRIMARY KEY AUTO_INCREMENT,
  enabled bool NOT NULL,
  created_at datetime NOT NULL,
  updated_at datetime NOT NULL,
  dir VARCHAR(256) NOT NULL,
  size bigint NOT NULL DEFAULT 0,
  name VARCHAR(256) NOT NULL,
  production_id bigint NOT NULL,
  md VARCHAR(256) NOT NULL,
  file_service_group_id bigint NOT NULL
);

CREATE TABLE file_service
(
  id bigint PRIMARY KEY AUTO_INCREMENT,
  enabled bool NOT NULL,
  created_at datetime NOT NULL,
  updated_at datetime NOT NULL,
  group_id bigint NOT NULL,
  host VARCHAR(256) NOT NULL,
  name VARCHAR(256) UNIQUE NOT NULL
);

CREATE TABLE file_service_group
(
  id bigint PRIMARY KEY AUTO_INCREMENT,
  enabled bool NOT NULL,
  created_at datetime NOT NULL,
  updated_at datetime NOT NULL,
  name VARCHAR(256) UNIQUE NOT NULL
);

CREATE TABLE production
(
  id bigint PRIMARY KEY AUTO_INCREMENT,
  enabled bool NOT NULL,
  created_at datetime NOT NULL,
  updated_at datetime NOT NULL,
  description VARCHAR(256) NOT NULL,
  name VARCHAR(256) UNIQUE NOT NULL,
  dir VARCHAR(256) NOT NULL
);
