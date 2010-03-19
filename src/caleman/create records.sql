CREATE TABLE records (
        record_id int NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
        name varchar(255),
        text varchar(1000),
        type int,
        start_time timestamp,
        end_time timestamp,
        notify_time  timestamp,
        user_id  int,
        FOREIGN KEY (user_id) REFERENCES users (user_id)
)
