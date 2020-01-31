DROP TABLE IF EXISTS device_location_history;
DROP TABLE IF EXISTS stand_visit_hours;
DROP TABLE IF EXISTS expo_hours;
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

CREATE TABLE IF NOT EXISTS expo_hours(
    id SERIAL PRIMARY KEY,
    start TIME NOT NULL,
    finish TIME NOT NULL
);

CREATE TABLE IF NOT EXISTS stand_visit_hours(
    stand_id VARCHAR NOT NULL,
    expo_hours_id SERIAL NOT NULL,
    visits INTEGER NOT NULL,
    PRIMARY KEY (stand_id, expo_hours_id),
    FOREIGN KEY (stand_id) REFERENCES stand (id),
    FOREIGN KEY (expo_hours_id) REFERENCES expo_hours (id)
);

CREATE TABLE IF NOT EXISTS device_location_history(
device_id VARCHAR NOT NULL,
stand_id VARCHAR NOT NULL,
distance FLOAT NOT NULL,
update_time TIMESTAMP NOT NULL
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
