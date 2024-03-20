create database testvk;

create table user
(
    id         bigint auto_increment
        primary key,
    email      varchar(255) null,
    first_name varchar(255) null,
    last_name  varchar(255) null,
    password   varchar(255) null,
    role       varchar(255) null
);

create table audit_event
(
    id         bigint auto_increment
        primary key,
    url        varchar(255) null,
    parameters varchar(255) null,
    timestamp  datetime(6)  null,
    type       varchar(255) null,
    user_id    bigint       null,
    ip         varchar(255) null,
    method     varchar(255) null,
    constraint FKjoiwt5ttgvok6rie9r756thb5
        foreign key (user_id) references user (id)
);