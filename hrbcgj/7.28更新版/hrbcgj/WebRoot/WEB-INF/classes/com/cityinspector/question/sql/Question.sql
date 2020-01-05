--建表语句，此仅为参考，开发人员可根据需要自行修改脚本
create table QUESTION (
	ID varchar(36) primary key,
	NAME VARCHAR(50),
	CREATETIME TIMESTAMP,
	ANSWERCONTENT VARCHAR(1000)
);

--创建索引
create index QUESTION_NAME on QUESTION (NAME);
create index QUESTION_CREATETIME on QUESTION (CREATETIME);
create index QUESTION_ANSWERCONTENT on QUESTION (ANSWERCONTENT);

--创建注释
comment on column QUESTION.ID is '主键';
comment on column QUESTION.NAME is '问题名称';
comment on column QUESTION.CREATETIME is '创建时间';
comment on column QUESTION.ANSWERCONTENT is '解答内容';
