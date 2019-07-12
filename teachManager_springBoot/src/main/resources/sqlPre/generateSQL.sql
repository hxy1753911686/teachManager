drop database if exists  estimate
CREATE DATABASE estimate

create table permission
(
  id          int auto_increment comment '权限ID'
    primary key,
  p_name      varchar(20)  null comment '权限名称',
  parent_id   int          null comment '父ID，null或空则为菜单',
  p_level     int          not null comment '1,2,3',
  url         varchar(50)  null comment 'url',
  icon        varchar(20)  null comment '页面上显示的图标',
  html_id     varchar(20)  null comment 'html上的id，用来触发事件',
  data_id     int          not null,
  description varchar(200) null comment '权限描述',
  constraint permission_data_id_uindex
    unique (data_id)
)DEFAULT CHARSET=utf8
  comment '权限';


create table role
(
  id        int auto_increment comment '角色ID'
    primary key,
  role_code varchar(30) not null comment '角色名（与权限框架结合）',
  role_name varchar(30) not null comment '角色显示的名称'
)DEFAULT CHARSET=utf8;

create table role_permission
(
  id            int auto_increment
    primary key,
  role_id       int null,
  permission_id int null
)DEFAULT CHARSET=utf8;

create table student
(
  id       int default 0 not null comment '学生ID'
    primary key,
  name     varchar(20)   null comment '学生姓名',
  gender   int(1)        null comment '学生性别，1为男，0为女',
  accessID varchar(32)   null comment '登录标识码，即身份证号，和登录人对应',
  birth    date          null comment '出生日期',
  is_out   tinyint(1)    null comment '是否毕业或退学'
)DEFAULT CHARSET=utf8;

create table user_role
(
  id      int auto_increment
    primary key,
  user_id int null,
  role_id int null
)DEFAULT CHARSET=utf8;

create table users
(
  id              int auto_increment comment '用户ID
'
    primary key,
  username        varchar(50) not null comment '用户名',
  password        varchar(50) not null comment '用户密码（加密）',
  phone           varchar(11) null comment '手机号码',
  email           varchar(30) null comment '邮箱',
  create_time     datetime    null comment '创建时间',
  login_time      datetime    null comment '登录时间',
  last_login_time datetime    null comment '上次登录时间',
  count           int         null comment '登录次数'
)DEFAULT CHARSET=utf8;