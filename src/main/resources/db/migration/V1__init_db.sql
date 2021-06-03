CREATE TABLE hotel
(
    id bigserial PRIMARY KEY,
    status varchar(36) NOT NULL,
    address varchar(36) NOT NULL,
    created_time TIMESTAMP WITH TIME ZONE,
    updated_time TIMESTAMP WITH TIME ZONE,
    name varchar(36) NOT NULL,
    contact varchar(36) NOT NULL
)
