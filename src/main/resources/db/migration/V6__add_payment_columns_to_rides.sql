ALTER TABLE rides
    ADD COLUMN payment_id BIGINT;

ALTER TABLE rides
    ADD COLUMN transaction_id VARCHAR(100);
