use shop;

CREATE TABLE inventory
(
    quantity   INT          NULL,
    price      INT          NULL,
    id         VARCHAR(255) NOT NULL,
    product_id VARCHAR(255) NULL,
    CONSTRAINT pk_inventory PRIMARY KEY (id)
);
