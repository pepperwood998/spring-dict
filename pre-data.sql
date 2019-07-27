
use `spring_dict`;

truncate `account`;
insert into `account` (`username`,`password`,`role`) values ('admin','admin','ADM');
insert into `account` (`username`,`password`,`role`) values ('user1','123','USR');
insert into `account` (`username`,`password`,`role`) values ('user2','xyz','USR');

truncate `word`;
insert into `word` (`key`,`meanings`,`type`) values ('phone','điện thoại',0);
insert into `word` (`key`,`meanings`,`type`) values ('computer','máy tính',0);
insert into `word` (`key`,`meanings`,`type`) values ('mouse','chuột | chuột máy tính',0);
insert into `word` (`key`,`meanings`,`type`) values ('mountain','ngọn núi',0);
insert into `word` (`key`,`meanings`,`type`) values ('drink','đồ uống | uống',0);
insert into `word` (`key`,`meanings`,`type`) values ('em bé','baby',1);
insert into `word` (`key`,`meanings`,`type`) values ('con trai','boy | pearl',1);
insert into `word` (`key`,`meanings`,`type`) values ('lớp học','class',1);
