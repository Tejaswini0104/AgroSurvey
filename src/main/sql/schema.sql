create table if not exists user (
    uname varchar(100) PRIMARY KEY,
    pwd text
);

create table if not exists form (
    uname varchar(100),
    id SERIAL PRIMARY KEY,
    fields text[]
);

create table if not exists responses (
    uname varchar(100),
    form_id integer,
    answers text[]
);
