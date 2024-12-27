use shop;

CREATE TABLE inventory
(
    id         VARCHAR(255) NOT NULL,
    product_id VARCHAR(255) NULL,
    quantity   INT          NULL,
    price      INT          NULL,
    CONSTRAINT pk_inventory PRIMARY KEY (id)
);
