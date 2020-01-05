--建表语句，此仅为参考，开发人员可根据需要自行修改脚本
create table GUIDE (
	ID varchar(36) primary key,
	TITLE VARCHAR(100),
	CREATETIME TIMESTAMP,
	DETAILCONTENT TEXT,
	SORTNUM INT
);

--创建索引
create index GUIDE_TITLE on GUIDE (TITLE);
create index GUIDE_CREATETIME on GUIDE (CREATETIME);
create index GUIDE_DETAILCONTENT on GUIDE (DETAILCONTENT);
create index GUIDE_SORTNUM on GUIDE (SORTNUM);

--创建注释
comment on column GUIDE.ID is '主键';
comment on column GUIDE.TITLE is '标题';
comment on column GUIDE.CREATETIME is '创建时间';
comment on column GUIDE.DETAILCONTENT is '详细内容';
comment on column GUIDE.SORTNUM is '排序顺序';
