--建表语句，此仅为参考，开发人员可根据需要自行修改脚本
create table ONLINESERVICE (
	ID varchar(36) primary key,
	TITLE VARCHAR(100),
	LINKURL VARCHAR(1000),
	CREATETIME TIMESTAMP
);

--创建索引
create index ONLINESERVICE_TITLE on ONLINESERVICE (TITLE);
create index ONLINESERVICE_LINKURL on ONLINESERVICE (LINKURL);
create index ONLINESERVICE_CREATETIME on ONLINESERVICE (CREATETIME);

--创建注释
comment on column ONLINESERVICE.ID is '主键';
comment on column ONLINESERVICE.TITLE is '标题';
comment on column ONLINESERVICE.LINKURL is '链接地址';
comment on column ONLINESERVICE.CREATETIME is '创建时间';
