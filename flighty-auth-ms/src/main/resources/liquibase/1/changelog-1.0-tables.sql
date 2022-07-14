CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE public.app_user
(
    id        BIGINT PRIMARY KEY,
    user_id   UUID DEFAULT uuid_generate_v1(),
    email     VARCHAR(25)  NOT NULL UNIQUE,
    password  VARCHAR(255) NOT NULL,
    full_name VARCHAR(50)  NOT NULL,
    status    VARCHAR(10)  NOT NULL
);

CREATE TABLE public.role
(
    id      BIGINT PRIMARY KEY,
    role_id UUID DEFAULT uuid_generate_v1(),
    name    VARCHAR(255) NOT NULL
);

CREATE TABLE public.user_role
(
    id      BIGINT PRIMARY KEY,
    user_id BIGINT REFERENCES public.app_user (id),
    role_id BIGINT REFERENCES public.role (id)
);