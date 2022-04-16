INSERT INTO ranking_average(ranking, cant_rates) VALUES (3,1);
INSERT INTO ranking_average(ranking, cant_rates) VALUES (3,1);
INSERT INTO ranking_average(ranking, cant_rates) VALUES (3,1);
INSERT INTO ranking_average(ranking, cant_rates) VALUES (3,1);

INSERT INTO stand(id, title, short_description, description, cover, ranking_average_id, latitude, longitude, average_time) VALUES('0C:F3:EE:08:FC:DD', 'Combot', 'Un proyecto con eje ecológico',
'Un proyecto con eje ecológico. Conocé ComBot,  la compactadora de botellas PET creada en su totalidad por Martín Pérez Mendoza y Santiago Carrillo.', 'https://user-images.githubusercontent.com/7771294/68998284-32920f80-088f-11ea-86aa-648135b7d262.jpg', 101,-34.55905, -58.45341, 10);

INSERT INTO stand(id, title, short_description, description, cover, ranking_average_id, latitude, longitude, average_time) VALUES('0C:F3:EE:04:18:A0', 'SYNTHETIC VOICE HARMONIZATION',
'Software para armonizar sonidos musicales', 'El proyecto se basa en el diseño de un software para armonizar sonidos musicales. Un armonizador es una aplicación de hardware o software que toma una señal de audio y le suma su tercera voz (los sonidos de la señal desplazados en su escala musical). El programa recibe un archivo de audio, calcula la escala de la pieza musical, determina las notas y las desplaza en su escala para obtener la tercera voz. Esta voz es sumada a la original, creando un efecto de armonización. Se utilizan algoritmos de procesamiento de audio, como el McLeud Pitch Method y un phase vocoder, con ciertas modificaciones ideadas para obtener la implementación deseada.', 'https://user-images.githubusercontent.com/7771294/68998580-1b552100-0893-11ea-8384-42a22b6d901a.jpeg', 102,-34.55907, -58.4534, 10);

INSERT INTO stand(id, title, short_description, description, cover, ranking_average_id, latitude, longitude, average_time) VALUES('0C:F3:EE:04:19:2F', 'Auto Formula SAE',
'Creado por estudiantes de Ingeniería Mecánica del ITBA','Auto Fórmula SAE creado por estudiantes de Ingeniería Mecánica del ITBA.', 'https://user-images.githubusercontent.com/7771294/68998234-83553880-088e-11ea-8763-b01236188de6.jpg', 103,-34.55904, -58.45342, 10);


INSERT INTO stand(id, title, short_description, description, cover, ranking_average_id, latitude, longitude, average_time) VALUES('0C:F3:EE:04:19:8E', 'GAZE TRACKER',
'Detección y seguimiento de pupilas', 'Es un sistema de detección y seguimiento de pupilas utilizando una webcam, aplicado a la estimación de la posición de la vista del usuario.', 'https://user-images.githubusercontent.com/7771294/68998629-c239bd00-0893-11ea-9f84-9cf179282c6d.jpeg', 104, -34.55906, -58.45344, 10);

INSERT INTO stand_ranking_device(stand_id, device_id, ranking) VALUES('0C:F3:EE:08:FC:DD', 'device_1', 3);
INSERT INTO stand_ranking_device(stand_id, device_id, ranking) VALUES('0C:F3:EE:04:19:2F', 'device_1', 3);
INSERT INTO stand_ranking_device(stand_id, device_id, ranking) VALUES('0C:F3:EE:04:18:A0', 'device_1', 3);
INSERT INTO stand_ranking_device(stand_id, device_id, ranking) VALUES('0C:F3:EE:04:19:8E', 'device_1', 3);

INSERT INTO stand_pictures(stand_id, picture) VALUES('0C:F3:EE:08:FC:DD', 'https://user-images.githubusercontent.com/7771294/68998258-f9599f80-088e-11ea-931c-86f192479939.jpeg');
INSERT INTO stand_pictures(stand_id, picture) VALUES('0C:F3:EE:08:FC:DD', 'https://user-images.githubusercontent.com/7771294/68998284-32920f80-088f-11ea-86aa-648135b7d262.jpg');
INSERT INTO stand_pictures(stand_id, picture) VALUES('0C:F3:EE:04:19:2F', 'https://user-images.githubusercontent.com/7771294/68998312-7dac2280-088f-11ea-82b7-975f253f9cdc.jpg');
INSERT INTO stand_pictures(stand_id, picture) VALUES('0C:F3:EE:04:19:2F', 'https://user-images.githubusercontent.com/7771294/68998234-83553880-088e-11ea-8763-b01236188de6.jpg');
INSERT INTO stand_pictures(stand_id, picture) VALUES('0C:F3:EE:04:19:2F', 'https://user-images.githubusercontent.com/7771294/68998440-358dff80-0891-11ea-9055-3e416cfc00b3.jpeg');
INSERT INTO stand_pictures(stand_id, picture) VALUES('0C:F3:EE:04:19:2F', 'https://user-images.githubusercontent.com/7771294/68998441-36269600-0891-11ea-8cc7-bc9a7172be82.jpeg');
INSERT INTO stand_pictures(stand_id, picture) VALUES('0C:F3:EE:04:19:2F', 'https://user-images.githubusercontent.com/7771294/68998442-36269600-0891-11ea-956c-17edb33f4651.jpg');
INSERT INTO stand_pictures(stand_id, picture) VALUES('0C:F3:EE:04:19:2F', 'https://user-images.githubusercontent.com/7771294/68998444-36269600-0891-11ea-8cc5-c0263263fd8f.jpeg');
INSERT INTO stand_pictures(stand_id, picture) VALUES('0C:F3:EE:04:19:2F', 'https://user-images.githubusercontent.com/7771294/68998445-36269600-0891-11ea-9747-12d2401e441d.jpg');
INSERT INTO stand_pictures(stand_id, picture) VALUES('0C:F3:EE:04:19:2F', 'https://user-images.githubusercontent.com/7771294/68998446-36bf2c80-0891-11ea-8448-d6cf724d30cb.jpg');
INSERT INTO stand_pictures(stand_id, picture) VALUES('0C:F3:EE:04:18:A0', 'https://user-images.githubusercontent.com/7771294/68998580-1b552100-0893-11ea-8384-42a22b6d901a.jpeg');
INSERT INTO stand_pictures(stand_id, picture) VALUES('0C:F3:EE:04:18:A0', 'https://user-images.githubusercontent.com/7771294/68998593-45a6de80-0893-11ea-9de7-9d474eebc771.png');
INSERT INTO stand_pictures(stand_id, picture) VALUES('0C:F3:EE:04:19:8E', 'https://user-images.githubusercontent.com/7771294/68998629-c239bd00-0893-11ea-9f84-9cf179282c6d.jpeg');
INSERT INTO stand_pictures(stand_id, picture) VALUES('0C:F3:EE:04:19:8E', 'https://user-images.githubusercontent.com/7771294/68998630-c2d25380-0893-11ea-9a90-967494e0fa8d.jpg');
INSERT INTO stand_pictures(stand_id, picture) VALUES('0C:F3:EE:04:19:8E', 'https://user-images.githubusercontent.com/7771294/68998631-c2d25380-0893-11ea-8a97-7d1ecf182caf.jpeg');

INSERT INTO expo_hours(id, start, finish) VALUES (0, '0:00:00', '8:00:00');
INSERT INTO expo_hours(id, start, finish) VALUES (1, '8:00:00', '10:00:00');
INSERT INTO expo_hours(id, start, finish) VALUES (2, '10:00:00', '12:00:00');
INSERT INTO expo_hours(id, start, finish) VALUES (3, '12:00:00', '14:00:00');
INSERT INTO expo_hours(id, start, finish) VALUES (4, '14:00:00', '16:00:00');
INSERT INTO expo_hours(id, start, finish) VALUES (5, '16:00:00', '18:00:00');
INSERT INTO expo_hours(id, start, finish) VALUES (6, '18:00:00', '20:00:00');
INSERT INTO expo_hours(id, start, finish) VALUES (7, '20:00:00', '23:59:59');

INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:08:FC:DD', 0, 2);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:08:FC:DD', 1, 2);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:08:FC:DD', 2, 2);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:08:FC:DD', 3, 2);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:08:FC:DD', 4, 2);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:08:FC:DD', 5, 0);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:08:FC:DD', 6, 0);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:08:FC:DD', 7, 0);

INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:04:19:2F', 0, 10);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:04:19:2F', 1, 10);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:04:19:2F', 2, 10);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:04:19:2F', 3, 10);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:04:19:2F', 4, 10);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:04:19:2F', 5, 5);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:04:19:2F', 6, 5);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:04:19:2F', 7, 5);

INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:04:18:A0', 0, 0);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:04:18:A0', 1, 0);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:04:18:A0', 2, 0);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:04:18:A0', 3, 0);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:04:18:A0', 4, 0);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:04:18:A0', 5, 0);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:04:18:A0', 6, 0);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:04:18:A0', 7, 0);

INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:04:19:8E', 0, 2);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:04:19:8E', 1, 2);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:04:19:8E', 2, 2);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:04:19:8E', 3, 2);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:04:19:8E', 4, 2);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:04:19:8E', 5, 0);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:04:19:8E', 6, 0);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:04:19:8E', 7, 0);

INSERT INTO device_tours(device_id, stand_ids) VALUES ('device_1', '0C:F3:EE:08:FC:DD 0C:F3:EE:04:19:2F 0C:F3:EE:04:18:A0');
INSERT INTO device_tours(device_id, stand_ids) VALUES ('device_2', '0C:F3:EE:08:FC:DD 0C:F3:EE:04:19:2F 0C:F3:EE:04:19:8E');
INSERT INTO device_tours(device_id, stand_ids) VALUES ('device_3', '0C:F3:EE:08:FC:DD 0C:F3:EE:04:19:2F 0C:F3:EE:04:18:A0');
INSERT INTO device_tours(device_id, stand_ids) VALUES ('device_4', '0C:F3:EE:08:FC:DD 0C:F3:EE:04:19:2F 0C:F3:EE:04:18:A0');
INSERT INTO device_tours(device_id, stand_ids) VALUES ('device_5', '0C:F3:EE:08:FC:DD 0C:F3:EE:04:19:2F 0C:F3:EE:04:18:A0');
INSERT INTO device_tours(device_id, stand_ids) VALUES ('device_6', '0C:F3:EE:08:FC:DD 0C:F3:EE:04:19:2F 0C:F3:EE:04:19:8E');
INSERT INTO device_tours(device_id, stand_ids) VALUES ('device_7', '0C:F3:EE:08:FC:DD 0C:F3:EE:04:19:2F');
INSERT INTO device_tours(device_id, stand_ids) VALUES ('device_8', '0C:F3:EE:08:FC:DD 0C:F3:EE:04:19:2F');

INSERT INTO tour_visits(stand_ids, visits) VALUES ('0C:F3:EE:08:FC:DD 0C:F3:EE:04:19:2F 0C:F3:EE:04:18:A0', 4);
INSERT INTO tour_visits(stand_ids, visits) VALUES ('0C:F3:EE:08:FC:DD 0C:F3:EE:04:19:2F 0C:F3:EE:04:19:8E', 2);
INSERT INTO tour_visits(stand_ids, visits) VALUES ('0C:F3:EE:08:FC:DD 0C:F3:EE:04:19:2F', 2);