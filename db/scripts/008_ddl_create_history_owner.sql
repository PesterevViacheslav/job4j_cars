CREATE TABLE IF NOT EXISTS history_owner (
   id SERIAL PRIMARY KEY,
   car_id INTEGER NOT NULL,
   owner_id INTEGER NOT NULL,
   startDt TIMESTAMP NOT NULL,
   endDt TIMESTAMP,
   CONSTRAINT fk_history_owner$car FOREIGN KEY (car_id) REFERENCES car(id),
   CONSTRAINT fk_history_owner$driver FOREIGN KEY (owner_id) REFERENCES owner(id)
);