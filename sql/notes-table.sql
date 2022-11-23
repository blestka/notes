create schema notes;
use notes;
create table notes
(
    id           varchar(36) not null primary key,
    text         varchar(256),
    expired_at   datetime,
    password     varchar(100),
    created_date datetime    not null default now()
)
