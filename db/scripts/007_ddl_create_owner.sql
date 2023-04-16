CREATE TABLE IF NOT EXISTS owner (
   id SERIAL PRIMARY KEY,
   name VARCHAR NOT NULL,
   auto_user_id INTEGER NOT NULL,
   CONSTRAINT fk_driver$auto_user FOREIGN KEY (auto_user_id) REFERENCES auto_user(id)
);