create TABLE personne (
    id int8 not null,
    nom varchar(255) not null,
    prenoms varchar(225),
    age int,
    login VARCHAR(255) not null,
    constraint personne_pk primary key (id),
    constraint personne_uk  unique (login)

);

CREATE SEQUENCE personne_id_seq MINVALUE 0 START WITH 1 INCREMENT BY 50;
