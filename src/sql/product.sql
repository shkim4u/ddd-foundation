CREATE TABLE product
(
    name       VARCHAR(255) NULL,
    price      INT NULL,
    detail     VARCHAR(255) NULL,
    product_id VARCHAR(5 char
) NOT NULL, CONSTRAINT pk_product PRIMARY KEY (product_id));

CREATE TABLE product_category
(
    product_product_id VARCHAR(5 char
) NOT NULL, category_id BIGINT NULL);

ALTER TABLE product_category
    ADD CONSTRAINT fk_product_category_on_product FOREIGN KEY (product_product_id) REFERENCES product (product_id);
