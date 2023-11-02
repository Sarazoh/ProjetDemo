ALTER TABLE personne
    ADD departement_id int8;

update personne set departement_id = (select id from departement where code = 'c001') where login ='zoh';
update personne set departement_id = (select id from departement where code = 'c001') where login ='julie';
update personne set departement_id = (select id from departement where code = 'c002') where login ='sara';
update personne set departement_id = (select id from departement where code = 'c002') where login ='douin';
update personne set departement_id = (select id from departement where code = 'c001') where login ='renaud';

ALTER TABLE personne alter column departement_id set not null;




