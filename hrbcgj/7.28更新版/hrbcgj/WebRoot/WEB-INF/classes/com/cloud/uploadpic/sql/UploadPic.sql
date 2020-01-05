--建表语句，此仅为参考，开发人员可根据需要自行修改脚本
create table UPLOADPIC (
	ID VARCHAR2(36) primary key,
	ORINAME VARCHAR2(300),
	SERNAME VARCHAR2(1000),
	SAVEPATH VARCHAR2(1000),
	UPLOADTIME TIMESTAMP,
	UPLOADUSERID VARCHAR2(100)
);

--创建索引
create index UPLOADPIC_ORINAME on UPLOADPIC (ORINAME);
create index UPLOADPIC_SERNAME on UPLOADPIC (SERNAME);
create index UPLOADPIC_SAVEPATH on UPLOADPIC (SAVEPATH);
create index UPLOADPIC_UPLOADTIME on UPLOADPIC (UPLOADTIME);
create index UPLOADPIC_UPLOADUSERID on UPLOADPIC (UPLOADUSERID);

--创建注释
comment on column UPLOADPIC.ID is '主键';
comment on column UPLOADPIC.ORINAME is '原文件名';
comment on column UPLOADPIC.SERNAME is '服务器文件名';
comment on column UPLOADPIC.SAVEPATH is '保存路径';
comment on column UPLOADPIC.UPLOADTIME is '上传时间';
comment on column UPLOADPIC.UPLOADUSERID is '上传人员';
