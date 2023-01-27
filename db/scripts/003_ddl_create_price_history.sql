CREATE TABLE IF NOT EXISTS price_history(
    id SERIAL PRIMARY KEY,
    before BIGINT not null,
    after BIGINT not null,
    created TIMESTAMP DEFAULT now(),
    post_id int references auto_post(id)
);