CREATE TABLE account
(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  enabled INTEGER NOT NULL,
  created_at TEXT NOT NULL,
  updated_at TEXT NOT NULL,
  name TEXT UNIQUE NOT NULL,
  password TEXT NOT NULL,
  is_admin INTEGER NOT NULL
);

CREATE TABLE download_task
(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  enabled INTEGER NOT NULL,
  created_at TEXT NOT NULL,
  updated_at TEXT NOT NULL,
  client_ip TEXT NOT NULL,
  file_id INTEGER NOT NULL,
  expired_at TEXT NOT NULL,
  time_cost_millis INTEGER NOT NULL,
  last_dlded_at TEXT NOT NULL,
  user_id INTEGER NOT NULL,
  uuid TEXT UNIQUE NOT NULL
);

CREATE TABLE file
(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  enabled INTEGER NOT NULL,
  created_at TEXT NOT NULL,
  updated_at TEXT NOT NULL,
  dir TEXT NOT NULL,
  size INTEGER NOT NULL DEFAULT 0,
  name TEXT NOT NULL,
  production_id INTEGER NOT NULL,
  md TEXT NOT NULL,
  file_service_group_id INTEGER NOT NULL
);

CREATE TABLE file_service
(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  enabled INTEGER NOT NULL,
  created_at TEXT NOT NULL,
  updated_at TEXT NOT NULL,
  group_id INTEGER NOT NULL,
  host TEXT NOT NULL,
  name TEXT UNIQUE NOT NULL
);

CREATE TABLE file_service_group
(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  enabled INTEGER NOT NULL,
  created_at TEXT NOT NULL,
  updated_at TEXT NOT NULL,
  name TEXT UNIQUE NOT NULL
);

CREATE TABLE production
(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  enabled INTEGER NOT NULL,
  created_at TEXT NOT NULL,
  updated_at TEXT NOT NULL,
  description TEXT NOT NULL,
  name TEXT UNIQUE NOT NULL,
  dir TEXT NOT NULL
);
