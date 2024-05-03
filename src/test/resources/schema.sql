create table users(
 id varchar(36) not null,
 firstname varchar(30),
 lastname varchar(30),
 user_email varchar(30) not null unique,
 password varchar(20) not null,
 primary key (id)
);
