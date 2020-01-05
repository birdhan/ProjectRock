--建表语句，此仅为参考，开发人员可根据需要自行修改脚本
create table SERVICE_TOPIC (
	ID varchar(36) primary key,
	TITLE VARCHAR(50),
	CONTENTVALUE TEXT,
	CREATETIME TIMESTAMP,
	ISSHOW VARCHAR(50)
);

--创建索引
create index SERVICE_TOPIC_TITLE on SERVICE_TOPIC (TITLE);
create index SERVICE_TOPIC_CONTENTVALUE on SERVICE_TOPIC (CONTENTVALUE);
create index SERVICE_TOPIC_CREATETIME on SERVICE_TOPIC (CREATETIME);
create index SERVICE_TOPIC_ISSHOW on SERVICE_TOPIC (ISSHOW);

--创建注释
comment on column SERVICE_TOPIC.ID is '主键';
comment on column SERVICE_TOPIC.TITLE is '话题标题';
comment on column SERVICE_TOPIC.CONTENTVALUE is '话题内容';
comment on column SERVICE_TOPIC.CREATETIME is '创建时间';
comment on column SERVICE_TOPIC.ISSHOW is '是否公开';
