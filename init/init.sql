CREATE DATABASE gatewatdb
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;
GRANT ALL PRIVILEGES ON DATABASE gatewatdb TO postgres;
\c gatewatdb


create sequence sq_route_id start 1 increment 1;

CREATE TABLE public.route (
    id numeric(10) NOT NULL,
    path_destination varchar(255),
    origin_ip varchar(255),
    max_request_per_second varchar(255),
    CONSTRAINT route_pk PRIMARY KEY (id)
);

CREATE INDEX route_idx01 ON public.route USING btree (path_destination);
CREATE INDEX route_idx02 ON public.route USING btree (origin_ip);
