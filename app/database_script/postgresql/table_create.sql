-- Table: account

-- DROP TABLE account;

CREATE TABLE account
(
  name character varying,
  id bigserial NOT NULL,
  enabled boolean NOT NULL DEFAULT false,
  created_at timestamp with time zone,
  updated_at timestamp with time zone,
  password character varying NOT NULL DEFAULT ''::character varying,
  is_admin boolean NOT NULL DEFAULT false,
  CONSTRAINT account_pkey PRIMARY KEY (id)
);

-- Table: download_task

-- DROP TABLE download_task;

CREATE TABLE download_task
(
  id bigserial NOT NULL,
  created_at timestamp with time zone NOT NULL,
  updated_at timestamp with time zone NOT NULL,
  enabled boolean NOT NULL DEFAULT false,
  client_ip character varying NOT NULL DEFAULT ''::character varying,
  file_id bigint NOT NULL,
  expired_at timestamp with time zone NOT NULL,
  time_cost_millis bigint NOT NULL,
  last_dlded_at timestamp with time zone,
  user_id bigint NOT NULL,
  uuid character varying NOT NULL,
  CONSTRAINT download_task_pkey PRIMARY KEY (id),
  CONSTRAINT download_task_id_key UNIQUE (id),
  CONSTRAINT download_task_uuid_key UNIQUE (uuid)
);

-- Table: file

-- DROP TABLE file;

CREATE TABLE file
(
  id bigserial NOT NULL,
  dir character varying NOT NULL DEFAULT ''::character varying,
  size bigint NOT NULL DEFAULT 0,
  created_at timestamp with time zone NOT NULL,
  updated_at timestamp with time zone NOT NULL,
  enabled boolean NOT NULL DEFAULT false,
  name character varying NOT NULL,
  production_id bigint NOT NULL,
  sd_card_price_fen bigint NOT NULL,
  md character varying NOT NULL,
  file_service_group_id bigint DEFAULT 1,
  CONSTRAINT file_pkey PRIMARY KEY (id)
);

-- Table: file_service

-- DROP TABLE file_service;

CREATE TABLE file_service
(
  id bigserial NOT NULL,
  created_at timestamp with time zone NOT NULL,
  updated_at timestamp with time zone NOT NULL,
  enabled boolean NOT NULL,
  group_id bigint NOT NULL,
  host character varying NOT NULL,
  CONSTRAINT file_service_pkey PRIMARY KEY (id)
);

-- Table: file_service_group

-- DROP TABLE file_service_group;

CREATE TABLE file_service_group
(
  id bigserial NOT NULL,
  created_at timestamp with time zone NOT NULL,
  updated_at timestamp with time zone NOT NULL,
  enabled boolean NOT NULL,
  name character varying NOT NULL,
  CONSTRAINT file_service_group_pkey PRIMARY KEY (id),
  CONSTRAINT file_service_group_name_key UNIQUE (name)
);

-- Table: production

-- DROP TABLE production;

CREATE TABLE production
(
  id bigserial NOT NULL,
  description character varying NOT NULL DEFAULT ''::character varying,
  created_at timestamp with time zone NOT NULL,
  updated_at timestamp with time zone NOT NULL,
  enabled boolean NOT NULL DEFAULT false,
  name character varying NOT NULL,
  dir character varying NOT NULL,
  CONSTRAINT prod_pkey PRIMARY KEY (id),
  CONSTRAINT production_dir_key UNIQUE (dir),
  CONSTRAINT production_id_key UNIQUE (id)
);

-- Table: sd_order

-- DROP TABLE sd_order;

CREATE TABLE sd_order
(
  id bigserial NOT NULL,
  created_at timestamp with time zone NOT NULL,
  updated_at timestamp with time zone NOT NULL,
  file_id bigint NOT NULL,
  status integer NOT NULL,
  username character varying NOT NULL,
  user_addr character varying NOT NULL,
  user_zip_code character varying NOT NULL,
  user_mobile character varying NOT NULL DEFAULT ''::character varying,
  user_email character varying NOT NULL DEFAULT ''::character varying,
  enabled boolean NOT NULL DEFAULT false,
  price_fen bigint NOT NULL,
  user_id bigint NOT NULL,
  uuid character varying NOT NULL,
  CONSTRAINT sd_order_pkey PRIMARY KEY (id),
  CONSTRAINT sd_order_uuid_key UNIQUE (uuid)
);
