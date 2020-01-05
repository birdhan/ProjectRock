--建表语句，此仅为参考，开发人员可根据需要自行修改脚本
create table ARTICLE (
	ID varchar(36) primary key,
	NAME VARCHAR,
	AUTHOR VARCHAR,
	CREATETIME VARCHAR(50),
	COVER VARCHAR(1000),
	ISSHOW VARCHAR(50),
	CONTENTVALUE TEXT
);

--创建索引
create index ARTICLE_NAME on ARTICLE (NAME);
create index ARTICLE_AUTHOR on ARTICLE (AUTHOR);
create index ARTICLE_CREATETIME on ARTICLE (CREATETIME);
create index ARTICLE_COVER on ARTICLE (COVER);
create index ARTICLE_ISSHOW on ARTICLE (ISSHOW);
create index ARTICLE_CONTENTVALUE on ARTICLE (CONTENTVALUE);

--创建注释
comment on column ARTICLE.ID is '主键';
comment on column ARTICLE.NAME is '文章标题';
comment on column ARTICLE.AUTHOR is '作者';
comment on column ARTICLE.CREATETIME is '创建时间';
comment on column ARTICLE.COVER is '封面图片';
comment on column ARTICLE.ISSHOW is '是否显示';
comment on column ARTICLE.CONTENTVALUE is '内容';
