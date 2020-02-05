INSERT INTO ranking_average(ranking, cant_rates) VALUES (3,1);
INSERT INTO ranking_average(ranking, cant_rates) VALUES (5,4);
INSERT INTO ranking_average(ranking, cant_rates) VALUES (2,1);
INSERT INTO ranking_average(ranking, cant_rates) VALUES (3,2);
INSERT INTO ranking_average(ranking, cant_rates) VALUES (3,4);
INSERT INTO ranking_average(ranking, cant_rates) VALUES (2,3);
INSERT INTO ranking_average(ranking, cant_rates) VALUES (3,2);
INSERT INTO ranking_average(ranking, cant_rates) VALUES (2,1);

INSERT INTO stand(id, title, short_description, description, cover, ranking_average_id, latitude, longitude, average_time) VALUES('0C:F3:EE:08:FC:DD', 'Combot', 'Un proyecto con eje ecológico',
'Un proyecto con eje ecológico. Conocé ComBot,  la compactadora de botellas PET creada en su totalidad por Martín Pérez Mendoza y Santiago Carrillo.', 'https://user-images.githubusercontent.com/7771294/68998284-32920f80-088f-11ea-86aa-648135b7d262.jpg', 1,-34.6403175,-58.4018125, 10);

INSERT INTO stand(id, title, short_description, description, cover, ranking_average_id, latitude, longitude, average_time) VALUES('0C:F3:EE:04:19:2F', 'Auto Formula SAE',
'Creado por estudiantes de Ingeniería Mecánica del ITBA','Auto Fórmula SAE creado por estudiantes de Ingeniería Mecánica del ITBA.', 'https://user-images.githubusercontent.com/7771294/68998234-83553880-088e-11ea-8763-b01236188de6.jpg', 2,-34.6403200,-58.40183001, 10);

INSERT INTO stand(id, title, short_description, description, cover, ranking_average_id, latitude, longitude, average_time) VALUES('0C:F3:EE:04:18:A0', 'SYNTHETIC VOICE HARMONIZATION',
'Software para armonizar sonidos musicales', 'El proyecto se basa en el diseño de un software para armonizar sonidos musicales. Un armonizador es una aplicación de hardware o software que toma una señal de audio y le suma su tercera voz (los sonidos de la señal desplazados en su escala musical). El programa recibe un archivo de audio, calcula la escala de la pieza musical, determina las notas y las desplaza en su escala para obtener la tercera voz. Esta voz es sumada a la original, creando un efecto de armonización. Se utilizan algoritmos de procesamiento de audio, como el McLeud Pitch Method y un phase vocoder, con ciertas modificaciones ideadas para obtener la implementación deseada.', 'https://user-images.githubusercontent.com/7771294/68998580-1b552100-0893-11ea-8384-42a22b6d901a.jpeg', 3,-34.6403220,-58.40184121, 10);

INSERT INTO stand(id, title, short_description, description, cover, ranking_average_id, latitude, longitude, average_time) VALUES('0C:F3:EE:04:19:8E', 'GAZE TRACKER',
'Detección y seguimiento de pupilas', 'Es un sistema de detección y seguimiento de pupilas utilizando una webcam, aplicado a la estimación de la posición de la vista del usuario.', 'https://user-images.githubusercontent.com/7771294/68998629-c239bd00-0893-11ea-9f84-9cf179282c6d.jpeg', 4,-34.6403300,-58.40187172, 10);

INSERT INTO stand(id, title, short_description, description, cover, ranking_average_id, latitude, longitude, average_time) VALUES('0C:F3:EE:04:1B:E4', 'TATEMATIC',
'Tatetí con compuertas lógicas','El proyecto consiste en un juego de Tatetí, implementado únicamente con compuertas lógicas y utilizando una CPLD (Complex Programmable Logic Device). El juego permite jugador vs jugador o jugador vs máquina. Además, maneja una salida de audio que indica el estado de la jugada.', 'https://user-images.githubusercontent.com/7771294/68998767-522c3680-0895-11ea-92ee-695cc8f8be5f.jpg', 5,-34.6403340,-58.4018002, 10);

INSERT INTO stand(id, title, short_description, description, cover, ranking_average_id, latitude, longitude, average_time) VALUES('0C:F3:EE:04:19:21','PROTOTIPO DE PROYECTOR LASER',
'Seguidor láser con webcam', 'Es un seguidor Laser con webcam, con posibilidad de dibujar.', 'https://user-images.githubusercontent.com/7771294/68998806-cbc42480-0895-11ea-9c96-c468c3359a8f.jpeg', 6,-34.6403380,-58.40163441, 10);

INSERT INTO stand(id, title, short_description, description, cover, ranking_average_id, latitude, longitude, average_time) VALUES('0C:F3:EE:04:19:3E', 'CONTROL DE ACCESO',
'Control de acceso electrónico','Implementación de un control de acceso electrónico utilizando una CPLD (Complex Programmable Logic Device).', 'https://user-images.githubusercontent.com/7771294/68998873-adaaf400-0896-11ea-9fb7-420463365810.jpg', 7,-34.6403400,-58.4014111, 10);

INSERT INTO stand(id, title, short_description, description, cover, ranking_average_id, latitude, longitude, average_time) VALUES('0C:F3:EE:04:18:AC', 'PEDAL WAH WAH INALÁMBRICO',
'Pedal analógico controlado digitalmente','Los pedales Wah Wah son accesorios para guitarras que se usan desde los años 70. Existen pedales analógicos y digitales. Los primeros son caros y dominan el mercado, mientras que los segundos tienen más prestaciones y si bien son potencialmente escalables para producción en serie, no son aceptados por el mercado. El Wah Wah inalámbrico propuesto es un pedal analógico controlado digitalmente, es decir que reúne las ventajas comerciales de uno y las prestaciones de otro.', 'https://user-images.githubusercontent.com/7771294/68998872-adaaf400-0896-11ea-8ccf-2a7efb2b476e.jpeg', 8,-34.6403299,-58.4015221, 10);

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
INSERT INTO stand_pictures(stand_id, picture) VALUES('0C:F3:EE:04:1B:E4', 'https://user-images.githubusercontent.com/7771294/68998767-522c3680-0895-11ea-92ee-695cc8f8be5f.jpg');
INSERT INTO stand_pictures(stand_id, picture) VALUES('0C:F3:EE:04:1B:E4', 'https://user-images.githubusercontent.com/7771294/68998766-522c3680-0895-11ea-8246-c929374925e3.jpg');
INSERT INTO stand_pictures(stand_id, picture) VALUES('0C:F3:EE:04:1B:E4', 'https://user-images.githubusercontent.com/7771294/68998768-52c4cd00-0895-11ea-9851-24ec2a3e31ad.jpeg');
INSERT INTO stand_pictures(stand_id, picture) VALUES('0C:F3:EE:04:19:21', 'https://user-images.githubusercontent.com/7771294/68998806-cbc42480-0895-11ea-9c96-c468c3359a8f.jpeg');
INSERT INTO stand_pictures(stand_id, picture) VALUES('0C:F3:EE:04:19:3E', 'https://user-images.githubusercontent.com/7771294/68998873-adaaf400-0896-11ea-9fb7-420463365810.jpg');
INSERT INTO stand_pictures(stand_id, picture) VALUES('0C:F3:EE:04:18:AC', 'https://user-images.githubusercontent.com/7771294/68998872-adaaf400-0896-11ea-8ccf-2a7efb2b476e.jpeg');
INSERT INTO stand_pictures(stand_id, picture) VALUES('0C:F3:EE:04:18:AC', 'https://user-images.githubusercontent.com/7771294/68998870-adaaf400-0896-11ea-89ca-0a35dc0a5135.jpg');
INSERT INTO stand_pictures(stand_id, picture) VALUES('0C:F3:EE:04:18:AC', 'https://user-images.githubusercontent.com/7771294/68998869-adaaf400-0896-11ea-9608-26bf23632411.jpeg');

INSERT INTO device_proximity(device_id, stand_id, distance, update_time) VALUES('device_1', '0C:F3:EE:08:FC:DD', 0.5, CURRENT_TIMESTAMP);
INSERT INTO device_proximity(device_id, stand_id, distance, update_time) VALUES('device_2', '0C:F3:EE:04:19:2F', 0.5, CURRENT_TIMESTAMP);
INSERT INTO device_proximity(device_id, stand_id, distance, update_time) VALUES('device_3', '0C:F3:EE:04:19:2F', 0.5, CURRENT_TIMESTAMP);
INSERT INTO device_proximity(device_id, stand_id, distance, update_time) VALUES('device_4', '0C:F3:EE:04:19:2F', 0.5, CURRENT_TIMESTAMP);
INSERT INTO device_proximity(device_id, stand_id, distance, update_time) VALUES('device_5', '0C:F3:EE:04:19:2F', 0.5, CURRENT_TIMESTAMP);
INSERT INTO device_proximity(device_id, stand_id, distance, update_time) VALUES('device_6', '0C:F3:EE:08:FC:DD', 0.5, CURRENT_TIMESTAMP);
INSERT INTO device_proximity(device_id, stand_id, distance, update_time) VALUES('device_7', '0C:F3:EE:04:19:8E', 0.5, CURRENT_TIMESTAMP);
INSERT INTO device_proximity(device_id, stand_id, distance, update_time) VALUES('device_8', '0C:F3:EE:04:19:8E', 0.5, CURRENT_TIMESTAMP);
INSERT INTO device_proximity(device_id, stand_id, distance, update_time) VALUES('device_9', '0C:F3:EE:08:FC:DD', 0.5, CURRENT_TIMESTAMP);
INSERT INTO device_proximity(device_id, stand_id, distance, update_time) VALUES('device_10', '0C:F3:EE:04:19:3E', 0.5, CURRENT_TIMESTAMP);
INSERT INTO device_proximity(device_id, stand_id, distance, update_time) VALUES('device_11', '0C:F3:EE:04:19:3E', 0.5, CURRENT_TIMESTAMP);
INSERT INTO device_proximity(device_id, stand_id, distance, update_time) VALUES('device_12', '0C:F3:EE:04:19:3E', 0.5, CURRENT_TIMESTAMP);

INSERT INTO expo_hours(id, start, finish) VALUES (1, '8:00:00', '10:00:00');
INSERT INTO expo_hours(id, start, finish) VALUES (2, '10:00:00', '12:00:00');
INSERT INTO expo_hours(id, start, finish) VALUES (3, '12:00:00', '14:00:00');
INSERT INTO expo_hours(id, start, finish) VALUES (4, '14:00:00', '16:00:00');
INSERT INTO expo_hours(id, start, finish) VALUES (5, '16:00:00', '18:00:00');
INSERT INTO expo_hours(id, start, finish) VALUES (6, '18:00:00', '20:00:00');

INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:08:FC:DD', 1, 2);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:08:FC:DD', 2, 10);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:08:FC:DD', 3, 20);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:08:FC:DD', 4, 5);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:08:FC:DD', 5, 5);
INSERT INTO stand_visit_hours(stand_id, expo_hours_id, visits) VALUES('0C:F3:EE:08:FC:DD', 6, 20);
