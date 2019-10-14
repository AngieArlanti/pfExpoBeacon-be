CREATE TABLE stand(
   id SERIAL,
   mac_address VARCHAR NOT NULL PRIMARY KEY,
   title VARCHAR NOT NULL,
   description VARCHAR NOT NULL,
   icon_url VARCHAR NOT NULL
);

INSERT INTO stand(title, description, mac_address, icon_url) VALUES('The first stand', 'Some stand with a description',
'0C:F3:EE:08:FC:DD', 'http://modular360.es/wp-content/uploads/2016/11/Stand-Basic-6-3-caras.png');

INSERT INTO stand(title, description, mac_address, icon_url) VALUES('The second stand', 'Some stand with a description',
'0C:F3:EE:04:19:2F', 'http://modular360.es/wp-content/uploads/2016/11/Stand-Basic-6-3-caras.png');

INSERT INTO stand(title, description, mac_address, icon_url) VALUES('The third stand', 'Some stand with a description',
'0C:F3:EE:04:18:A0', 'http://modular360.es/wp-content/uploads/2016/11/Stand-Basic-6-3-caras.png');