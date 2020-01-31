DROP TABLE IF EXISTS device_proximity;
DROP TABLE IF EXISTS stand_pictures;
DROP TABLE IF EXISTS stand;

CREATE TABLE IF NOT EXISTS stand(
   id VARCHAR NOT NULL PRIMARY KEY,
   title VARCHAR NOT NULL,
   short_description VARCHAR NOT NULL,
   description VARCHAR NOT NULL,
   cover VARCHAR NOT NULL,
   ranking INTEGER,
   latitude FLOAT NOT NULL,
   longitude FLOAT NOT NULL,
   stand_number SERIAL,
   average_time FLOAT
);

CREATE TABLE IF NOT EXISTS stand_pictures(
    id SERIAL,
    stand_id VARCHAR NOT NULL,
    picture VARCHAR NOT NULL,
    PRIMARY KEY (id, stand_id),
    FOREIGN KEY (stand_id) REFERENCES stand (id)
);

CREATE TABLE IF NOT EXISTS device_proximity(
    device_id VARCHAR NOT NULL,
    stand_id VARCHAR NOT NULL,
    distance FLOAT NOT NULL,
    update_time TIMESTAMP NOT NULL,
    PRIMARY KEY (device_id, stand_id),
    FOREIGN KEY (stand_id) REFERENCES stand (id)
);
