--建表语句，此仅为参考，开发人员可根据需要自行修改脚本
create table SPECIAL (
	ID varchar(36) primary key,
	WEBURL VARCHAR(50),
	PICURL VARCHAR(50)
);

--创建索引
create index SPECIAL_WEBURL on SPECIAL (WEBURL);
create index SPECIAL_PICURL on SPECIAL (PICURL);

--创建注释
comment on column SPECIAL.ID is '主键';
comment on column SPECIAL.WEBURL is '图片链接';
comment on column SPECIAL.PICURL is 'logo图片';
