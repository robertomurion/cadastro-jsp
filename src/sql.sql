CREATE DATABASE "curso-jsp"
WITH OWNER = postgres
ENCODING = 'UTF8'
TABLESPACE = pg_default
CONNECTION LIMIT = -1;



CREATE SEQUENCE usersequence
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

ALTER TABLE usersequence
OWNER TO postgres;


CREATE TABLE usuario
(
id bigint NOT NULL DEFAULT nextval('usersequence'::regclass),
login character VARYING(500),
senha character VARYING(20),
CONSTRAINT usuario_pkey PRIMARY KEY (id)
)
WITH(
OIDS=FALSE
);

ALTER TABLE usuario
OWNER TO postgres;



ALTER TABLE usuario ADD COLUMN nome character varying(500);

INSERT INTO usuario(
id,login,senha)
VALUES(1,'alex', 'alex');

INSERT INTO usuario(
id,login,senha)
VALUES(2,'admin', 'admin');


ALTER TABLE usuario ADD COLUMN fone character varying(500);


CREATE SEQUENCE productsequence
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;


CREATE TABLE produto
(
id bigint NOT NULL DEFAULT nextval('productsequence'::regclass),
nome character varying(500),
quantidade float,
valor float,
CONSTRAINT usuario_pkey PRIMARY KEY (id)
)
WITH(
OIDS=FALSE
);


INSERT INTO produto(
id, nome,quantidade,valor)
VALUES(1,'notebook', 5.0, 6790.0);


ALTER TABLE usuario ADD COLUMN cep character varying(500),
ADD COLUMN rua character varying(500),
ADD COLUMN bairro character varying(500),
ADD COLUMN cidade character varying(500),
ADD COLUMN estado character varying(500),
ADD COLUMN ibge character varying(500);

ALTER TABLE usuario ADD COLUMN momento timestamp without time zone NOT NULL
   DEFAULT (current_timestamp AT TIME ZONE 'UTC');
   
   
CREATE SEQUENCE phonesequence
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;


CREATE TABLE telefone
(
id bigint NOT NULL DEFAULT nextval('phonesequence'::regclass),
numero character VARYING(20),
tipo character VARYING(20),
usuario bigint NOT NULL,
CONSTRAINT fone_pkey PRIMARY KEY (id)
)
WITH(
OIDS=FALSE
);

ALTER TABLE public.usuario ADD COLUMN fotobase64 text;

ALTER TABLE public.usuario ADD COLUMN contenttype character varying;

ALTER TABLE public.usuario ADD COLUMN fotobase64miniatura text;

ALTER TABLE public.usuario ADD COLUMN ativo boolean;

CREATE SEQUENCE categorysequence
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

CREATE TABLE public.categoria
(
  idcategoria bigint NOT NULL DEFAULT nextval('categorysequence'::regclass),
  categoria character varying(50),
  CONSTRAINT categoria_pkey PRIMARY KEY (idcategoria)
)
WITH (
  OIDS=FALSE
);

 ALTER TABLE public.produto ADD COLUMN idcategoria bigint;
 
 
ALTER TABLE produto ADD constraint categoria_fk foreign key (idcategoria)
references categoria (idcategoria);

