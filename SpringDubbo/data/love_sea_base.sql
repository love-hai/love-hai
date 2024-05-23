create table if not exists tb_user
(
    id        bigint(64)   not null
        primary key,
    user_name varchar(20)  null,
    password  varchar(100) null
);
create table if not exists ls_hero
(
    id        bigint(64)   not null
        primary key,
    user_name varchar(20)  null,
    sex       varchar(10)  null,
    remark    varchar(100) null
);

