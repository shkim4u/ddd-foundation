use shop;

CREATE TABLE inventory
(
    id         VARCHAR(64) NOT NULL,
    product_id VARCHAR(64) NULL,
    quantity   INT          NULL,
    price      INT          NULL,
    CONSTRAINT pk_inventory PRIMARY KEY (id)
);

CREATE INDEX idx_product_id ON inventory (product_id);
