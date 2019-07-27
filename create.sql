drop database if exists `spring_dict`;
create database `spring_dict`;

use `spring_dict`;
drop table if exists `account`;
create table `account` (
	`id` int not null auto_increment,
    `username` varchar(255) not null,
    `password` varchar(255) not null,
    `role` varchar(10) not null,
    primary key(`id`)
);

drop table if exists `word`;
create table `word` (
	`id` int not null auto_increment,
    `word_key` nvarchar(255) not null,
    `meanings` nvarchar(255) not null,
    `trans_type` int not null,
    primary key(`id`)
);

drop table if exists `type_detail`;
create table `type_detail` (
	`trans_type` int not null,
    `type_value` varchar(10) not null,
    primary key(`type`)
);
