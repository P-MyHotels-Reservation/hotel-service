CREATE TABLE hotel.room_type
(
    id SERIAL PRIMARY KEY,
    room_type VARCHAR(255),
    price VARCHAR(255)
);

INSERT INTO hotel.room_type ("room_type", "price")
VALUES ('KING_SUPERIOR_ROOM', '100000'),
       ('QUEEN_SUPERIOR_ROOM', '70000'),
       ('SUPERIOR_TWIN_ROOM', '80000'),
       ('KING_LUXURY_ROOM', '75000'),
       ('QUEEN_LUXURY_ROOM', '60000'),
       ('LUXURY_TWIN_ROOM', '65000'),
       ('CLUB_SUITE', '77000'),
       ('DELUXE_ROOM', '50000');

CREATE TABLE hotel.room
(
    id              SERIAL PRIMARY KEY,
    hotel_id        BIGINT,
    name            VARCHAR(32),
    floor           VARCHAR(32),
    type_id         INTEGER,
    comments        VARCHAR(500),
    status          VARCHAR(64),
    updated_date    TIMESTAMP WITH TIME ZONE,
    created_date    TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (hotel_id) REFERENCES hotel(id),
    FOREIGN KEY (type_id) REFERENCES room_type(id)
);