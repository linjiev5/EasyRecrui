-- Project Name : noname
-- Date/Time    : 2021/06/11 14:43:03
-- Author       : user
-- RDBMS Type   : PostgreSQL
-- Application  : A5:SQL Mk-2

/*
  BackupToTempTable, RestoreFromTempTable疑似命令が付加されています。
  これにより、drop table, create table 後もデータが残ります。
  この機能は一時的に $$TableName のような一時テーブルを作成します。
*/

-- 運営に連絡
--* RestoreFromTempTable
create table message_to_operation (
  message_id SERIAL
  , uid BIGINT
  , message VARCHAR(2048)
  , read BOOLEAN
  , update_time TIMESTAMP
  , constraint message_to_operation_PKC primary key (message_id)
) ;

-- 履歴提出
--* RestoreFromTempTable
create table resume (
  resume_id SERIAL
  , recrui_id BIGINT not null
  , send_uid BIGINT not null
  , recevie_uid BIGINT not null
  , result VARCHAR(2)
  , update_time TIMESTAMP
  , constraint resume_PKC primary key (resume_id)
) ;

-- お知らせ
--* RestoreFromTempTable
create table notice (
  notice_id SERIAL
  , title VARCHAR(128)
  , notice TEXT
  , update_time TIMESTAMP
  , constraint notice_PKC primary key (notice_id)
) ;

-- 会社情報
--* RestoreFromTempTable
create table company_info (
  uid SERIAL
  , company_name VARCHAR(128)
  , mail VARCHAR(128)
  , tel VARCHAR(20)
  , info VARCHAR(2048)
  , founded_time DATE
  , icon VARCHAR(128)
  , adress VARCHAR(256)
  , update_time TIMESTAMP
  , constraint company_info_PKC primary key (uid)
) ;

-- 会社写真
--* RestoreFromTempTable
create table company_picture (
  pic_id SERIAL
  , uid BIGINT
  , picture VARCHAR(128)
  , constraint company_picture_PKC primary key (pic_id)
) ;

-- 学歴
--* RestoreFromTempTable
create table educational_background (
  educational_id SERIAL
  , uid BIGINT
  , start_time DATE
  , end_time DATE
  , school_name VARCHAR(64)
  , constraint educational_background_PKC primary key (educational_id)
) ;

-- 求人情報表
--* RestoreFromTempTable
create table recrui_info (
  recrui_id SERIAL
  , uid BIGINT
  , employment_method VARCHAR(32)
  , category VARCHAR(64)
  , title VARCHAR(128)
  , salary VARCHAR(32)
  , welfare VARCHAR(128)
  , station VARCHAR(64)
  , job_details TEXT
  , update_time TIMESTAMP
  , constraint recrui_info_PKC primary key (recrui_id)
) ;

-- 履歴書情報
--* RestoreFromTempTable
create table resume_info (
  uid SERIAL
  , icon VARCHAR(128)
  , nationality VARCHAR(32)
  , name_katakana VARCHAR(64)
  , nearest_station VARCHAR(32)
  , hobbies_and_skills VARCHAR(64)
  , licence_or_qualification VARCHAR(64)
  , motivation TEXT
  , self_public_relations TEXT
  , update_time TIMESTAMP
  , constraint resume_info_PKC primary key (uid)
) ;

-- ユーザ個人情報
--* RestoreFromTempTable
create table user_info (
  uid SERIAL
  , name VARCHAR(32)
  , icon VARCHAR(128)
  , mail VARCHAR(128)
  , tel VARCHAR(20)
  , sex INT
  , adress VARCHAR(256)
  , birthday DATE
  , update_time TIMESTAMP
  , constraint user_info_PKC primary key (uid)
) ;

-- ログイン表
--* RestoreFromTempTable
create table user_login (
  uid SERIAL
  , user_name VARCHAR(128) not null
  , password VARCHAR(32)
  , rocked BOOLEAN
  , user_role VARCHAR(32)
  , create_time TIMESTAMP
  , update_time TIMESTAMP
  , constraint user_login_PKC primary key (uid)
) ;

-- 職歴
--* RestoreFromTempTable
create table work_experience (
  work_id SERIAL
  , uid BIGINT
  , start_time DATE
  , end_time DATE
  , conpany_name VARCHAR(128)
  , position VARCHAR(32)
  , constraint work_experience_PKC primary key (work_id)
) ;

comment on table message_to_operation is '運営に連絡';
comment on column message_to_operation.message_id is 'メッセージID';
comment on column message_to_operation.uid is 'ユーザID';
comment on column message_to_operation.message is 'メッセージ';
comment on column message_to_operation.read is 'チェック';
comment on column message_to_operation.update_time is '更新時間';

comment on table resume is '履歴提出';
comment on column resume.resume_id is '履歴ID';
comment on column resume.recrui_id is '求人情報ID';
comment on column resume.send_uid is '送るID';
comment on column resume.recevie_uid is 'もらうID';
comment on column resume.result is '結果';
comment on column resume.update_time is '更新時間';

comment on table notice is 'お知らせ';
comment on column notice.notice_id is 'お知らせID';
comment on column notice.title is 'タイトル';
comment on column notice.notice is 'お知らせ内容';
comment on column notice.update_time is '更新時間';

comment on table company_info is '会社情報';
comment on column company_info.uid is 'ユーザID';
comment on column company_info.company_name is '会社名';
comment on column company_info.mail is 'メール';
comment on column company_info.tel is '電話番号';
comment on column company_info.info is '会社情報';
comment on column company_info.founded_time is '創業時間';
comment on column company_info.icon is 'アイコンパス';
comment on column company_info.adress is '住所';
comment on column company_info.update_time is '更新時間';

comment on table company_picture is '会社写真';
comment on column company_picture.pic_id is '写真ID';
comment on column company_picture.uid is 'ユーザID';
comment on column company_picture.picture is '写真パス';

comment on table educational_background is '学歴';
comment on column educational_background.educational_id is '学歴ID';
comment on column educational_background.uid is 'ユーザID';
comment on column educational_background.start_time is '入学時間';
comment on column educational_background.end_time is '卒業時間';
comment on column educational_background.school_name is '学校名';

comment on table recrui_info is '求人情報表';
comment on column recrui_info.recrui_id is '求人ID';
comment on column recrui_info.uid is 'ユーザID';
comment on column recrui_info.employment_method is '雇用方式';
comment on column recrui_info.category is 'カテゴリ';
comment on column recrui_info.title is 'タイトル';
comment on column recrui_info.salary is '給与';
comment on column recrui_info.welfare is '福利厚生';
comment on column recrui_info.station is '駅';
comment on column recrui_info.job_details is '求人詳細';
comment on column recrui_info.update_time is '更新時間';

comment on table resume_info is '履歴書情報';
comment on column resume_info.uid is 'ユーザID';
comment on column resume_info.icon is 'アイコンパス';
comment on column resume_info.nationality is '国籍';
comment on column resume_info.name_katakana is 'カタカナ';
comment on column resume_info.nearest_station is '最寄駅';
comment on column resume_info.hobbies_and_skills is '趣味・特技';
comment on column resume_info.licence_or_qualification is '免許・資格';
comment on column resume_info.motivation is '希望動機';
comment on column resume_info.self_public_relations is '自己PR';
comment on column resume_info.update_time is '更新時間';

comment on table user_info is 'ユーザ個人情報';
comment on column user_info.uid is 'ユーザID';
comment on column user_info.name is '名前';
comment on column user_info.icon is 'アイコンパス';
comment on column user_info.mail is 'メール';
comment on column user_info.tel is '電話番号';
comment on column user_info.sex is '性別';
comment on column user_info.adress is '住所';
comment on column user_info.birthday is '誕生日';
comment on column user_info.update_time is '更新時間';

comment on table user_login is 'ログイン表';
comment on column user_login.uid is 'ユーザID';
comment on column user_login.user_name is 'ユーザ名';
comment on column user_login.password is 'パスワード';
comment on column user_login.rocked is 'ロック';
comment on column user_login.user_role is 'キャラクター';
comment on column user_login.create_time is '作成時間';
comment on column user_login.update_time is '更新時間';

comment on table work_experience is '職歴';
comment on column work_experience.work_id is '職歴ID';
comment on column work_experience.uid is 'ユーザID';
comment on column work_experience.start_time is '入社時間';
comment on column work_experience.end_time is '退社時間';
comment on column work_experience.conpany_name is '会社名';
comment on column work_experience.position is '職位';


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
