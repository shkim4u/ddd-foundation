use shop;

CREATE TABLE inventory
(
    id         VARCHAR(255) NOT NULL,
    product_id VARCHAR(255) NULL,
    price      INT          NULL,
    quantity   INT          NULL,
    CONSTRAINT pk_inventory PRIMARY KEY (id)
);
