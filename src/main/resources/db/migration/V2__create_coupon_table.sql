CREATE TABLE coupons
(
    id BIGSERIAL PRIMARY KEY,

    code VARCHAR(50) UNIQUE NOT NULL,

    type VARCHAR(20) NOT NULL,

    value DECIMAL(10,2) NOT NULL,

    minimum_ride_amount DECIMAL(10,2) NOT NULL,

    maximum_discount DECIMAL(10,2) NOT NULL,

    active BOOLEAN NOT NULL,

    expiry_date TIMESTAMP NOT NULL,

    created_at TIMESTAMP NOT NULL
);