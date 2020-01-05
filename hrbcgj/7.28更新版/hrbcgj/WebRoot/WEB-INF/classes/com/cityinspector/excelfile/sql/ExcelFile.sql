--建表语句，此仅为参考，开发人员可根据需要自行修改脚本
create table EXCELFILE (
	ID varchar(36) primary key,
	NAME VARCHAR(50),
	UPLOADTIME TIMESTAMP,
	ATTACHMENTFILEID VARCHAR(1000)
);

--创建索引
create index EXCELFILE_NAME on EXCELFILE (NAME);
create index EXCELFILE_UPLOADTIME on EXCELFILE (UPLOADTIME);
create index EXCELFILE_ATTACHMENTFILEID on EXCELFILE (ATTACHMENTFILEID);

--创建注释
comment on column EXCELFILE.ID is '主键';
comment on column EXCELFILE.NAME is '文件名称';
comment on column EXCELFILE.UPLOADTIME is '上传时间';
comment on column EXCELFILE.ATTACHMENTFILEID is '附件';
