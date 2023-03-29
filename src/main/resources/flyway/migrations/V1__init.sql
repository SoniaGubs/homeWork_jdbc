CREATE TABLE passenger
(
    id              INT  AUTO_INCREMENT,
    name            VARCHAR(200),
    surname         VARCHAR(200),
    dob             VARCHAR(45),
    passport_number VARCHAR(45),
    PRIMARY KEY (id)
);

CREATE TABLE plane
(
    id    INT           AUTO_INCREMENT,
    model VARCHAR(200) ,
    PRIMARY KEY (id)
);

CREATE TABLE crew
(
    id        INT          NOT NULL AUTO_INCREMENT,
    full_name VARCHAR(200) NOT NULL,
    title     VARCHAR(45)  NOT NULL,
    plane_id  INT          NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (plane_id) REFERENCES plane (id)
);

CREATE TABLE city
(
    id      INT          AUTO_INCREMENT,
    city    VARCHAR(45) NOT NULL,
    country VARCHAR(45) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE route
(
    id                INT  AUTO_INCREMENT,
    departure_city_id INT ,
    arrival_city_id   INT,
    PRIMARY KEY (id),
    FOREIGN KEY (departure_city_id) REFERENCES city (id),
    FOREIGN KEY (arrival_city_id) REFERENCES city (id)
);

CREATE TABLE plane_route
(
    id       INT AUTO_INCREMENT,
    plane_id INT ,
    route_id INT ,
    PRIMARY KEY (id),
    FOREIGN KEY (plane_id) REFERENCES plane (id),
    FOREIGN KEY (route_id) REFERENCES route (id)
);


CREATE TABLE orders
(
    id                  INT           AUTO_INCREMENT,
    plane_route_id      INT          ,
    passenger_id        INT          ,
    departure_data_time VARCHAR(200) ,
    arrival_data_time   VARCHAR(200) ,
    status              VARCHAR(200) ,
    PRIMARY KEY (id),
    FOREIGN KEY (passenger_id) REFERENCES passenger (id),
    FOREIGN KEY (plane_route_id) REFERENCES plane_route (id)
);

CREATE TABLE payment
(
    id        INT NOT NULL AUTO_INCREMENT,
    orders_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (orders_id) REFERENCES orders (id)
);

CREATE TABLE ticket
(
    id        INT NOT NULL AUTO_INCREMENT,
    orders_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (orders_id) REFERENCES orders (id)
);