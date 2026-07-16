CREATE TABLE coupon_usage
(
    id BIGSERIAL PRIMARY KEY,

    rider_id BIGINT NOT NULL,

    coupon_id BIGINT NOT NULL,

    used_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_coupon_usage_coupon
        FOREIGN KEY(coupon_id)
            REFERENCES coupons(id)
);