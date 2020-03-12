DROP TABLE IF EXISTS device_location_history;
DROP TABLE IF EXISTS stand_visit_hours;
DROP TABLE IF EXISTS expo_hours;
DROP TABLE IF EXISTS device_proximity;
DROP TABLE IF EXISTS stand_pictures;
DROP TABLE IF EXISTS stand_ranking;
DROP TABLE If EXISTS stand_ranking_device;
DROP TABLE IF EXISTS stand;
DROP TABLE IF EXISTS ranking_average;
DROP TABLE IF EXISTS device_tours;
DROP TABLE IF EXISTS tour_visits;
DROP TABLE IF EXISTS location;

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
   ranking_average_id INTEGER,
   latitude FLOAT NOT NULL,
   longitude FLOAT NOT NULL,
   stand_number SERIAL,
   average_time FLOAT,
   FOREIGN KEY (ranking_average_id) REFERENCES ranking_average (id)
);

CREATE TABLE IF NOT EXISTS stand_pictures(
    id SERIAL,
    stand_id VARCHAR NOT NULL REFERENCES stand(id) ON DELETE CASCADE,
    picture VARCHAR NOT NULL,
    PRIMARY KEY (id, stand_id)
);

CREATE TABLE IF NOT EXISTS device_proximity(
    device_id VARCHAR NOT NULL,
    stand_id VARCHAR NOT NULL REFERENCES stand(id) ON DELETE CASCADE,
    distance FLOAT NOT NULL,
    update_time TIMESTAMP NOT NULL,
    PRIMARY KEY (device_id, stand_id)
);

CREATE TABLE IF NOT EXISTS expo_hours(
    id SERIAL PRIMARY KEY,
    start TIME NOT NULL,
    finish TIME NOT NULL
);

CREATE TABLE IF NOT EXISTS stand_visit_hours(
    stand_id VARCHAR NOT NULL REFERENCES stand(id) ON DELETE CASCADE,
    expo_hours_id SERIAL NOT NULL,
    visits INTEGER NOT NULL,
    PRIMARY KEY (stand_id, expo_hours_id),
    FOREIGN KEY (expo_hours_id) REFERENCES expo_hours (id)
);

CREATE TABLE IF NOT EXISTS device_location_history(
    device_id VARCHAR NOT NULL,
    stand_id VARCHAR NOT NULL REFERENCES stand(id) ON DELETE CASCADE,
    distance FLOAT NOT NULL,
    update_time TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS stand_ranking_device(
    stand_id VARCHAR NOT NULL REFERENCES stand(id) ON DELETE CASCADE,
    device_id VARCHAR NOT NULL,
    ranking INTEGER,
    PRIMARY KEY(stand_id, device_id)
);

CREATE TABLE IF NOT EXISTS device_tours(
    device_id VARCHAR NOT NULL,
    stand_ids VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS tour_visits(
    stand_ids VARCHAR NOT NULL PRIMARY KEY,
    visits INTEGER NOT NULL
);

CREATE OR REPLACE FUNCTION update_device_location_history_with_device_proximity() RETURNS TRIGGER LANGUAGE 'plpgsql' AS '
BEGIN
INSERT INTO device_location_history (device_id, stand_id, distance, update_time)
VALUES (NEW.device_id, NEW.stand_id, NEW.distance, NEW.update_time);

RETURN NEW;
END; ';

CREATE TRIGGER update_device_location_history
AFTER INSERT OR UPDATE ON device_proximity
FOR EACH ROW
EXECUTE PROCEDURE update_device_location_history_with_device_proximity();

CREATE TABLE IF NOT EXISTS location(
    device_id VARCHAR NOT NULL PRIMARY KEY,
    latitude FLOAT NOT NULL,
    longitude FLOAT NOT NULL,
    update_time TIMESTAMP NOT NULL
);