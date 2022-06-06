drop table DOG if exists;

create table if not exists DOG (
  id int not null,
  nombre varchar(25) not null,
  fecha_nac DATETIME,
  raza varchar(10),
  peso int,
  tiene_chip int
);
