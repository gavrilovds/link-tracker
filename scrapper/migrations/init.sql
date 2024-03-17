--liquibase formatted sql

--changeset gavrilovds:1
create sequence link_pk_seq start 1;
create sequence chat_link_pk_seq start 1;

create table if not exists chat
(
    id bigint not null primary key
);

create table if not exists link
(
    id              bigint not null default nextval('link_pk_seq') primary key,
    url             text   not null unique,
    type            text   not null,
    updated_at      timestamp with time zone,
    last_checked_at timestamp with time zone
);

create table if not exists chat_link
(
    id      bigint not null default nextval('chat_link_pk_seq') primary key,
    chat_id bigint not null unique references chat,
    link_id bigint not null unique references link
);
--rollback drop table chat, link, chat_link;
