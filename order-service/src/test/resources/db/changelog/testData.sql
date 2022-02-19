CREATE TABLE orders
(
    id                VARCHAR(255),
    user_id           text,
    status            varchar(255),
    start_geolocation text,
    stop_geolocation  text,
    start_delivery    TIME with time zone,
    end_delivery      TIME with time zone,
    operator_id       integer,
    PRIMARY KEY (id)
);