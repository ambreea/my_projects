use assignment_db_final;

insert into utilizator (prenume, nume, gen, data_nasterii, locul_nasterii, nume_utilizator, parola, email, telefon, data_inregistrarii, profil) values ('Ana','Ion','F','1985-05-29','Constanta','Ana29','Ana@29','anaion@gmail.com','07056980','2019-09-12','Happy'),('Petre','Ion','M','1987-12-01','Constanta','PetreIon','Petrisor87','petreion@gmail.com','07056260','2019-09-12','Happy'),('Iris','Anghel','F','1975-06-05','Alba Iulia','Iris','floaredeiris','irisanghel@gmail.com','0756895698','2010-05-01','Travelling...'),('Razvan','Gheorghe','M','1988-10-15','Baia Mare','Razvan','Razvan@1234','razvangheorghe@gmail.com','0758967412','2005-01-12','La meci');

insert into prieteni (utilizator_id1, utilizator_id2, stare_prietenie, data_prietenie) values ('1','2','Acceptat','2019-09-12 15:14:26'),('1','3','In asteptare','2019-09-12 15:14:26'),('2','4','Acceptat','2019-09-12 15:14:26'),('3','4','Acceptat','2010-09-12 21:14:26');

insert into postare (utilizator_id,titlu,mesaj,data_postare) values ('1', 'Oscar Wilde','Imprumuta intotdeauna bani de la un pesimist. Nu se va astepta niciodata sa ii idai inapoi','2021-02-07'),('1', 'Despre multumire','Sa multumesti pe toata lumea e imposibil, dar sa-i enervezi pe toti e floare la ureche', '2021-01-05'),('2', 'Your shoes','You have to do what is right for you...No one walks in your shoes.', '2021-03-05');

select u1.nume as 'utilizator nume', u1.prenume as 'utilizator prenume', u2.nume as 'prieten nume', u2.prenume as 'prieten prenume', p.stare_prietenie
from `prieteni` p
left join `utilizator` u1 on u1.utilizator_id = p.utilizator_id1
left join `utilizator` u2 on u2.utilizator_id = p.utilizator_id2;




