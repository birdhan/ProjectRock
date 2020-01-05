--建表语句，此仅为参考，开发人员可根据需要自行修改脚本
create table SCHEDULE (
	ID VARCHAR2(36) primary key,
	CLASSTYPE VARCHAR2(200),
	FREQUENCY VARCHAR2(50),
	STATUS VARCHAR2(50),
	REMARK VARCHAR2(250)
);

--创建索引
create index SCHEDULE_CLASSTYPE on SCHEDULE (CLASSTYPE);
create index SCHEDULE_FREQUENCY on SCHEDULE (FREQUENCY);
create index SCHEDULE_STATUS on SCHEDULE (STATUS);
create index SCHEDULE_REMARK on SCHEDULE (REMARK);

--创建注释
comment on column SCHEDULE.ID is '主键';
comment on column SCHEDULE.CLASSTYPE is '执行的类';
comment on column SCHEDULE.FREQUENCY is '执行频率';
comment on column SCHEDULE.STATUS is '状态';
comment on column SCHEDULE.REMARK is '备注';
