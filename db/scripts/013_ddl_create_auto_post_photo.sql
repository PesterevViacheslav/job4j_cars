CREATE TABLE IF NOT EXISTS auto_post_photo (
     id SERIAL PRIMARY KEY,
     auto_post_id INTEGER NOT NULL,
     photo_id INTEGER NOT NULL,
     CONSTRAINT fk_auto_post_photo$post FOREIGN KEY (auto_post_id) REFERENCES auto_post(id),
     CONSTRAINT fk_auto_post_photo$photo FOREIGN KEY (photo_id) REFERENCES photo(id)
);