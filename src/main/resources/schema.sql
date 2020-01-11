DROP TABLE IF EXISTS stand_pictures;
DROP TABLE IF EXISTS stand;

CREATE TABLE IF NOT EXISTS stand(
   id VARCHAR NOT NULL PRIMARY KEY,
   title VARCHAR NOT NULL,
   short_description VARCHAR NOT NULL,
   description VARCHAR NOT NULL,
   cover VARCHAR NOT NULL,
   ranking INTEGER
);

CREATE TABLE IF NOT EXISTS stand_pictures(
    id SERIAL,
    stand_id VARCHAR NOT NULL,
    picture VARCHAR NOT NULL,
    PRIMARY KEY (id, stand_id),
    FOREIGN KEY (stand_id) REFERENCES stand (id)
);

