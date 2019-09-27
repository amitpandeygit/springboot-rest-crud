# REST API based CRUD application using Spring Boot

A complete end-to-end CRUD example implemented in Spring Boot using REST APIs
Requires - PostgresSQL backend

## Step 1 - Set up Postgres Database

* If Postgres is not installed on your system, recommended installation instruction for Postgres Database - https://postgresapp.com/. Recommended graphical GUI/Client for Postgres is - pgAdmin4 (https://www.pgadmin.org/download/)
* Open Postgres, create a new schema, or within a existing schema of your choice, execute the following script (after replacing the **<owner_name>** - 
```
-- Table: public.people
-- DROP TABLE public.people;

CREATE TABLE public.people
(
    id bigint NOT NULL DEFAULT nextval('people_id_seq1'::regclass),
    first_name character varying(20) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(20) COLLATE pg_catalog."default" NOT NULL,
    age integer NOT NULL,
    CONSTRAINT people_pkey1 PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.people OWNER to <owner_name>;

GRANT ALL ON TABLE public.people TO <owner_name>;

-- In case if the "generated id" is not returned to the calling function on insert
GRANT ALL(id) ON public.people TO <owner_name>; 
```
