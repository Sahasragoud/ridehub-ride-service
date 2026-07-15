ALTER TABLE rides
    ADD COLUMN coupon_code VARCHAR(50);

ALTER TABLE rides
    ADD COLUMN discount_applied DECIMAL(10,2);