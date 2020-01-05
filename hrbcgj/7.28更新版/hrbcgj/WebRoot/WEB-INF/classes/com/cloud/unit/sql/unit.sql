--建表语句，此仅为参考，开发人员可根据需要自行修改脚本
create table UNIT (
	ID varchar(36) primary key,
	WEBURL VARCHAR(50),
	PICURL VARCHAR(50)
);

--创建索引
create index UNIT_WEBURL on UNIT (WEBURL);
create index UNIT_PICURL on UNIT (PICURL);

--创建注释
comment on column UNIT.ID is '主键';
comment on column UNIT.WEBURL is '网站的连接';
comment on column UNIT.PICURL is '图片的连接';
