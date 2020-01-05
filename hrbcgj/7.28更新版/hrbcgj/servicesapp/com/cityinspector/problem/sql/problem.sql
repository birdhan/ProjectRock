--建表语句，此仅为参考，开发人员可根据需要自行修改脚本
create table service_problem (
	ID varchar(36) primary key,
	USERNAME VARCHAR(50),
	PHONE VARCHAR(50),
	TITLE VARCHAR(50),
	CONTENT VARCHAR(50),
	CATEGORY VARCHAR(50),
	TIME VARCHAR(50),
	STATUS VARCHAR(50),
	REPLY VARCHAR(50),
	RESPONSETIME VARCHAR(50)
);

--创建索引
create index service_problem_USERNAME on service_problem (USERNAME);
create index service_problem_PHONE on service_problem (PHONE);
create index service_problem_TITLE on service_problem (TITLE);
create index service_problem_CONTENT on service_problem (CONTENT);
create index service_problem_CATEGORY on service_problem (CATEGORY);
create index service_problem_TIME on service_problem (TIME);
create index service_problem_STATUS on service_problem (STATUS);
create index service_problem_REPLY on service_problem (REPLY);
create index service_problem_RESPONSETIME on service_problem (RESPONSETIME);

--创建注释
comment on column service_problem.ID is '主键';
comment on column service_problem.USERNAME is '用户名';
comment on column service_problem.PHONE is '联系电话';
comment on column service_problem.TITLE is '标题';
comment on column service_problem.CONTENT is '申请内容';
comment on column service_problem.CATEGORY is '类别';
comment on column service_problem.TIME is '申请时间';
comment on column service_problem.STATUS is '状态';
comment on column service_problem.REPLY is '回复内容';
comment on column service_problem.RESPONSETIME is '回复时间';
