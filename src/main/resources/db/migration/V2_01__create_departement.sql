CREATE table departement(
    id int8 not null,
    code varchar(225) not null,
    designation varchar(225) not null,
    constraint departement_pk primary key (id),
    constraint departement_uk unique (code)
);
CREATE SEQUENCE departement_id_seq minvalue 0 start with 1 increment by 50;