--建表语句，此仅为参考，开发人员可根据需要自行修改脚本
create table VOTE (
	ID varchar(36) primary key,
	TITLE VARCHAR(50),
	CONTENT VARCHAR(50),
	STARTTIME VARCHAR(50),
	ENDTIME VARCHAR(50),
	IF_FINISH VARCHAR(50),
	TYPE VARCHAR(50)
);

--创建索引
create index VOTE_TITLE on VOTE (TITLE);
create index VOTE_CONTENT on VOTE (CONTENT);
create index VOTE_STARTTIME on VOTE (STARTTIME);
create index VOTE_ENDTIME on VOTE (ENDTIME);
create index VOTE_IF_FINISH on VOTE (IF_FINISH);
create index VOTE_TYPE on VOTE (TYPE);

--创建注释
comment on column VOTE.ID is '主键';
comment on column VOTE.TITLE is '标题';
comment on column VOTE.CONTENT is '内容';
comment on column VOTE.STARTTIME is '开始时间';
comment on column VOTE.ENDTIME is '结束时间';
comment on column VOTE.IF_FINISH is '是否完成';
comment on column VOTE.TYPE is '类型';
