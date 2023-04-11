create table doctors(

     id bigint not null auto_increment,
     name varchar(100) not null,
     email varchar(100) not null unique,
     telephone varchar(20) not null,
     crm varchar(6) not null unique,
     specialty varchar(100) not null,
     street varchar(100) not null,
     neighborhood varchar(100) not null,
     zip_code varchar(9) not null,
     city varchar(100) not null,
     property_number varchar(20),
     uf char(2),
     complement varchar(100),

     primary key(id)
);