create table student
(
  id       int default 0 not null comment '学生ID'
    primary key,
  name     varchar(20)   null comment '学生姓名',
  gender   int(1)        null comment '学生性别，1为男，0为女',
  accessID varchar(32)   null comment '登录标识码，即身份证号，和登录人对应',
  birth    date          null comment '出生日期',
  is_out   tinyint(1)    null comment '是否毕业或退学'
);