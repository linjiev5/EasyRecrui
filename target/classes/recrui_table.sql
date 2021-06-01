-- Project Name : noname
-- Date/Time    : 2021/04/08 13:28:39
-- Author       : user
-- RDBMS Type   : PostgreSQL
-- Application  : A5:SQL Mk-2

/*
  BackupToTempTable, RestoreFromTempTable疑似命令が付加されています。
  これにより、drop table, create table 後もデータが残ります。
  この機能は一時的に $$TableName のような一時テーブルを作成します。
*/

-- message_to_operation
--* RestoreFromTempTable
create table message_to_operation (
  message_id serial not null
  , uid integer not null
  , message character varying
  , read boolean
  , update_time timestamp
  , constraint message_to_operation_PKC primary key (message_id)
) ;

-- resume
--* RestoreFromTempTable
create table resume (
  resume_id serial not null
  , recrui_id integer not null
  , send_uid integer not null
  , recevie_uid integer not null
  , result character varying
  , update_time timestamp
  , constraint resume_PKC primary key (resume_id)
) ;

-- notice
--* RestoreFromTempTable
create table notice (
  notice_id bigserial not null
  , title character varying
  , notice text
  , update_time timestamp
  , constraint notice_PKC primary key (notice_id)
) ;

-- company_info
--* RestoreFromTempTable
create table company_info (
  uid serial not null
  , company_name character varying
  , mail character varying
  , tel character varying
  , info character varying
  , founded_time date
  , icon character varying
  , adress character varying
  , update_time timestamp
  , constraint company_info_PKC primary key (uid)
) ;

-- company_picture
--* RestoreFromTempTable
create table company_picture (
  pic_id serial not null
  , uid integer
  , picture character varying
  , constraint company_picture_PKC primary key (pic_id)
) ;

-- educational_background
--* RestoreFromTempTable
create table educational_background (
  educational_id serial not null
  , uid integer
  , start_time date
  , end_time date
  , school_name character varying
  , constraint educational_background_PKC primary key (educational_id)
) ;

-- recrui_info
--* RestoreFromTempTable
create table recrui_info (
  recrui_id serial not null
  , uid integer
  , employment_method character varying
  , category character varying
  , title character varying
  , salary character varying
  , welfare character varying
  , station character varying
  , job_details text
  , update_time TIMESTAMP
  , constraint recrui_info_PKC primary key (recrui_id)
) ;

-- resume_info
--* RestoreFromTempTable
create table resume_info (
  uid serial not null
  , icon character varying
  , nationality character varying
  , name_katakana character varying
  , nearest_station character varying
  , hobbies_and_skills character varying
  , licence_or_qualification character varying
  , motivation text
  , self_public_relations text
  , update_time timestamp(6) without time zone
  , constraint resume_info_PKC primary key (uid)
) ;

-- user_info
--* RestoreFromTempTable
create table user_info (
  uid serial not null
  , name character varying
  , icon character varying
  , mail character varying
  , tel character varying
  , sex integer
  , adress character varying
  , birthday date
  , update_time TIMESTAMP
  , constraint user_info_PKC primary key (uid)
) ;

-- user_login
--* RestoreFromTempTable
create table user_login (
  uid serial not null
  , user_name character varying not null
  , password character varying
  , rocked boolean
  , user_role character varying
  , create_time TIMESTAMP
  , update_time TIMESTAMP
  , constraint user_login_PKC primary key (uid)
) ;

-- work_experience
--* RestoreFromTempTable
create table work_experience (
  work_id serial not null
  , uid integer
  , start_time date
  , end_time date
  , conpany_name character varying
  , position character varying
  , constraint work_experience_PKC primary key (work_id)
) ;

comment on table message_to_operation is 'message_to_operation';
comment on column message_to_operation.message_id is 'message_id';
comment on column message_to_operation.uid is 'uid';
comment on column message_to_operation.message is 'message';
comment on column message_to_operation.read is 'read';
comment on column message_to_operation.update_time is 'update_time';

comment on table resume is 'resume';
comment on column resume.resume_id is 'resume_id';
comment on column resume.recrui_id is 'recrui_id';
comment on column resume.send_uid is 'send_uid';
comment on column resume.recevie_uid is 'recevie_uid';
comment on column resume.result is 'result';
comment on column resume.update_time is 'update_time';

comment on table notice is 'notice';
comment on column notice.notice_id is 'notice_id';
comment on column notice.title is 'title';
comment on column notice.notice is 'notice';
comment on column notice.update_time is 'update_time';

comment on table company_info is 'company_info';
comment on column company_info.uid is 'uid';
comment on column company_info.company_name is 'company_name';
comment on column company_info.mail is 'mail';
comment on column company_info.tel is 'tel';
comment on column company_info.info is 'info';
comment on column company_info.founded_time is 'founded_time';
comment on column company_info.icon is 'icon';
comment on column company_info.adress is 'adress';
comment on column company_info.update_time is 'update_time';

comment on table company_picture is 'company_picture';
comment on column company_picture.pic_id is 'pic_id';
comment on column company_picture.uid is 'uid';
comment on column company_picture.picture is 'picture';

comment on table educational_background is 'educational_background';
comment on column educational_background.educational_id is 'educational_id';
comment on column educational_background.uid is 'uid';
comment on column educational_background.start_time is 'start_time';
comment on column educational_background.end_time is 'end_time';
comment on column educational_background.school_name is 'school_name';

comment on table recrui_info is 'recrui_info';
comment on column recrui_info.recrui_id is 'recrui_id';
comment on column recrui_info.uid is 'uid';
comment on column recrui_info.employment_method is 'employment_method';
comment on column recrui_info.category is 'category';
comment on column recrui_info.title is 'title';
comment on column recrui_info.salary is 'salary';
comment on column recrui_info.welfare is 'welfare';
comment on column recrui_info.station is 'station';
comment on column recrui_info.job_details is 'job_details';
comment on column recrui_info.update_time is 'update_time';

comment on table resume_info is 'resume_info';
comment on column resume_info.uid is 'uid';
comment on column resume_info.icon is 'icon';
comment on column resume_info.nationality is 'nationality';
comment on column resume_info.name_katakana is 'name_katakana';
comment on column resume_info.nearest_station is 'nearest_station';
comment on column resume_info.hobbies_and_skills is 'hobbies_and_skills';
comment on column resume_info.licence_or_qualification is 'licence_or_qualification';
comment on column resume_info.motivation is 'motivation';
comment on column resume_info.self_public_relations is 'self_public_relations';
comment on column resume_info.update_time is 'update_time';

comment on table user_info is 'user_info';
comment on column user_info.uid is 'uid';
comment on column user_info.name is 'name';
comment on column user_info.icon is 'icon';
comment on column user_info.mail is 'mail';
comment on column user_info.tel is 'tel';
comment on column user_info.sex is 'sex';
comment on column user_info.adress is 'adress';
comment on column user_info.birthday is 'birthday';
comment on column user_info.update_time is 'update_time';

comment on table user_login is 'user_login';
comment on column user_login.uid is 'uid';
comment on column user_login.user_name is 'user_name';
comment on column user_login.password is 'password';
comment on column user_login.rocked is 'rocked';
comment on column user_login.user_role is 'user_role';
comment on column user_login.create_time is 'create_time';
comment on column user_login.update_time is 'update_time';

comment on table work_experience is 'work_experience';
comment on column work_experience.work_id is 'work_id';
comment on column work_experience.uid is 'uid';
comment on column work_experience.start_time is 'start_time';
comment on column work_experience.end_time is 'end_time';
comment on column work_experience.conpany_name is 'conpany_name';
comment on column work_experience.position is 'position';

--管理員追加
-- usreName:admin
--password: Admin
insert into user_login(user_name,password,rocked,user_role,create_time,update_time)values
('admin','e3afed0047b08059d0fada10f400c1e5',false,'admin','2000-01-01 00:00:00','2000-01-01 00:00:00');
insert into user_info(name,sex,birthday,update_time)values
('管理員',1,'1992-03-11 00:00:00','2000-01-01 00:00:00');
insert into resume_info(update_time)values('2000-01-01 00:00:00');
insert into company_info(update_time)values('2000-01-01 00:00:00');

-- テーブル削除,調整用
/*
drop table user_login;
drop table user_info;
drop table company_info;
drop table company_picture;
drop table resume_info;
drop table educational_background;
drop table work_experience;
drop table recrui_info;
drop table notice;
drop table resume;
drop table message_to_operation;
*/
