CREATE TABLE rides (

                       id BIGSERIAL PRIMARY KEY,

                       rider_id BIGINT NOT NULL,
                       driver_id BIGINT,

                       pickup_latitude DOUBLE PRECISION NOT NULL,
                       pickup_longitude DOUBLE PRECISION NOT NULL,

                       drop_latitude DOUBLE PRECISION NOT NULL,
                       drop_longitude DOUBLE PRECISION NOT NULL,

                       pickup_address VARCHAR(255) NOT NULL,
                       drop_address VARCHAR(255) NOT NULL,

                       ride_status VARCHAR(30) NOT NULL,
                       ride_type VARCHAR(30) NOT NULL,
                       payment_status VARCHAR(30) NOT NULL,

                       estimated_fare DECIMAL(10,2) NOT NULL,
                       actual_fare DECIMAL(10,2),

                       requested_at TIMESTAMP,
                       accepted_at TIMESTAMP,
                       started_at TIMESTAMP,
                       completed_at TIMESTAMP,

                       created_at TIMESTAMP NOT NULL,
                       updated_at TIMESTAMP
);