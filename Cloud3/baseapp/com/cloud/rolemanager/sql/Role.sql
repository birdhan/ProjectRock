--建表语句，此仅为参考，开发人员可根据需要自行修改脚本
create table ROLE (
	ID VARCHAR2(36) primary key,
	NAME VARCHAR2(50)
);

--创建索引
create index ROLE_NAME on ROLE (NAME);

--创建注释
comment on column ROLE.ID is '主键';
comment on column ROLE.NAME is '名称';
