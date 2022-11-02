CREATE TABLE IF NOT EXISTS auto_user (
     id         SERIAL PRIMARY KEY,
     login      TEXT NOT NULL,
     password   TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS auto_post (
      id          SERIAL PRIMARY KEY,
      description TEXT NOT NULL,
      created     TIMESTAMP NOT NULL default now(),
      auto_user_id INT NOT NULL REFERENCES auto_user(id) ON DELETE CASCADE
    );

CREATE UNIQUE INDEX IF NOT EXISTS uq_auto_user_login ON auto_user(lower(trim(login)) ASC);
