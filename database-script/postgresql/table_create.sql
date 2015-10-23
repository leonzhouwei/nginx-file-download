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
  CONSTRAINT account_pkey PRIMARY KEY (id)
)

-- Table: download_history

-- DROP TABLE download_history;

CREATE TABLE download_history
(
  id bigserial NOT NULL,
  task_id bigint NOT NULL DEFAULT (-1),
  created_at timestamp with time zone NOT NULL,
  updated_at timestamp with time zone NOT NULL,
  enabled boolean NOT NULL DEFAULT false,
  client_ip character varying NOT NULL DEFAULT ''::character varying,
  web_server_host character varying NOT NULL DEFAULT ''::character varying,
  app_server_thread_name character varying NOT NULL DEFAULT ''::character varying,
  app_server_thread_id bigint NOT NULL,
  req_route character varying NOT NULL DEFAULT ''::character varying,
  req_params character varying NOT NULL DEFAULT ''::character varying,
  app_server_uuid character varying NOT NULL DEFAULT ''::character varying,
  CONSTRAINT download_histroy_pkey PRIMARY KEY (id)
)

-- Table: download_task

-- DROP TABLE download_task;

CREATE TABLE download_task
(
  id bigserial NOT NULL,
  created_at timestamp with time zone NOT NULL,
  updated_at timestamp with time zone NOT NULL,
  enabled boolean NOT NULL DEFAULT false,
  production_id bigint NOT NULL DEFAULT (-1),
  client_ip character varying NOT NULL DEFAULT ''::character varying,
  file_id bigint NOT NULL DEFAULT (-1),
  expired_at timestamp with time zone NOT NULL,
  time_cost_millis bigint NOT NULL DEFAULT 0,
  last_dlded_at timestamp with time zone,
  user_id bigint NOT NULL,
  CONSTRAINT download_task_pkey PRIMARY KEY (id),
  CONSTRAINT download_task_id_key UNIQUE (id)
)

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
  CONSTRAINT file_pkey PRIMARY KEY (id)
)

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
  CONSTRAINT prod_pkey PRIMARY KEY (id),
  CONSTRAINT production_id_key UNIQUE (id)
)

-- Table: sd_order

-- DROP TABLE sd_order;

CREATE TABLE sd_order
(
  id bigserial NOT NULL,
  created_at timestamp with time zone NOT NULL,
  updated_at timestamp with time zone NOT NULL,
  file_id bigint NOT NULL,
  status integer NOT NULL DEFAULT 0,
  username character varying NOT NULL,
  user_addr character varying NOT NULL,
  user_zip_code character varying NOT NULL,
  user_mobile character varying NOT NULL DEFAULT ''::character varying,
  user_email character varying NOT NULL DEFAULT ''::character varying,
  enabled boolean NOT NULL DEFAULT false,
  price_fen bigint NOT NULL DEFAULT 0,
  CONSTRAINT sd_order_pkey PRIMARY KEY (id)
)
