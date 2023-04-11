create table patients(

     id bigint not null auto_increment,
     name varchar(100) not null,
     email varchar(100) not null unique,
     telephone varchar(20) not null,
     cpf varchar(14) not null unique,
     street varchar(100) not null,
     neighborhood varchar(100) not null,
     zip_code varchar(9) not null,
     city varchar(100) not null,
     property_number varchar(20),
     uf char(2),
     complement varchar(100),

     primary key(id)
);