create table USERS(
	id int,
	name varchar (20),
	apellido varchar (20),
	phone varchar (9),
	city varchar (20),
	camiseta varchar (3),
	Nacionalidad varchar (20)
)

insert into USERS (id,name,apellido,phone,city,camiseta,Nacionalidad)
values (1, 'Fernando', 'Carrión', '933896865', 'Madrid', '10', 'Perú');
insert into USERS (id,name,apellido,phone,city,camiseta,Nacionalidad)
VALUES (2, 'Paolo', 'Guerrero', '983938332', 'Brasil', '9', 'Perú');
insert into USERS (id,name,apellido,phone,city,camiseta,Nacionalidad)
VALUES (3,'Cristiano', 'Ronaldo', '984755251', 'Manchester', '7', 'Portugal');

select * from Users where name='Cristiano'
select * from Users where name='Fernando'
select * from users


update users set name='Paolo' where apellido= 'Guerrero'
update users set city='Madrid' where apellido= 'Ronaldo'
update users set city='Miami' where apellido= 'Carrión'


show databases;

/*CREATE PROCEDURE*/

create procedure listarUserPerId(in p_id INT)
begin
	select * from users where id = p_id;
END

call listarUserPerId(1);