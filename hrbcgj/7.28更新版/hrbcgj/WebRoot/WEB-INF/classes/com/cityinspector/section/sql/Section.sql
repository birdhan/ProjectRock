--建表语句，此仅为参考，开发人员可根据需要自行修改脚本
create table SECTION (
	ID varchar(36) primary key,
	NAME VARCHAR(50),
	POSTION VARCHAR(50),
	ISSHOW VARCHAR,
	SORTNUM INT
);

--创建索引
create index SECTION_NAME on SECTION (NAME);
create index SECTION_POSTION on SECTION (POSTION);
create index SECTION_ISSHOW on SECTION (ISSHOW);
create index SECTION_SORTNUM on SECTION (SORTNUM);

--创建注释
comment on column SECTION.ID is '主键';
comment on column SECTION.NAME is '栏目名称';
comment on column SECTION.POSTION is '位置';
comment on column SECTION.ISSHOW is '是否显示';
comment on column SECTION.SORTNUM is '排序顺序';
