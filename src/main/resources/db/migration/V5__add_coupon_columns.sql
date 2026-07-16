ALTER TABLE coupons
    ADD COLUMN one_time_use BOOLEAN DEFAULT FALSE;

UPDATE coupons
SET one_time_use = FALSE
WHERE one_time_use IS NULL;

ALTER TABLE coupons
    ALTER COLUMN one_time_use SET NOT NULL;