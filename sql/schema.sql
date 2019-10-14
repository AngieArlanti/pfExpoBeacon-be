CREATE TABLE stand(
   id SERIAL,
   title VARCHAR NOT NULL,
   description VARCHAR NOT NULL,
   macAddress VARCHAR NOT NULL PRIMARY KEY,
   iconUrl VARCHAR NOT NULL
);

INSERT INTO stand(title, description, macAddress, iconUrl) VALUES('The first stand', 'Some stand with a description',
'0C:F3:EE:08:FC:DD', 'http://modular360.es/wp-content/uploads/2016/11/Stand-Basic-6-3-caras.png');

INSERT INTO stand(title, description, macAddress, iconUrl) VALUES('The second stand', 'Some stand with a description',
'0C:F3:EE:04:19:2F', 'http://modular360.es/wp-content/uploads/2016/11/Stand-Basic-6-3-caras.png');

INSERT INTO stand(title, description, macAddress, iconUrl) VALUES('The third stand', 'Some stand with a description',
'0C:F3:EE:04:18:A0', 'http://modular360.es/wp-content/uploads/2016/11/Stand-Basic-6-3-caras.png');