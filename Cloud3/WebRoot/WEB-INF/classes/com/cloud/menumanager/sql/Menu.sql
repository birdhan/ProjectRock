--建表语句，此仅为参考，开发人员可根据需要自行修改脚本
create table MENU (
	ID VARCHAR2(36) primary key,
	MENUNAME VARCHAR2(20),
	MENUURL VARCHAR2(500),
	MENUTYPE VARCHAR2(2),
	PARENTID VARCHAR2(50),
	MENUSOR NUMBER
);

--创建索引
create index MENU_MENUNAME on MENU (MENUNAME);
create index MENU_MENUURL on MENU (MENUURL);
create index MENU_MENUTYPE on MENU (MENUTYPE);
create index MENU_PARENTID on MENU (PARENTID);
create index MENU_MENUSOR on MENU (MENUSOR);

--创建注释
comment on column MENU.ID is '主键';
comment on column MENU.MENUNAME is '菜单名称';
comment on column MENU.MENUURL is '菜单地址';
comment on column MENU.MENUTYPE is '菜单类型';
comment on column MENU.PARENTID is '父菜单';
comment on column MENU.MENUSOR is '菜单顺序';
