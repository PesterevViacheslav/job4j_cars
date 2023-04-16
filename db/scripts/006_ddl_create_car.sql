CREATE TABLE IF NOT EXISTS car (
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    engine_id INTEGER NOT NULL,
    CONSTRAINT fk_car$engine FOREIGN KEY (engine_id) REFERENCES engine(id)
);