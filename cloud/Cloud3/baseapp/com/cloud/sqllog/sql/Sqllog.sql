--建表语句，此仅为参考，开发人员可根据需要自行修改脚本
create table SQLLOG (
	ID VARCHAR2(36) primary key,
	FBEGINTIME TIMESTAMP,
	FENDTIME TIMESTAMP,
	FSQL VARCHAR2(2000),
	FSQLTYPE NUMBER,
	FPARAMETERS CLOB
);

--创建索引
create index SQLLOG_FBEGINTIME on SQLLOG (FBEGINTIME);
create index SQLLOG_FENDTIME on SQLLOG (FENDTIME);
create index SQLLOG_FSQL on SQLLOG (FSQL);
create index SQLLOG_FSQLTYPE on SQLLOG (FSQLTYPE);
create index SQLLOG_FPARAMETERS on SQLLOG (FPARAMETERS);

--创建注释
comment on column SQLLOG.ID is '主键';
comment on column SQLLOG.FBEGINTIME is '开始时间';
comment on column SQLLOG.FENDTIME is '结束时间';
comment on column SQLLOG.FSQL is 'SQL';
comment on column SQLLOG.FSQLTYPE is 'SQL类型';
comment on column SQLLOG.FPARAMETERS is '参数';
