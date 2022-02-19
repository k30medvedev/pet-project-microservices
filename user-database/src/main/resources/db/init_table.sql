DROP TABLE IF EXISTS public.users;
create table if not exists users
(
    user_id varchar(255) not null
        constraint pk_users
            primary key,
    name varchar(250) not null
        constraint users_name_key
            unique,
    email varchar(250) not null
        constraint users_email_key
            unique,
    password varchar(250) not null,
    rating double precision,
    created_at timestamp,
    updated_at timestamp,
    active  boolean,
    activation_key varchar(255)
);

alter table users
    owner to "user";

DROP TABLE IF EXISTS public.roles;
create table if not exists roles
(
    id varchar(255) not null
        constraint roles_pk
            primary key,
    name varchar(255) not null
);

alter table roles
    owner to "user";

DROP TABLE IF EXISTS public.users_roles;
create table if not exists users_roles
(
    user_id varchar(255) not null
        constraint users_roles_users_user_id_fk
            references users
            on update cascade on delete cascade,
    role_id integer not null
        constraint users_roles_roles_id_fk
            references roles
);

create table if not exists permissions
(
    id   varchar(255) not null
        constraint permissions_pk
            primary key,
    name varchar(250)
);
create table if not exists roles_permissions
(
    role_id       varchar(255) not null
        constraint roles_permissions_roles_id_fk
            references roles,
    permission_id varchar(255)
        constraint roles_permissions_permissions_id_fk
            references permissions
);

