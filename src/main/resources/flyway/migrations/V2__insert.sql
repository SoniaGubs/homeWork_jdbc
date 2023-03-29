INSERT INTO passenger (id, name, surname, dob, passport_number)
VALUES (1, 'Nikita', 'Komarov', '01.02.1990', '8795554412'),
       (2, 'Svetlana', 'Petrova', '21.04.1995', '7985465213'),
       (3, 'Alex', 'Old', '05.09.1997', '7552354685'),
       (4, 'Olga', 'Frolova', '15.04.1994', '4583546857');


INSERT INTO plane (id, model)
VALUES (1, 'boeing_777'),
       (2, 'boeing_720');

INSERT INTO crew(id, full_name, title, plane_id)
VALUES (1, 'Nik Petrov', 'pilot', 1),
       (2, 'Mary Fill', 'stewardess', 1),
       (3, 'Sara Rodger', 'stewardess', 1),
       (4, 'Tomas Gab', 'pilot', 2),
       (5, 'Samanta Dor', 'stewardess', 2),
       (6, 'Vera Kalina', 'stewardess', 2);


INSERT INTO city (id, city, country)
VALUES (1, 'Minsk', 'Belarus'),
       (2, 'Moscow', 'Russia'),
       (3, 'Paris', 'France'),
       (4, 'Berlin', 'Germany');

INSERT INTO route (id, departure_city_id, arrival_city_id)
VALUES (1, 2, 3),
       (2, 1, 4);

INSERT INTO plane_route (id, plane_id, route_id)
VALUES (1, 1, 2),
       (2, 2, 1);


INSERT INTO orders (id, plane_route_id, passenger_id, departure_data_time, arrival_data_time, status)
VALUES (1, 1, 1, '11.12.2023 08.30', '11.12.2023 10.50' ,'paid'),
       (2, 1, 2,'11.12.2023 08.30', '11.12.2023 10.50', 'unpaid'),
       (3, 2, 3, '12.12.2023 18.40','12.12.2023 21.10', 'paid');

INSERT INTO ticket (id, orders_id)
VALUES (1, 1),
       (2, 3);

INSERT INTO payment (id, orders_id)
VALUES (1, 1),
       (2, 3);
