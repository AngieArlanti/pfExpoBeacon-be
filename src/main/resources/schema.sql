DROP TABLE IF EXISTS device_proximity;
DROP TABLE IF EXISTS stand_pictures;
DROP TABLE IF EXISTS stand_ranking;
DROP TABLE If EXISTS stand_ranking_device;
DROP TABLE IF EXISTS stand;
DROP TABLE IF EXISTS ranking_average;

CREATE TABLE IF NOT EXISTS ranking_average(
    id SERIAL,
    ranking FLOAT,
    cant_rates INTEGER,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS stand(
   id VARCHAR NOT NULL PRIMARY KEY,
   title VARCHAR NOT NULL,
   short_description VARCHAR NOT NULL,
   description VARCHAR NOT NULL,
   cover VARCHAR NOT NULL,
   ranking_average_id INTEGER REFERENCES ranking_average (id)
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
    immediate_stand_id VARCHAR NOT NULL,
    update_time TIMESTAMP NOT NULL,
    PRIMARY KEY (device_id),
    FOREIGN KEY (immediate_stand_id) REFERENCES stand (id)
);
CREATE TABLE IF NOT EXISTS stand_ranking_device(
    stand_id VARCHAR NOT NULL,
    device_id VARCHAR NOT NULL,
    ranking INTEGER,
    PRIMARY KEY(stand_id, device_id),
    FOREIGN KEY (stand_id) REFERENCES stand (id)
);