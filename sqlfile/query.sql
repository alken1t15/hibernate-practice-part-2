CREATE TABLE categ(
    id SERIAL PRIMARY KEY ,
    name varchar(255)
);
CREATE TABLE option(
    id SERIAL PRIMARY KEY ,
    category_id INT,
    name VARCHAR(255),
    FOREIGN KEY (category_id) REFERENCES categ (id)
)