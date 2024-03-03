use assignment_db_final;

-- show index from utilizator;
-- show index from prieteni;
-- show index from postare;

/*select nume, prenume from utilizator
where nume = 'ion';*/

/*select * from postare
where match(mesaj) against ('mu*' in boolean mode);*/

/*create or replace view dateUtilizator as
select prenume, nume, data_nasterii, locul_nasterii from utilizator;*/

-- select * from dateUtilizator;

/*delimiter //
create procedure introducere_utilizator(new_prenume varchar(45), new_nume varchar(45), new_gen enum('M','F'), new_data_nasterii date, new_locul_nasterii varchar(45), new_nume_utilizator varchar(45), new_parola varchar(30), new_email varchar(45), new_telefon varchar(15), new_data_inregistrarii datetime)
begin
insert into utilizator (prenume, nume, gen, data_nasterii, locul_nasterii, nume_utilizator, parola, email, telefon, data_inregistrarii) values (new_prenume, new_nume, new_gen, new_data_nasterii, new_locul_nasterii, new_nume_utilizator, new_parola, new_email, new_telefon, new_data_inregistrarii);
end //
delimiter ;*/

-- call introducere_utilizator('Iris','Pop','F','1985-02-12','Iasi','floare-de-iris','iris55688','pop.iris@mail.com','0756125897','2008-08-12');

-- modificarea tuturor campurilor unui utilizator; 

/*delimiter //
create procedure modificare_utilizator(new_utilizator_id int, new_prenume varchar(45), new_nume varchar(45), new_gen enum('M','F',''), new_data_nasterii date, new_locul_nasterii varchar(45), new_nume_utilizator varchar(45), new_parola varchar(30), new_email varchar(45), new_telefon varchar(15), new_data_inregistrarii datetime, new_profil text, new_fotografie mediumblob, new_cv mediumblob)
begin

if new_prenume!='' then update utilizator set prenume=new_prenume where utilizator_id=new_utilizator_id; end if;
if new_nume!='' then update utilizator set nume=new_nume where utilizator_id=new_utilizator_id; end if;
if new_gen!='' then update utilizator set gen=new_gen where utilizator_id=new_utilizator_id; end if;
if new_data_nasterii!='0000-00-00' then update utilizator set data_nasterii=new_data_nasterii where utilizator_id=new_utilizator_id; end if;
if new_locul_nasterii!='' then update utilizator set locul_nasterii=new_locul_nasterii where utilizator_id=new_utilizator_id; end if;
if new_nume_utilizator!='' then update utilizator set nume_utilizator=new_nume_utilizator where utilizator_id=new_utilizator_id; end if;
if new_parola!='' then update utilizator set parola=new_parola where utilizator_id=new_utilizator_id; end if;
if new_email!='' then update utilizator set email=new_email where utilizator_id=new_utilizator_id; end if;
if new_telefon!='' then update utilizator set telefon=new_telefon where utilizator_id=new_utilizator_id; end if;
if new_data_inregistrarii!='0000-00-00 00:00:00' then update utilizator set data_inregistrarii=new_data_inregistrarii where utilizator_id=new_utilizator_id; end if;
if new_profil!='' then update utilizator set profil=new_profil where utilizator_id=new_utilizator_id; end if;
if new_fotografie!='' then update utilizator set fotografie=new_fotografie where utilizator_id=new_utilizator_id; end if;
if new_cv!='' then update utilizator set cv=new_cv where utilizator_id=new_utilizator_id; end if;
end //
delimiter ;*/

/*delimiter //
create procedure sterge_utilizator (new_utilizator_id int)
begin
delete from utilizator where utilizator_id=new_utilizator_id;
end //
delimiter ;*/

-- call sterge_utilizator(7);

/*delimiter //
create function nr_prieteni (new_utilizator_id int)
returns int deterministic
begin
set @count_prieteni1 = 0;
set @count_prieteni2 = 0;
select count(utilizator_id1) from prieteni where new_utilizator_id = utilizator_id1 into @count_prieteni1;
select count(utilizator_id2) from prieteni where new_utilizator_id = utilizator_id2 into @count_prieteni2;
return @count_prieteni1 + @count_prieteni2;
end //
delimiter ;*/

-- select nr_prieteni(3);






