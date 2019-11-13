DROP TABLE stand_icon_urls;
DROP TABLE stand;

CREATE TABLE stand(
   id VARCHAR NOT NULL PRIMARY KEY,
   title VARCHAR NOT NULL,
   description VARCHAR NOT NULL,
   cover_url VARCHAR NOT NULL,
   ranking INTEGER
);

CREATE TABLE stand_icon_urls(
    id SERIAL,
    stand_id VARCHAR NOT NULL,
    icon_url VARCHAR NOT NULL,
    PRIMARY KEY (id, stand_id),
    FOREIGN KEY (stand_id) REFERENCES stand (id)
);

