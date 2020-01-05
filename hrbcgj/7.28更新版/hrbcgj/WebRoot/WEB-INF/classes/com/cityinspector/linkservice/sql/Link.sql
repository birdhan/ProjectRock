--建表语句，此仅为参考，开发人员可根据需要自行修改脚本
create table SERVICE_LINK (
	ID varchar(36) primary key,
	LINKNAME VARCHAR(50),
	LINKURL VARCHAR(1000),
	LOGOPIC VARCHAR(100)
);

--创建索引
create index SERVICE_LINK_LINKNAME on SERVICE_LINK (LINKNAME);
create index SERVICE_LINK_LINKURL on SERVICE_LINK (LINKURL);
create index SERVICE_LINK_LOGOPIC on SERVICE_LINK (LOGOPIC);

--创建注释
comment on column SERVICE_LINK.ID is '主键';
comment on column SERVICE_LINK.LINKNAME is '链接名称';
comment on column SERVICE_LINK.LINKURL is '链接地址';
comment on column SERVICE_LINK.LOGOPIC is '链接图标';
