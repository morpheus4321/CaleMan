CREATE TABLE users (
        user_id int NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
        name varchar(255)
)
