DROP TABLE stand;

CREATE TABLE stand(
   id SERIAL,
   mac_address VARCHAR NOT NULL PRIMARY KEY,
   title VARCHAR NOT NULL,
   description VARCHAR NOT NULL,
   icon_url VARCHAR NOT NULL
);