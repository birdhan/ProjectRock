--建表语句，此仅为参考，开发人员可根据需要自行修改脚本
create table DATADICT (
	ID VARCHAR2(36) primary key,
	DICTVALUE VARCHAR2(100),
	DICTLABEL VARCHAR2(200),
	DICTTYPE VARCHAR2(50),
	MODULENAME VARCHAR2(50),
	PROPERTY VARCHAR2(50)
);

--创建索引
create index DATADICT_DICTVALUE on DATADICT (DICTVALUE);
create index DATADICT_DICTLABEL on DATADICT (DICTLABEL);
create index DATADICT_DICTTYPE on DATADICT (DICTTYPE);
create index DATADICT_MODULENAME on DATADICT (MODULENAME);
create index DATADICT_PROPERTY on DATADICT (PROPERTY);

--创建注释
comment on column DATADICT.ID is '主键';
comment on column DATADICT.DICTVALUE is '字典值';
comment on column DATADICT.DICTLABEL is '字典显示值';
comment on column DATADICT.DICTTYPE is '字典控件类型';
comment on column DATADICT.MODULENAME is '模块名称';
comment on column DATADICT.PROPERTY is '控件属性';
